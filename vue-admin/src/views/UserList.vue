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
          <v-tab @click="getUserList(true)" class="mr-3">
            <v-icon class="mr-2">mdi-code-tags</v-icon>
            Normal
          </v-tab>
          <v-tab @click="getUserList(false)">
            <v-icon class="mr-2">mdi-cloud</v-icon>
            Silence
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
                  :items="userList"
                  :page="totalPage"
                  :items-per-page="pageSize"
                  :server-items-length="userNum"
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
                            <v-btn icon dark @click="close">
                              <v-icon>mdi-close</v-icon>
                            </v-btn>
                            <v-toolbar-title>Editing</v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-toolbar-items>
                              <v-btn dark text @click="save">Save</v-btn>
                            </v-toolbar-items>
                          </v-toolbar>
                          <v-row>
                            <v-col>
                              <material-card icon="mdi-calendar-today" icon-small title="Category" color="primary">
                                <v-card-text>
                                  <v-select
                                    v-model="editedItem.category"
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
                              <v-text-field
                                v-model="editedItem.title"
                                label="Title"
                                prepend-icon="mdi-format-title"
                              ></v-text-field>
                              <v-combobox
                                hint="置顶文章等级"
                                v-model="editedItem.level"
                                :items="levelList"
                                item-value="value"
                                item-text="text"
                                return-object
                                hide-selected
                                outlined
                                prepend-icon="mdi-format-title"
                              ></v-combobox>

                              <v-text-field
                                v-model="editedItem.link"
                                :rules="[value => !value || value[0]!=='/' || '首字符不能为/']"
                                label="Link(tooisorahe.com/link)"
                                prepend-icon="mdi-link-variant-plus"
                              ></v-text-field>
                            </v-col>
                            <v-col cols="12"><vue-tinymce v-model="editedItem.contentMd" :setting="setting" /></v-col>
                          </v-row>

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
                  <template v-slot:item.avatar="{ item }">
                    <v-img :src="item.qqAvatar?item.qqAvatar:item.avatarUrl" max-width="80" max-height="45"></v-img>
                  </template>
                  <template v-slot:item.userProxy="{ item }">
                      <v-chip>
                        {{ item.userProxy===2?'用户':item.userProxy===1?'测试':'博主' }}
                      </v-chip>
                  </template>
                  <template v-slot:item.enableComment="{ item }">
                    <v-switch v-model="item.enableComment" inset @click="updateUserState(item)"></v-switch>
                  </template>
                  <template v-slot:item.enableEmailNotification="{ item }">
                    <v-switch v-model="item.enableEmailNotification" inset @click="updateUserState(item)"></v-switch>
                  </template>
                  <template v-slot:item.loginCount="{ item }">
                    <v-chip>
                      {{ item.loginCount }}
                    </v-chip>
                  </template>
                  <template v-slot:item.actions="{ item }">
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
import {get} from "vuex-pathify";
import {updateUserState, getUserListByPage, removeUser} from "../api/user";

export default {
  name: "UserList",
  data: () => ({
    tabs: 0,

    dialog: false,
    dialogDelete: false,

    userNum: 0,
    loading: true,
    currentPage: 1,
    pageSize: 10,
    totalPage: 1,
    options: {},
    searchStr: '',
    headers: [
      {
        value: 'username',
        align: 'start',
        sortable: false,
        text: '用户名',
      },
      { value: 'avatar', text: '头像', sortable: false},
      { value: 'userProxy', text: '权限', sortable: false },
      { value: 'lastLoginIp', text: '上次登录IP', sortable: false },
      { value: 'ipSource', text: 'IP归属地', sortable: false },
      { value: 'os', text: '系统' },
      { value: 'browser', text: '浏览器' },
      { value: 'enableComment', text: '开放评论', sortable: true },
      { value: 'enableEmailNotification', text: '开启邮件', sortable: false },
      { value: 'loginCount', text: '登录次数' },
      { value: 'createTime', text: '日期' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    /*  文本编辑器  */
    contentMd: '',
    /*  文本编辑器  */
    /**/
    title: '',
    link: '',
    level: '',
    levelList: [],
    category: '',
    categoryList: [],
    userList: [],
    /**/
    editedIndex: -1,
    editedItem: {
    },
    defaultItem: { // 默认数值，取消编辑时令编辑项回复原本数值
    },
  }),
  computed: {
    setting: get('editor/setting'),
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },
  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },
  mounted () {
    this.getUserList(true);
    //this.getUserList(false);
  },
  created () {

  },
  methods: {
    getUserList(enableComment){
      const { sortBy, sortDesc, page, itemsPerPage } = this.options;
      let param = {};
      param.enableComment = enableComment;
      param.pageSize = itemsPerPage;
      param.currentPage = page;
      let sortMap = new Map();
      for (let i=0;i<sortBy.length;i++){
        sortMap[sortBy[i]] = sortDesc[i];
      }
      param.sortMap = sortMap;
      console.log('sort map: '+sortMap.toString());
      console.log('admin getUserList: sortBy='+sortBy+';sortDesc='+sortDesc);
      this.loading = true
      getUserListByPage(param).then(response => {
        console.log(response);
        if (response.state === this.$STATE.SUCCESS){
          let userData = response.data;
          console.log(response.data.records);
          console.log(userData.records.length);
          this.userList = userData.records;
          this.userNum = userData.records.length;
          this.totalPage = userData.pages;
          this.pageSize = userData.size;
          this.currentPage = userData.current;
        }
        this.loading = false
      })
    },
    updateUserState(item){
      this.editedIndex = this.userList.indexOf(item)
      let param = {};
      param.uid = item.uid
      param.enableComment = item.enableComment
      param.enableEmailNotification = item.enableEmailNotification
      console.log(param)
      updateUserState(param).then(response => {
        console.log(response.message);
        if (response.state === this.$STATE.SUCCESS){
          if (!item.enableComment){
            this.userList.splice(this.editedIndex, 1)
          }
        }
      })
    },

    editItem (item) {
      this.editedIndex = this.userList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      console.log(item)
      // vue-tinymce问题，更新content后不同步。将于1.1.1版本修复
      this.contentMd = item.contentMd;
      this.dialog = true
      this.$nextTick(() => {
        tinymce.activeEditor.setContent(item.contentMd);
      })
    },
    save () {
      if (this.editedIndex > -1) {
        Object.assign(this.userList[this.editedIndex], this.editedItem)
        console.log("save: "+this.editedItem.link+this.editedItem.content)
        this.updateUserState(this.editedItem)
      } else {
        this.userList.push(this.editedItem)
      }
      this.close()
    },
    close () {
      this.dialog = false
      this.contentMd = '';
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    deleteItem (item) {
      this.editedIndex = this.userList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },
    deleteItemConfirm () {
      let that = this;
      console.log('delete user: '+that.userList[that.editedIndex].uid+':'+this.editedItem.uid);
      console.log('edit index:'+this.editedIndex+':'+that.editedIndex);

      removeUser(this.editedItem.uid).then(response => {
        if (response.state === this.$STATE.SUCCESS){
          console.log(response.message);
          this.userList.splice(this.editedIndex, 1)
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
