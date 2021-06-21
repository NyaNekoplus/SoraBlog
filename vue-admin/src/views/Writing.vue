<template>
  <v-container id="user-profile-view" fluid tag="section">
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
            <v-col cols="12" sm="6" md="4">
              <material-card icon="mdi-calendar-today" icon-small title="Category" color="primary">
                <v-card-text>
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
                </v-card-text>
              </material-card>
            </v-col>

            <v-col cols="12" sm="6" md="4">
              <material-card icon="mdi-calendar-today" icon-small title="Date" color="primary">
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
                      <!--:rules="ruleRequire"-->
                      <v-text-field
                        :value="computedDateFormattedMomentjs"
                        clearable
                        label="Blog release date"
                        prepend-icon="mdi-calendar-outline"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        @click:clear="date = null"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="date" @change="datePicker = false" width="290"></v-date-picker>
                  </v-menu>
                </v-card-text>
              </material-card>
            </v-col>

            <v-col cols="12" sm="6" md="4">
              <material-card icon="mdi-calendar-today" icon-small title="Time" color="primary">
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
                      <!--:rules="ruleRequire"-->
                      <v-text-field
                        v-model="time"
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
                          <v-switch v-model="enableComment" inset></v-switch>
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
                            :rules="[value => !!value || 'Required.' , value => !value || value[0]!=='/' || '首字符不能为/']"
                            label="Link(tooisorahe.com/link)"
                            prepend-icon="mdi-link-variant-plus"
                          ></v-text-field>
                        </v-col>
                        <v-spacer/>
                        <v-col cols="2" md="2" align-self="center" class="d-flex">
                          <span class="switch-label">作为草稿</span>
                          <v-switch v-model="isDraft" inset></v-switch>
                        </v-col>
                        <v-col cols="4" md="4" align-self="center">
                          <!--                            success-messages="如不上传将使用默认封面"-->
                          <v-file-input
                            clearable
                            v-model="image"
                            accept=".png, .jpeg, .jpg, .jiff, .PNG"
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
                                <v-icon left>mdi-bookmark-plus-outline</v-icon>
                                <strong v-if="item.name">{{ item.name }}</strong>&nbsp;
                                <strong v-else>{{ item }}</strong>&nbsp;
                              </v-chip>
                            </template>
                          </v-combobox>
                        </v-col>
                        <v-spacer/>
                        <v-col cols="2" md="2" align-self="center" class="d-flex">
                          <span class="switch-label">是否原创</span>
                          <v-switch v-model="isOriginal" inset></v-switch>
                        </v-col>
                        <v-col cols="4" md="4" align-self="center">
                          <v-row justify="center">
                            <!--:disabled="!isValid"-->
                            <app-btn
                              :disabled="!isValid"
                              @click="handleSubmit"
                              large
                              rel="noopener noreferrer"
                              target="_blank"
                              class="align-content-center"
                              max-width="auto"
                            >
                              <v-icon left>mdi-playlist-check</v-icon>
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
                            <v-row class="fill-height ma-0" align="center" justify="center">
                              <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
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
import {get} from 'vuex-pathify'
import {submitArticle} from "../api/article";
import {getCategoryList} from "../api/category";
import {getTagList} from "../api/tag";
import {uploadImage} from "../api/file";

export default {
  name: "Writing",
  data: () => ({
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

    contentMd: '<h1 style="text-align: center;">大丈夫生于天地之间,岂能郁郁久居人下!</h1>春宵苦短日高起，从此君王不早朝。<br />在天愿作比翼鸟，在地愿为连理枝。<br />天长地久有时尽，此恨绵绵无绝期。</p>',

    ruleRequire: [value => !!value || 'Required.'],
    rules: [
      value => !!value || 'Required.',
      value => (value || '').length <= 20 || 'Max 20 characters',
      value => {
        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        return pattern.test(value) || 'Invalid e-mail.'
      },
    ],

    image: null,
    localImgUrl: '',
    imageRules: [
      value => {
        if (value===null){return true;}
        else {return !value || value.size < 2000000 || 'Image size should be less than 2 MB!';}
      },
      //value => !!value || !value || value.size < 2000000 || 'Image size should be less than 2 MB!',
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
  }),
  computed: {
    setting: get('editor/setting'),
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
      try {
        this.localImgUrl = URL.createObjectURL(this.image);
      }catch (e) {
        console.log(e);
        this.localImgUrl = this.image;
      }

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
      console.log('is valid: ' +this.isValid);
      if (!this.$refs.form.validate()){
        return ;
      }
      let param = {};
      let activeEditor = tinymce.activeEditor;
      let editBody = activeEditor.getBody();
      activeEditor.selection.select(editBody);
      let content = activeEditor.selection.getContent( {'format' : 'text'});

      let createTime = null;
      if (this.date !== null&&this.time !== null){
        createTime = this.date + ' ' + this.time + ':00';
      }
      console.log('纯文本： '+content);
      console.log('HTML文本： '+this.contentMd);
      console.log('Date: '+ createTime);
      console.log('Tags: '+ this.tags);

      param.tagList = [];
      this.tags.forEach(t=>{param.tagList.push({uid: t.uid, name: (t.name)?t.name:t})})
      param.categoryUid = this.category.uid;
      param.title = this.title;
      param.link = this.link[0]==='/'?this.link.substr(1):this.link;
      param.lang = 0; // zhs by default
      param.content = content;
      param.contentMd = this.contentMd;
      param.enableComment = this.enableComment;
      param.isDraft = this.isDraft;
      param.isOriginal = this.isOriginal;
      param.level = this.level.value;
      param.createTime = createTime;
      if (this.image!=null){
        this.loading.uploadIsLoading = true;
        let formData = new window.FormData();
        formData.append('imageList', this.image);
        for (let key in this.requestParam){formData.append(key,this.requestParam[key]);}
        uploadImage(formData).then(fileRes=>{
          if (fileRes.state === this.$STATE.SUCCESS){
            console.log('上传图片返回数据：'+fileRes);
            alert(fileRes.message);
            param.coverUid = fileRes.data[0].uid;
            submitArticle(param).then(response=>{
              if (response.state === this.$STATE.SUCCESS){
                alert(response.message);
                this.contentMd = '';
              }else {
                alert(response.message);
              }
            });
            this.loading.uploadIsLoading = false;
            //this.$message.success(response.message);
          }else {
            //this.$message.error(response.message);
            this.loading.uploadIsLoading = false;
          }
        });
      } else {
        submitArticle(param).then(response=>{
          if (response.state === this.$STATE.SUCCESS){
            alert(response.message);
            this.contentMd = '';
            //this.$message.show(response.data.message);
            //this.$message.success(response.message);
          }else {
            this.$message.error(response.message);
            alert(response.message);
          }
        });
      }
    },
    removeTag (item) {
      this.tags.splice(this.tags.indexOf(item), 1)
      this.tags = [...this.tags]
    },
  },
  created() {
    getCategoryList().then(response => {
      let data = response.data;
      this.categoryList = data;
      this.category = data[0];
      console.log('category list:'+response.data);
    })
    getTagList().then(response => {
      let data = response.data;
      this.tagList = data;
      this.tags = data;
      console.log('tag list:'+response.data);
    })
  },
  deactivated(){
    tinymce.remove()
  },
  beforeDestroy(){
    tinymce.remove();
  },
}
</script>

<style scoped>
.switch-label{
  align-self: center;
  margin-right: 0.5rem;
}

</style>
