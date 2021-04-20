<template>
  <v-container id="user-profile-view" fluid tag="section">
    <material-card
      icon="mdi-clipboard-text"
      icon-small
      title="Simple Table"
      color="accent"
    >
      <v-data-table
        :headers="headers"
        :items="classificationList"
        sort-by="calories"
        class="elevation-1"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>图片分类</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="500px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">New Item</v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.projectName" label="Project Name"></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.classificationName" label="Classification Name"></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.url" label="Url"></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                  <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="dialogDelete" max-width="500px">
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
        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
        <template v-slot:no-data>
          <v-btn color="primary" @click="initialize">Reset</v-btn>
        </template>
      </v-data-table>
    </material-card>
  </v-container>
</template>

<script>
import {
  getImageClassification,
  addImageClassification,
  deleteImageClassification,
  updateImageClassification
} from '../api/file';
import MaterialCard from "../components/MaterialCard";
import DefaultSearch from "../layouts/default/widgets/Search";
export default {
  name: "ImageClassification",
  components: {DefaultSearch, MaterialCard},
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {
        text: 'ID',
        align: 'start',
        sortable: false,
        value: 'uid',
      },
      { text: 'Project name', value: 'projectName' },
      { text: 'Classification name', value: 'classificationName' },
      { text: 'Url', value: 'url' },
      { text: 'Create Time', value: 'createTime' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    classificationList: [
      {
        uid: 'Frozen Yogurt',
        projectName: '',
        classificationName: '',
        url: '',
        createTime: '2021-04-12 10:05:00',
      },
    ],
    editedIndex: -1,
    editedItem: {
      uid: '',
      projectName: 'sora-admin',
      classificationName: '',
      url: '',
      createTime: '2021-04-12 10:05:00',
    },
    defaultItem: {
      uid: '',
      projectName: '',
      classificationName: '',
      url: '',
      createTime: '2021-04-12 10:05:00',
    },

    loading: false,
    pageSize: 10,
    currentPage: 1,
    currentTab: Number,

    //icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],
    transparent: 'rgba(255, 255, 255, 0)',
  }),
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
  },
  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },

  methods: {
    editItem (item) {
      this.editedIndex = this.classificationList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      this.editedIndex = this.classificationList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      deleteImageClassification(this.editedItem.uid).then(response => {
        if (response.state === this.$STATE.SUCCESS){
          console.log(response.message);
          this.classificationList.splice(this.editedIndex, 1)
        }else {
          console.log(response.message);
        }
      })
      this.closeDelete()
    },

    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    save () {
      if (this.editedIndex > -1) { // 更新
        let i = this.editedIndex;
        updateImageClassification(this.editedItem).then(response => {
          if (response.state === this.$STATE.SUCCESS){
            console.log(response.message);
            Object.assign(this.classificationList[i], response.data)
          }else {
            console.log(response.message);
          }
        })
      } else { // 新增
        addImageClassification(this.editedItem).then(response => {
          if (response.state === this.$STATE.SUCCESS){
            console.log(response.data);
            this.classificationList.push(response.data);
          }else {
            console.log(response.message);
          }
        })
      }
      this.close()
    },
  },
  mounted() {
    getImageClassification().then(response => {
      console.log('Classification List: '+response.data);
      this.classificationList = response.data;
      //this.getImagesByClassification(response.data.data[0])
    });
  },
  created() {

    //this.getImagesByClassification({name:'jsdeliv',pageSize:this.pageSize,currentPage:1});
  }
}
</script>

<style scoped>

.v-card {
  transition: opacity .4s ease-in-out;
}


.show-btns {
  color: rgba(255, 255, 255, 1) !important;
}

</style>
