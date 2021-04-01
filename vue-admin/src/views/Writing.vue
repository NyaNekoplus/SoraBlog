<template>
  <v-container
    id="user-profile-view"
    fluid
    tag="section"
  >
    <view-intro>
      <v-file-input
        v-model="blogFile"
        accept=".md, .html"
        :rules="blogFileRules"
        hint="File extentions (.md, .html) File Size limits (max: 1mb)"
        placeholder="Upload blog file"
        prepend-icon="mdi-upload"
        outlined
        filled
        show-size
        @click:clear="localFileUrl='';blogFile=null"
        @change="handleBlogFile">
        <template v-slot:selection="{ text }">
          <v-chip
            small
            label
            color="primary"
            v-text="text"/>
        </template>
      </v-file-input>
    </view-intro>
    <v-form ref="form" v-model="isValid" lazy-validation>
      <v-row align="center" justify="center">
        <v-col cols="12">
          <v-row justify="center">
            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <material-card
                icon="mdi-calendar-today"
                icon-small
                title="Category"
                color="primary"
              >
                <v-card-text>
                  <!--
                  :hint="`${select.name}, ${select.uid}`"
                  -->
                  <v-select
                    v-model="category"
                    :items="categoryList"
                    item-text="name"
                    item-value="uid"
                    label="Select"
                    persistent-hint
                    return-object
                    single-line
                  ></v-select>
                  <!--
                  <v-select
                    v-model="category"
                    :items="categoryList"
                    hide-selected
                  >
                  </v-select>
                  -->
                </v-card-text>
                <!--
                <v-card-text>
                  <v-text-field
                    :rules="rules"
                    prepend-icon="mdi-tag-arrow-left-outline"
                  ></v-text-field>
                </v-card-text>
                -->
              </material-card>
            </v-col>

            <v-col
              cols="12"
              sm="6"
              md="4"
            >
              <material-card
                icon="mdi-calendar-today"
                icon-small
                title="Date"
                color="primary"
              >
                <v-card-text>
                  <v-menu
                    v-model="datePicker"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        :value="computedDateFormattedMomentjs"
                        :rules="ruleRequire"
                        clearable
                        label="Blog release date"
                        prepend-icon="mdi-calendar-outline"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        @click:clear="date = null"
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      v-model="date"
                      @change="datePicker = false"
                      width="290"
                    ></v-date-picker>
                  </v-menu>
                </v-card-text>
              </material-card>
            </v-col>

            <v-col cols="12" sm="6" md="4">
              <material-card
                icon="mdi-calendar-today"
                icon-small
                title="Time"
                color="primary"
              >
                <v-card-text>
                  <v-menu
                    ref="menu"
                    v-model="timePicker"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="time"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="time"
                        :rules="ruleRequire"
                        label="Blog release time"
                        prepend-icon="mdi-clock-time-four-outline"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-time-picker
                      v-if="timePicker"
                      v-model="time"
                      ampm-in-title
                      format="ampm"

                      landscape
                      scrollable
                      @click:minute="$refs.menu.save(time)"
                    ></v-time-picker>
                  </v-menu>
                </v-card-text>
              </material-card>
            </v-col>

          </v-row>

          <v-row justify="center">
            <v-col cols="12">
              <v-card>
                <v-sheet class="pa-6">
                  <v-row justify="center">
                    <v-col cols="8" md="8" >
                      <v-row>
                        <v-col cols="5" md="5" align-self="center">
                          <v-text-field
                            v-model="title"
                            :rules="ruleRequire"
                            label="Title"
                            prepend-icon="mdi-format-title"
                          ></v-text-field>
                        </v-col>
                        <v-spacer/>
                        <v-col cols="2" md="2" align-self="center" class="d-flex">

                          <span class="switch-label">开放评论</span>
                          <v-switch
                            v-model="enableComment"
                            inset
                          ></v-switch>

                        </v-col>
                        <v-col cols="4" md="4" align-self="center" class="d-flex">
                          <v-combobox
                            hint="置顶文章等级"
                            v-model="level"
                            :items="levelList"
                            item-value="value"
                            item-text="text"
                            return-object
                            hide-selected
                            outlined
                            prepend-icon="mdi-format-title"
                          ></v-combobox>
                        </v-col>
                      </v-row>
                      <br/>
                      <v-row>
                        <v-col cols="5" md="5" align-self="center">
                          <v-text-field
                            v-model="link"
                            :rules="ruleRequire"
                            label="Link(tooisorahe.com/link)"
                            prepend-icon="mdi-link-variant-plus"
                          ></v-text-field>
                        </v-col>
                        <v-spacer/>
                        <v-col cols="2" md="2" align-self="center" class="d-flex">

                          <span class="switch-label">作为草稿</span>
                          <v-switch
                            v-model="isDraft"
                            inset
                          ></v-switch>

                        </v-col>
                        <v-col cols="4" md="4" align-self="center">
                          <v-file-input
                            v-model="image"
                            accept=".png, .jpeg, .jpg"
                            :rules="imageRules"
                            hint="File extentions (.png, .jpg, .jpeg) File Size limits (max: 1mb)"
                            placeholder="Upload profile image"
                            prepend-icon="mdi-camera"
                            :disabled="loading.uploadIsLoading"
                            :loading="loading.uploadIsLoading"
                            outlined
                            filled
                            show-size
                            @click:clear="localImgUrl='';image=null"
                            @change="previewImage">
                            <template v-slot:selection="{ text }">
                              <v-chip
                                small
                                label
                                color="primary"
                                v-text="text"/>
                            </template>
                          </v-file-input>
                        </v-col>
                      </v-row>
                      <br/>
                      <v-row>
                        <v-col cols="5" md="5" align-self="center">
                          <v-combobox
                            v-model="tags"
                            :items="tagList"
                            item-text="name"
                            item-value="uid"
                            :rules="ruleRequire"
                            hint="Press enter to apply change"
                            persistent-hint
                            return-object
                            chips
                            clearable
                            label="Add tags for this Article"
                            multiple
                            prepend-icon="mdi-tag-arrow-left-outline"
                          >
                            <template v-slot:selection="{ attrs, item, select, selected }">
                              <v-chip
                                v-bind="attrs"
                                :input-value="{uid:null, name: selected}"
                                close
                                @click="select"
                                @click:close="removeTag(item)"
                              >
                                <v-icon left>
                                  mdi-bookmark-plus-outline
                                </v-icon>
                                <strong v-if="item.name">{{ item.name }}</strong>&nbsp;
                                <strong v-else>{{ item }}</strong>&nbsp;
                              </v-chip>
                            </template>
                          </v-combobox>
                        </v-col>
                        <v-spacer/>
                        <v-col cols="2" md="2" align-self="center" class="d-flex">
                          <span class="switch-label">是否原创</span>
                          <v-switch
                            v-model="isOriginal"
                            inset
                          ></v-switch>
                        </v-col>
                        <v-col cols="4" md="4" align-self="center">
                          <v-row justify="center">
                            <app-btn
                              :disabled="!isValid"
                              @click="handleSubmit"
                              large
                              rel="noopener noreferrer"
                              target="_blank"
                              class="align-content-center"
                              max-width="auto"
                            >
                              <v-icon left>
                                mdi-playlist-check
                              </v-icon>
                              <span>Release</span>
                            </app-btn>
                          </v-row>
                        </v-col>
                      </v-row>
                    </v-col>

                    <v-col cols="4" md="4" align-self="center">
                      <v-row justify="center">
                        <v-img
                          :src="localImgUrl"
                          lazy-src="https://picsum.photos/id/11/100/60"
                          :aspect-ratio="16/9"
                          max-width="550"
                        >
                          <template v-slot:placeholder>
                            <v-row
                              class="fill-height ma-0"
                              align="center"
                              justify="center"
                            >
                              <v-progress-circular
                                indeterminate
                                color="grey lighten-5"
                              ></v-progress-circular>
                            </v-row>
                          </template>
                        </v-img>
                      </v-row>

                    </v-col>

                  </v-row>

                  <v-row v-if="!isOriginal" justify="center">
                    <v-col cols="5">
                      <v-text-field
                        clearable
                        v-model="author"
                        :rules="ruleRequire"
                        label="Author"
                        prepend-icon="mdi-format-title"
                      ></v-text-field></v-col>
                    <v-spacer/>
                    <v-col cols="5">
                      <v-text-field
                        clearable
                        v-model="articleSource"
                        :rules="ruleRequire"
                        label="Source of the article"
                        prepend-icon="mdi-format-title"
                      ></v-text-field></v-col>
                  </v-row>

                  <v-row justify="center">
                    <v-col cols="12">
                      <vue-tinymce
                        v-model="contentMd"
                        :setting="setting" />
                    </v-col>
                  </v-row>
                </v-sheet>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script>
import {submitArticle} from "../api/article";
import {getCategoryList} from "../api/category";
import {getTagList} from "../api/tag";
import {uploadImage} from "../api/image";

export default {
  name: "Writing",
  data() {
    return {
      setting: {
        //menubar: 'file edit insert view format table tools help',
        menubar: false,
        toolbar: "undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | codesample | code | toc | preview |",
        toolbar_mode: "floating",
        quickbars_selection_toolbar: "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor",
        plugins: "link image media table lists fullscreen quickbars codesample code preview toc",

        language: 'zh_CN', //本地化设置
        branding: false,
        statusbar: false,
        skin: 'oxide',
        height: '70vh',

        images_upload_url: '/admin/upimg.php',
        images_upload_base_path: '/admin',
        //toc_depth: 3,
        //toc_class: 'my-class',
        //toc_header: 'div'
      },

      isValid: true,
      loading: {
        uploadIsLoading: false,
      },

      level: {value:0,text:'Default'},
      levelList: [{value:3,text:'Level-3'},{value:2,text:'Level-2'},{value:1,text:'Level-1'},{value:0,text:'Default'}],

      categoryList: [],

      date: null,
      datePicker: false,
      time: null,
      timePicker: false,
      category: {uid: Number,name: ''},

      title: '',
      link: '',

      tags: [],
      tagList: [],

      enableComment: true,
      isDraft: true,
      isOriginal: true,

      contentMd: '<h1 style="text-align: center;">长恨歌</h1><p style="text-align: center;">汉皇重色思倾国，御宇多年求不得。<br />杨家有女初长成，养在深闺人未识。<br />天生丽质难自弃，一朝选在君王侧。<br />回眸一笑百媚生，六宫粉黛无颜色。<br />春寒赐浴华清池，温泉水滑洗凝脂。<br />侍儿扶起娇无力，始是新承恩泽时。<br />云鬓花颜金步摇，芙蓉帐暖度春宵。<br />春宵苦短日高起，从此君王不早朝。<br />承欢侍宴无闲暇，春从春游夜专夜。<br />后宫佳丽三千人，三千宠爱在一身。<br />金屋妆成娇侍夜，玉楼宴罢醉和春。<br />姊妹弟兄皆列土，可怜光彩生门户。<br />遂令天下父母心，不重生男重生女。<br />骊宫高处入青云，仙乐风飘处处闻。<br />缓歌慢舞凝丝竹，尽日君王看不足。<br />渔阳鼙鼓动地来，惊破霓裳羽衣曲。</p><p style="text-align: center;">九重城阙烟尘生，千乘万骑西南行。<br />翠华摇摇行复止，西出都门百余里。<br />六军不发无奈何，宛转蛾眉马前死。<br />花钿委地无人收，翠翘金雀玉搔头。<br />君王掩面救不得，回看血泪相和流。<br />黄埃散漫风萧索，云栈萦纡登剑阁。<br />峨嵋山下少人行，旌旗无光日色薄。<br />蜀江水碧蜀山青，圣主朝朝暮暮情。<br />行宫见月伤心色，夜雨闻铃肠断声。<br />天旋日转回龙驭，到此踌躇不能去。<br />马嵬坡下泥土中，不见玉颜空死处。<br />君臣相顾尽沾衣，东望都门信马归。<br />归来池苑皆依旧，太液芙蓉未央柳。<br />芙蓉如面柳如眉，对此如何不泪垂。<br />春风桃李花开夜，秋雨梧桐叶落时。<br />西宫南苑多秋草，落叶满阶红不扫。<br />梨园弟子白发新，椒房阿监青娥老。<br />夕殿萤飞思悄然，孤灯挑尽未成眠。<br />迟迟钟鼓初长夜，耿耿星河欲曙天。<br />鸳鸯瓦冷霜华重，翡翠衾寒谁与共。<br />悠悠生死别经年，魂魄不曾来入梦。</p><p style="text-align: center;">临邛道士鸿都客，能以精诚致魂魄。<br />为感君王辗转思，遂教方士殷勤觅。<br />排空驭气奔如电，升天入地求之遍。<br />上穷碧落下黄泉，两处茫茫皆不见。<br />忽闻海上有仙山，山在虚无缥缈间。<br />楼阁玲珑五云起，其中绰约多仙子。<br />中有一人字太真，雪肤花貌参差是。<br />金阙西厢叩玉扃，转教小玉报双成。<br />闻道汉家天子使，九华帐里梦魂惊。<br />揽衣推枕起徘徊，珠箔银屏迤逦开。<br />云鬓半偏新睡觉，花冠不整下堂来。</p><p style="text-align: center;">风吹仙袂飘飖举，犹似霓裳羽衣舞。<br />玉容寂寞泪阑干，梨花一枝春带雨。<br />含情凝睇谢君王，一别音容两渺茫。<br />昭阳殿里恩爱绝，蓬莱宫中日月长。<br />回头下望人寰处，不见长安见尘雾。<br />惟将旧物表深情，钿合金钗寄将去。<br />钗留一股合一扇，钗擘黄金合分钿。<br />但令心似金钿坚，天上人间会相见。</p><p style="text-align: center;">临别殷勤重寄词，词中有誓两心知。<br />七月七日长生殿，夜半无人私语时。<br />在天愿作比翼鸟，在地愿为连理枝。<br />天长地久有时尽，此恨绵绵无绝期。</p>',

      ruleRequire: [value => !!value || 'Required.'],
      rules: [
        value => !!value || 'Required.',
        value => (value || '').length <= 20 || 'Max 20 characters',
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        },
      ],

      image: [],
      localImgUrl: '',
      imageRules: [
        value => !value || value.size < 1000000 || 'Image size should be less than 1 MB!',
      ],

      blogFile: [],
      localFileUrl: '',
      blogFileRules: [
        value => !value || value.size < 1000000 || 'File size should be less than 1 MB!',
      ],

      author: 'Vincent Willem van Gogh',
      articleSource: 'Starry Night Over the Rhone ---Musée d\'Orsay, Paris, France',


      requestParam: {
        userUid: 1,
        projectName: 'sora-admin',
        classificationName: 'blog-image'
      }
    }
  },
  computed: {
    computedDateFormattedMomentjs () {
      return this.date;
      //return this.date ? moment(this.date).format('dddd, MMMM Do YYYY') : ''
    },
  },
  watch: {
    tags() {
      console.log('watch tags: '+this.tags);
    }
  },
  methods: {
    previewImage(){
      this.localImgUrl = URL.createObjectURL(this.image);
      console.log(this.image);
      console.log(this.localImgUrl);
    },
    handleBlogFile(){
      this.localFileUrl = URL.createObjectURL(this.blogFile);
      console.log(this.blogFile);
      console.log(this.localFileUrl);
    },
    uploadFiles({files,params,success,error}){
      let formData = new FormData();
      files.map(file=>formData.append('filedatas',file.raw));
      for (let key in params){
        formData.append(key,params[key]);
      }
      let xhr = new XMLHttpRequest();
      xhr.open('post',process.env.VUE_APP_FILE_API+'/images',true);
      xhr.onreadystatechange = function (){
        if (xhr.readyState === 4){
          if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
            success && success(xhr.responseText);
          }else {
            error && error(xhr.status);
          }
        }
      }
      xhr.send(formData);
    },
    handleSubmit(){
      this.loading.uploadIsLoading = true;
      let formData = new window.FormData();
      formData.append('imageList', this.image);
      for (let key in this.requestParam){
        formData.append(key,this.requestParam[key]);
      }

      //param.imageUid = null;
      uploadImage(formData).then(fileRes=>{
        if (fileRes.data.state === 'success'){
          console.log('上传图片返回数据：'+fileRes);
          alert(fileRes.data.message);

          let activeEditor = tinymce.activeEditor;
          let editBody = activeEditor.getBody();
          activeEditor.selection.select(editBody);
          let content = activeEditor.selection.getContent( {'format' : 'text'});
          //let content = tinyMCE.activeEditor.selection.getContent({ format: 'text' });
          let createTime = this.date + ' ' + this.time;

          console.log('纯文本： '+content);
          console.log('HTML文本： '+this.contentMd);
          console.log('Date: '+ createTime);
          console.log('Tags: '+ this.tags);

          let param = {};
          param.tagList = [];
          this.tags.forEach(t=>{
            param.tagList.push({
              uid: t.uid, name: (t.name)?t.name:t
            })})
          param.categoryUid = this.category.uid;
          param.title = this.title;
          param.link = this.link;
          param.lang = 0; // zhs by default
          param.content = content;
          param.contentMd = this.contentMd;
          param.enableComment = this.enableComment;
          param.isDraft = this.isDraft;
          param.isOriginal = this.isOriginal;
          param.level = this.level.value;
          param.coverUid = fileRes.data.data[0].uid;
          param.createTime = createTime;
          submitArticle(param).then(response=>{
            if (response.data.state === 'success'){
              alert(response.data.message);
              this.contentMd = '';
              //this.$message.show(response.data.message);
              this.$message.success(response.data.message);
            }else {
              this.$message.error(response.data.message);
              alert(response.data.message);
            }
          });
          this.loading.uploadIsLoading = false;
          this.$message.success(response.data.message);
        }else {
          this.$message.error(response.data.message);
          this.loading.uploadIsLoading = false;
          alert(response.data.message);
        }
      });
    },
    removeTag (item) {
      this.tags.splice(this.tags.indexOf(item), 1)
      this.tags = [...this.tags]
    },
  },
  created() {
    getCategoryList().then(response => {
      let data = response.data.data;
      this.categoryList = data;
      this.category = data[0];
      console.log('category list:'+response.data.data);
    })
    getTagList().then(response => {
      let data = response.data.data;
      this.tagList = data;
      this.tags = data;
      console.log('tag list:'+response.data.data);
    })
  }
}
</script>

<style scoped>
.switch-label{
  align-self: center;
  margin-right: 0.5rem;
}

</style>
