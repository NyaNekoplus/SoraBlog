<template>
  <v-container id="user-profile-view" fluid tag="section">
    <material-card color="accent" full-header>
      <template #heading>
        <v-tabs
          v-model="tabs"
          background-color="transparent"
          slider-color="white"
          class="pa-8"
        >
          <span class="subheading font-weight-light mx-3" style="align-self: center">Tasks:</span>
          <v-tab @click="getBlogList(false)" class="mr-3">
            <v-icon class="mr-2">mdi-code-tags</v-icon>
            List
          </v-tab>
          <v-tab @click="getBlogList(true)">
            <v-icon class="mr-2">mdi-cloud</v-icon>
            Recycle Bin
          </v-tab>
        </v-tabs>
      </template>
      <v-tabs-items v-model="tabs" background-color="transparent">
        <v-tab-item v-for="n in 2" :key="n">
          <v-card-text>
            <v-row align="center">
              <v-col cols="12">
                <v-data-table
                  :headers="headers"
                  :items="blogList"
                  :page="totalPage"
                  :items-per-page="pageSize"
                  :server-items-length="blogNum"
                  :options.sync="options"
                  :loading="loading"
                  :search="searchStr"
                  calculate-widths
                  multi-sort
                  class="elevation-1"
                  :footer-props="{
                    showFirstLastPage: true,
                    firstIcon: 'mdi-arrow-collapse-left',
                    lastIcon: 'mdi-arrow-collapse-right',
                    prevIcon: 'mdi-minus',
                    nextIcon: 'mdi-plus'
                  }"
                >
                  <template v-slot:top>
                    <v-toolbar flat>
                      <v-spacer></v-spacer>
                      <v-text-field
                        v-model="searchStr"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                      ></v-text-field>
                      <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
                        <v-card>
                          <v-toolbar dark color="primary">
                              <v-btn icon dark @click="dialog = false">
                                <v-icon>mdi-close</v-icon>
                              </v-btn>
                              <v-toolbar-title>Editing</v-toolbar-title>
                              <v-spacer></v-spacer>
                              <v-toolbar-items>
                                <v-btn dark text @click="save(item)">Save</v-btn>
                              </v-toolbar-items>
                            </v-toolbar>
                          <v-list three-line subheader>
                              <v-subheader>User Controls</v-subheader>
                              <v-list-item>
                                <v-list-item-content>
                                  <v-list-item-title>Content filtering</v-list-item-title>
                                  <v-list-item-subtitle>Set the content filtering level to restrict apps that can be downloaded</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                              <v-list-item>
                                <v-list-item-content>
                                  <v-list-item-title>Password</v-list-item-title>
                                  <v-list-item-subtitle>Require password for purchase or use password to restrict purchase</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                            </v-list>
                          <v-divider></v-divider>
                          <v-list three-line subheader>
                              <v-subheader>General</v-subheader>
                              <v-list-item>
                                <v-list-item-action>
                                  <v-checkbox></v-checkbox>
                                </v-list-item-action>
                                <v-list-item-content>
                                  <v-list-item-title>Notifications</v-list-item-title>
                                  <v-list-item-subtitle>Notify me about updates to apps or games that I downloaded</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                              <v-list-item>
                                <v-list-item-action>
                                  <v-checkbox></v-checkbox>
                                </v-list-item-action>
                                <v-list-item-content>
                                  <v-list-item-title>Sound</v-list-item-title>
                                  <v-list-item-subtitle>Auto-update apps at any time. Data charges may apply</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                              <v-list-item>
                                <v-list-item-action>
                                  <v-checkbox></v-checkbox>
                                </v-list-item-action>
                                <v-list-item-content>
                                  <v-list-item-title>Auto-add widgets</v-list-item-title>
                                  <v-list-item-subtitle>Automatically add home screen widgets</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                            </v-list>
                          <vue-tinymce v-model="contentMd" :setting="setting" />
                        </v-card>
                      </v-dialog>
                      <v-dialog v-model="dialogDelete" max-width="500px" :retain-focus="false">
                        <v-card>
                          <v-card-title class="headline">Are you sure you want to delete this item?</v-card-title>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                            <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                            <v-spacer></v-spacer>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-toolbar>
                  </template>
                  <template v-slot:item.cover="{ item }">
                    <v-img :src="item.coverUrl" max-width="160" max-height="90"></v-img>
                  </template>
                  <template v-slot:item.tags="{ item }">
                    <v-chip-group active-class="primary--text" column>
                      <v-chip v-for="tag in item.tagList" :key="tag.uid">
                        {{ tag.name }}
                      </v-chip>
                    </v-chip-group>
                  </template>
                  <template v-slot:item.enableComment="{ item }">
                    <v-switch v-model="item.enableComment" inset @click="changeArticleState(item,false)"></v-switch>
                  </template>
                  <template v-slot:item.level="{ item }">
                    <v-chip class="ma-2" :color="item.level===1?'primary':item.level===2?'cyan':item.level===3?'red':'secondary'" label text-color="white">
                      <v-icon left>{{ item.level===1?'network-strength-1':item.level===2?'network-strength-2':item.level===3?'network-strength-4':'network-strength-outline' }}</v-icon>
                      {{item.level}}
                    </v-chip>
                  </template>
                  <template v-slot:item.actions="{ item }">
                    <app-btn icon text elevation="1" min-width="0" class="px-2 ml-1">
                      <v-icon @click="editItem(item)">mdi-file-edit-outline</v-icon>
                    </app-btn>
                    <app-btn icon text elevation="1" min-width="0" class="px-2 ml-1">
                      <v-icon @click="changeArticleState(item,true)">
                        {{tabs===0?'mdi-eye-off':'mdi-eye'}}
                      </v-icon>
                    </app-btn>
                    <app-btn icon text elevation="1" min-width="0" class="px-2 ml-1">
                      <v-icon @click="deleteItem(item)">mdi-trash-can-outline</v-icon>
                    </app-btn>
                  </template>
                  <template v-slot:no-data>
                    <v-btn color="primary">Reset</v-btn>
                  </template>
                </v-data-table>
              </v-col>
            </v-row>
          </v-card-text>
        </v-tab-item>
      </v-tabs-items>
    </material-card>
  </v-container>
</template>

<script>
import {deleteArticle, getBlogListByPage, updateArticleState} from "../api/article";
import AppBtn from "../components/app/Btn";

export default {
  name: "ArticleTable",
  components: {AppBtn},
  data: () => ({
    tabs: 0,

    dialog: false,
    dialogDelete: false,
    /*
 headers   {
  text: string,
  value: string,
  align?: 'start' | 'center' | 'end',
  sortable?: boolean,
  filterable?: boolean,
  groupable?: boolean,
  divider?: boolean,
  class?: string | string[],
  cellClass?: string | string[],
  width?: string | number,
  filter?: (value: any, search: string, item: any) => boolean,
  sort?: (a: any, b: any) => number
}*/
    blogNum: 0,
    loading: true,
    currentPage: 1,
    pageSize: 10,
    totalPage: 1,
    options: {},
    searchStr: '',
    headers: [
      {
        value: 'title',
        align: 'start',
        sortable: false,
        text: '标题',
      },
      { value: 'cover', text: '封面', sortable: false},
      { value: 'category', text: '分类', sortable: false },
      { value: 'tags', text: '标签', sortable: false },
      { value: 'link', text: '链接', sortable: false },
      { value: 'viewCount', text: '浏览次数' },
      { value: 'level', text: '置顶等级' },
      { value: 'enableComment', text: '开放评论', sortable: true },
      //{ value: 'isDraft', text: '作为草稿', sortable: false },
      { value: 'createTime', text: '发布日期' },
      { value: 'updateTime', text: '更新日期' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    /*  文本编辑器  */
    contentMd: '',
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
    /*  文本编辑器  */
    blogList: [],
    editedIndex: -1,
    editedItem: {
      articleSource: '',
      author: '',
      category: '',
      categoryUid: '',
      commentCount: 0,
      content: '',
      contentMd: '',
      coverUid: '',
      coverUrl: '',
      createTime: '',
      enableComment: '',
      isDraft: '',
      isOriginal: '',
      lang: 0,
      level: 0,
      link: '',
      outsideLink: '',
      sort: '',
      summary: '',
      tagList: '',
      tagUid: '',
      title: '',
      type: '',
      uid: 0,
      updateTime: '',
      viewCount: 0,
    },
    defaultItem: { // 默认数值，取消编辑时令编辑项回复原本数值
      articleSource: '',
      author: '',
      category: '',
      categoryUid: '',
      commentCount: 0,
      content: '',
      contentMd: '',
      coverUid: '',
      coverUrl: '',
      createTime: '',
      enableComment: '',
      isDraft: '',
      isOriginal: '',
      lang: 0,
      level: 0,
      link: '',
      outsideLink: '',
      sort: '',
      summary: '',
      tagList: '',
      tagUid: '',
      title: '',
      type: '',
      uid: 0,
      updateTime: '',
      viewCount: 0,
    },
  }),
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },
  watch: {
    /*
    options{
  page: number,
  itemsPerPage: number,
  sortBy: string[],
  sortDesc: boolean[],
  groupBy: string[],
  groupDesc: boolean[],
  multiSort: boolean,
  mustSort: boolean
}*/
    //'options.itemsPerPage','options.page':
    /*
    'options.page': {
      handler() {//深度监听，可监听到对象、数组的变化
        console.log('option:');
        this.getBlogList();
      },
    },
    */
    /*
    options: {
      handler() {//深度监听，可监听到对象、数组的变化
        console.log('option:' + this.options.sortBy + ' ' + this.options.sortDesc);
        this.getBlogList();
      },
      //deep: true, // 开启深度监听
    },
    */
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  mounted () {
    this.getBlogList(false);
  },
  created () {
  },
  methods: {
    getBlogList(isDraft){
      const { sortBy, sortDesc, page, itemsPerPage } = this.options;
      let param = {};
      param.isDraft = isDraft;
      param.pageSize = itemsPerPage;
      param.currentPage = page;
      let sortMap = new Map();
      for (let i=0;i<sortBy.length;i++){
        sortMap[sortBy[i]] = sortDesc[i];
      }
      param.sortMap = sortMap;
      console.log('sort map: '+sortMap.toString());
      console.log('admin getBlogList: sortBy='+sortBy+';sortDesc='+sortDesc);
      this.loading = true
      getBlogListByPage(param).then(response => {
        if (response.state === this.$STATE.SUCCESS){
          let blogData = response.data;
          console.log(response);
          console.log(response.data.records);
          console.log(blogData.records.length);
          this.blogList = blogData.records;
          this.blogNum = blogData.records.length;
          this.totalPage = blogData.pages;
          this.pageSize = blogData.size;
          this.currentPage = blogData.current;
        }else {
          console.log(response.message);
        }
        this.loading = false
      })
    },
    changeArticleState(item, changeDraft){
      this.editedIndex = this.blogList.indexOf(item)
      let param = {};
      param.uid = item.uid;
      param.isDraft = !item.isDraft;
      param.enableComment = item.enableComment;
      //param.level = item.level;
      updateArticleState(param).then(response => {
        if (response.state === this.$STATE.SUCCESS){
          console.log(response.message);
          if (changeDraft){
            this.blogList.splice(this.editedIndex, 1)
          }
        }else {
          console.log(response.message);
        }
      })
    },

    editItem (item) {
      this.editedIndex = this.blogList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      console.log(item)
      this.contentMd = item.contentMd;
      this.dialog = true
    },
    save () {
      if (this.editedIndex > -1) {
        Object.assign(this.blogList[this.editedIndex], this.editedItem)
      } else {
        this.blogList.push(this.editedItem)
      }
      this.close()
    },
    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    deleteItem (item) {
      this.editedIndex = this.blogList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },
    deleteItemConfirm () {
      //let blog = this.blogList[this.editedIndex]
      let that = this;
      console.log('delete blog: '+that.blogList[that.editedIndex].uid+':'+this.editedItem.uid);
      console.log('edit index:'+this.editedIndex+':'+that.editedIndex);

      deleteArticle(this.editedItem.uid).then(response => {
        if (response.state === this.$STATE.SUCCESS){
          console.log(response.message);
          this.blogList.splice(this.editedIndex, 1)
          //this.blogList = that.blogList;
        }else {
          console.log(response.message);
        }
        this.closeDelete()
      })
    },
    closeDelete () {
      console.log('closeDelete');
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      });
    },
  },
}
</script>

<style scoped>

</style>
