<template>
  <v-container
    id="user-profile-view"
    fluid
    tag="section"
  >
    <v-row
      align="center"
      justify="center"
    >
      <v-col cols="12">
        <!--
        <material-card
          color="success"
          full-header
        >
          <template #heading>
            <div class="pa-6 white--text">
              <div class="text-h4 font-weight-light">
                思绪如风，偶尔在这里停留
              </div>

              <div class="text-subtitle-1">
                Created using the Roboto Font Family
              </div>
            </div>
          </template>
          -->
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
                <v-combobox
                  v-model="articleProps.category"
                  :items="category"
                  hide-selected
                >

                </v-combobox>
              </v-card-text>
              <!--
              <v-card-text>
                <v-text-field
                  :rules="articleProps.rules"
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
                  v-model="articleProps.datePicker"
                  :close-on-content-click="false"
                  :nudge-right="40"
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
                      @click:clear="articleProps.date = null"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="articleProps.date"
                    @change="articleProps.datePicker = false"
                    full-width
                  ></v-date-picker>
                </v-menu>
              </v-card-text>
              <!--
              <v-date-picker
                v-model="articleProps.date"
                :first-day-of-week="0"
                locale="zh-cn"
              ></v-date-picker>
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
              title="Time"
              color="primary"
            >
              <v-card-text>
                <v-menu
                  ref="menu"
                  v-model="articleProps.timePicker"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="articleProps.time"
                  transition="scale-transition"
                  offset-y

                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="articleProps.time"
                      :rules="ruleRequire"
                      label="Blog release time"
                      prepend-icon="mdi-clock-time-four-outline"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    v-if="articleProps.timePicker"
                    v-model="articleProps.time"
                    ampm-in-title
                    format="ampm"
                    full-width
                    landscape
                    scrollable
                    @click:minute="$refs.menu.save(articleProps.time)"
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
                  <v-col cols="6" md="6" align-self="center">
                    <v-text-field
                      v-model="articleProps.title"
                      :rules="ruleRequire"
                      label="Title"
                      prepend-icon="mdi-format-title"
                    >

                    </v-text-field>

                    <v-divider

                    ></v-divider>

                    <v-text-field
                      v-model="articleProps.link"
                      :rules="ruleRequire"
                      label="Link(tooisorahe.com/link)"
                      prepend-icon="mdi-link-variant-plus"
                    >
                    </v-text-field>

                    <v-divider

                    ></v-divider>

                    <v-combobox
                      v-model="articleProps.tags"
                      :rules="ruleRequire"
                      chips
                      clearable
                      label="Add tags for this Article"
                      multiple
                      prepend-icon="mdi-tag-arrow-left-outline"
                    >
                      <template v-slot:selection="{ attrs, item, select, selected }">
                        <v-chip
                          v-bind="attrs"
                          :input-value="selected"
                          close
                          @click="select"
                          @click:close="removeTag(item)"
                        >
                          <v-icon left>
                            mdi-bookmark-plus-outline
                          </v-icon>
                          <strong>{{ item }}</strong>&nbsp;
                        </v-chip>
                      </template>
                    </v-combobox>
                  </v-col>

                  <v-col cols="2" md="2" align-self="center">
                    <v-row>
                      <span class="switch-label">开放评论</span>
                      <v-switch
                        v-model="articleProps.enableComment"
                        inset
                      ></v-switch>
                    </v-row>

                    <v-row>
                      <span class="switch-label">作为草稿</span>
                      <v-switch
                        v-model="articleProps.isDraft"
                        inset
                      ></v-switch>
                    </v-row>

                    <v-row>
                      <span class="switch-label">置顶文章</span>
                      <v-switch
                        v-model="articleProps.isTop"
                        inset
                      ></v-switch>
                    </v-row>
                  </v-col>

                  <v-col cols="4" md="4" align-self="center">
                    <app-btn
                      @click="handleSubmit"
                      large
                      rel="noopener noreferrer"
                      target="_blank"
                    >
                      <v-icon left>
                        mdi-playlist-check
                      </v-icon>

                      <span>Release</span>
                    </app-btn>
                    <app-btn
                      @click="testFunc"
                      large
                      rel="noopener noreferrer"
                      target="_blank"
                    >
                      <v-icon left>
                        mdi-playlist-check
                      </v-icon>

                      <span>Test</span>
                    </app-btn>
                  </v-col>

                </v-row>

                <v-row justify="center">
                  <v-col cols="12">
                    <vue-tinymce
                      v-model="articleProps.contentMd"
                      :setting="setting" />
                  </v-col>
                </v-row>
              </v-sheet>
            </v-card>
          </v-col>
        </v-row>


      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {submitArticle} from "../api/article";

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

      category: ['Life', 'Tech'],
      articleProps: {

        date: null,
        datePicker: false,
        time: null,
        timePicker: false,
        category: 'Life',

        title: '',
        link: '',
        tags: [],
        //chips: ['Programming', 'Playing video games', 'Watching movies', 'Sleeping'],

        enableComment: true,
        isTop: false,
        isDraft: true,

        contentMd: '<h1 style="text-align: center;">长恨歌</h1><p style="text-align: center;">汉皇重色思倾国，御宇多年求不得。<br />杨家有女初长成，养在深闺人未识。<br />天生丽质难自弃，一朝选在君王侧。<br />回眸一笑百媚生，六宫粉黛无颜色。<br />春寒赐浴华清池，温泉水滑洗凝脂。<br />侍儿扶起娇无力，始是新承恩泽时。<br />云鬓花颜金步摇，芙蓉帐暖度春宵。<br />春宵苦短日高起，从此君王不早朝。<br />承欢侍宴无闲暇，春从春游夜专夜。<br />后宫佳丽三千人，三千宠爱在一身。<br />金屋妆成娇侍夜，玉楼宴罢醉和春。<br />姊妹弟兄皆列土，可怜光彩生门户。<br />遂令天下父母心，不重生男重生女。<br />骊宫高处入青云，仙乐风飘处处闻。<br />缓歌慢舞凝丝竹，尽日君王看不足。<br />渔阳鼙鼓动地来，惊破霓裳羽衣曲。</p><p style="text-align: center;">九重城阙烟尘生，千乘万骑西南行。<br />翠华摇摇行复止，西出都门百余里。<br />六军不发无奈何，宛转蛾眉马前死。<br />花钿委地无人收，翠翘金雀玉搔头。<br />君王掩面救不得，回看血泪相和流。<br />黄埃散漫风萧索，云栈萦纡登剑阁。<br />峨嵋山下少人行，旌旗无光日色薄。<br />蜀江水碧蜀山青，圣主朝朝暮暮情。<br />行宫见月伤心色，夜雨闻铃肠断声。<br />天旋日转回龙驭，到此踌躇不能去。<br />马嵬坡下泥土中，不见玉颜空死处。<br />君臣相顾尽沾衣，东望都门信马归。<br />归来池苑皆依旧，太液芙蓉未央柳。<br />芙蓉如面柳如眉，对此如何不泪垂。<br />春风桃李花开夜，秋雨梧桐叶落时。<br />西宫南苑多秋草，落叶满阶红不扫。<br />梨园弟子白发新，椒房阿监青娥老。<br />夕殿萤飞思悄然，孤灯挑尽未成眠。<br />迟迟钟鼓初长夜，耿耿星河欲曙天。<br />鸳鸯瓦冷霜华重，翡翠衾寒谁与共。<br />悠悠生死别经年，魂魄不曾来入梦。</p><p style="text-align: center;">临邛道士鸿都客，能以精诚致魂魄。<br />为感君王辗转思，遂教方士殷勤觅。<br />排空驭气奔如电，升天入地求之遍。<br />上穷碧落下黄泉，两处茫茫皆不见。<br />忽闻海上有仙山，山在虚无缥缈间。<br />楼阁玲珑五云起，其中绰约多仙子。<br />中有一人字太真，雪肤花貌参差是。<br />金阙西厢叩玉扃，转教小玉报双成。<br />闻道汉家天子使，九华帐里梦魂惊。<br />揽衣推枕起徘徊，珠箔银屏迤逦开。<br />云鬓半偏新睡觉，花冠不整下堂来。</p><p style="text-align: center;">风吹仙袂飘飖举，犹似霓裳羽衣舞。<br />玉容寂寞泪阑干，梨花一枝春带雨。<br />含情凝睇谢君王，一别音容两渺茫。<br />昭阳殿里恩爱绝，蓬莱宫中日月长。<br />回头下望人寰处，不见长安见尘雾。<br />惟将旧物表深情，钿合金钗寄将去。<br />钗留一股合一扇，钗擘黄金合分钿。<br />但令心似金钿坚，天上人间会相见。</p><p style="text-align: center;">临别殷勤重寄词，词中有誓两心知。<br />七月七日长生殿，夜半无人私语时。<br />在天愿作比翼鸟，在地愿为连理枝。<br />天长地久有时尽，此恨绵绵无绝期。</p>',
      },
      ruleRequire: [value => !!value || 'Required.'],
      rules: [
        value => !!value || 'Required.',
        value => (value || '').length <= 20 || 'Max 20 characters',
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        },
      ],
    }
  },
  computed: {
    computedDateFormattedMomentjs () {
      return this.articleProps.date;
      //return this.articleProps.date ? moment(this.articleProps.date).format('dddd, MMMM Do YYYY') : ''
    },
  },
  methods: {
    testFunc(){
      this.$message({
        content: '成功',
        type: 'success'
      });

      console.log(this.$message);
      //this.$message()
    },
    handleSubmit(){

      let activeEditor = tinymce.activeEditor;
      let editBody = activeEditor.getBody();
      activeEditor.selection.select(editBody);
      let content = activeEditor.selection.getContent( {'format' : 'text'});

      //let content = tinyMCE.activeEditor.selection.getContent({ format: 'text' });
      let releaseTime = this.articleProps.date + ' ' + this.articleProps.time;

      console.log('纯文本： '+content);
      console.log('HTML文本： '+this.articleProps.contentMd);
      console.log('Date: '+ releaseTime);
      console.log('Tags: '+ this.articleProps.tags);

      let param = {};
      param.title = this.articleProps.title;
      param.link = this.articleProps.link;
      param.language = 0; // zhs by default
      param.content = content;
      param.contentMd = this.articleProps.contentMd;
      param.enableComment = this.articleProps.enableComment;
      param.isDraft = this.articleProps.isDraft;
      param.isTop = this.articleProps.isTop;
      param.releaseTime = releaseTime;
      submitArticle(param).then(response=>{
        console.log(response.data);
        if (response.data.state === 'success'){
          alert(response.data.message);
          //this.$message.show(response.data.message);
          this.$message.success(response.data.message);
        }else {
          this.$message.error(response.data.message);
          alert(response.data.message);
        }
      });
    },
    removeTag (item) {
      this.articleProps.tags.splice(this.articleProps.tags.indexOf(item), 1)
      this.articleProps.tags = [...this.articleProps.tags]
    },
  }
}
</script>

<style scoped>
.switch-label{
  align-self: center;
  margin-right: 0.5rem;
}

</style>
