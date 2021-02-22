<template>
  <v-container
    id="user-profile-view"
    fluid
    tag="section"
  >
    <material-card
      color="accent"
      full-header
    >
      <template #heading>
        <v-tabs
          v-model="tabs"
          background-color="transparent"
          slider-color="white"
          class="pa-8"
        >
          <span
            class="subheading font-weight-light mx-3"
            style="align-self: center"
          >Tasks:</span>
          <v-tab class="mr-3">
            <v-icon class="mr-2">
              mdi-bug
            </v-icon>
            Bugs
          </v-tab>
          <v-tab class="mr-3">
            <v-icon class="mr-2">
              mdi-code-tags
            </v-icon>
            Website
          </v-tab>
          <v-tab>
            <v-icon class="mr-2">
              mdi-cloud
            </v-icon>
            Server
          </v-tab>
        </v-tabs>
      </template>
      <v-tabs-items
        v-model="tabs"
        background-color="transparent"
      >
        <v-tab-item
          v-for="n in 3"
          :key="n"
        >
          <v-card-text>
            <v-row
              align="center"
            >
              <v-col cols="12">
                <v-data-table
                  :headers="blogProps"
                  :items="blogList"
                  :options.sync="options"
                  :server-items-length="blogNum"
                  :loading="loading"
                  calculate-widths
                  class="elevation-1"
                >
                  <template v-slot:item.isDraft="{ item }">
                    <v-switch
                      v-model="item.isDraft"
                      inset
                    ></v-switch>
                  </template>
                  <template v-slot:item.enableComment="{ item }">
                    <v-switch
                      v-model="item.enableComment"
                      inset
                    ></v-switch>
                  </template>
                  <template v-slot:item.isTop="{ item }">
                    <v-switch
                      v-model="item.isTop"
                      inset
                    ></v-switch>
                  </template>
                  <template v-slot:item.actions="{ item }">
                    <app-btn icon text elevation="1" min-width="0" class="px-2 ml-1">
                      <v-icon>
                        mdi-file-edit-outline
                      </v-icon>
                    </app-btn>
                    <app-btn icon text elevation="1" min-width="0" class="px-2 ml-1">
                      <v-icon
                        v-if="tabs===0"
                        @click="deleteItem(item)"
                      >
                        mdi-eye
                      </v-icon>
                      <v-icon
                        v-else
                        @click="deleteItem(item)"
                      >
                        mdi-eye-off
                      </v-icon>
                    </app-btn>
                    <app-btn icon text elevation="1" min-width="0" class="px-2 ml-1">
                      <v-icon
                        @click="deleteItem(item)"
                      >
                        mdi-trash-can-outline
                      </v-icon>
                    </app-btn>
                  </template>
                  <template v-slot:no-data>
                    <v-btn
                      color="primary"
                      @click="initialize"
                    >
                      Reset
                    </v-btn>
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
import {getBlogListByPage} from "../api/article";
import AppBtn from "../components/app/Btn";

export default {
  name: "ArticleTable",
  components: {AppBtn},
  data: () => ({
    tabs: 0,
    tasks: {
      0: [
        {
          text: 'Sign contract for "What are conference organizers afraid of?"',
          value: true,
        },
        {
          text: 'Lines From Great Russian Literature? Or E-mails From My Boss?',
          value: false,
        },
        {
          text: 'Flooded: One year later, assessing what was lost and what was found when a ravaging rain swept through metro Detroit',
          value: false,
        },
        {
          text: 'Create 4 Invisible User Experiences you Never Knew About',
          value: true,
        },
      ],
      1: [
        {
          text: 'Flooded: One year later, assessing what was lost and what was found when a ravaging rain swept through metro Detroit',
          value: true,
        },
        {
          text: 'Sign contract for "What are conference organizers afraid of?"',
          value: false,
        },
      ],
      2: [
        {
          text: 'Lines From Great Russian Literature? Or E-mails From My Boss?',
          value: false,
        },
        {
          text: 'Flooded: One year later, assessing what was lost and what was found when a ravaging rain swept through metro Detroit',
          value: true,
        },
        {
          text: 'Sign contract for "What are conference organizers afraid of?"',
          value: true,
        },
      ],
    },

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
    options: {},
    blogProps: [
      {
        value: 'title',
        align: 'start',
        sortable: false,
        text: '标题',
      },
      { value: 'category', text: '分类' },
      { value: 'tags', text: '标签' },
      { value: 'link', text: '链接' },
      { value: 'viewCount', text: '浏览次数' },
      { value: 'isTop', text: '文章置顶' },
      { value: 'enableComment', text: '开放评论' },
      { value: 'isDraft', text: '作为草稿' },
      { value: 'releaseTime', text: '发布日期' },
      { value: 'updateTime', text: '更新日期' },
      { text: 'Actions', value: 'actions', sortable: false },
    ],
    blogList: [],
    editedIndex: -1,
    editedItem: {
      name: '',
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },
    defaultItem: {
      name: '',
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },
  }),

  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
    //getOptions () {return this.options.itemsPerPage;}
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
    'options.page': {
      handler () {//深度监听，可监听到对象、数组的变化
        console.log('option:');
        this.getBlogList();
      },
      //deep: true, // 开启深度监听
    },
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },
  mounted () {
    this.getBlogList()
  },

  created () {
    //this.initialize()
  },

  methods: {
    testFunc(){
      this.$message({
        message: '成功',
        type: 'success',
        time: 50000,
      });
      console.log(this.$message);
    },
    getDataFromApi () {
      this.loading = true
      this.fakeApiCall().then(data => {
        this.desserts = data.items
        this.totalDesserts = data.total
        this.loading = false
      })
    },
    /**
     * In a real application this would be a call to fetch() or axios.get()
     */
    getBlogList(){
      const { sortBy, sortDesc, page, itemsPerPage } = this.options;
      let param = {};
      param.pageSize = itemsPerPage;
      param.currentPage = page;
      console.log(param);
      this.loading = true
      getBlogListByPage(param).then(response => {
        let blogData = response.data.data;
        console.log(response.data.data.records);
        console.log(blogData.records.length);
        this.blogList = blogData.records;
        this.blogNum = blogData.records.length;
        this.loading = false
      })
    },
    /*
    fakeApiCall () {
      return new Promise((resolve, reject) => {
        const { sortBy, sortDesc, page, itemsPerPage } = this.options

        let items = this.getDesserts()
        const total = items.length

        if (sortBy.length === 1 && sortDesc.length === 1) {//根据option处理数据
          items = items.sort((a, b) => {
            const sortA = a[sortBy[0]]
            const sortB = b[sortBy[0]]

            if (sortDesc[0]) {
              if (sortA < sortB) return 1
              if (sortA > sortB) return -1
              return 0
            } else {
              if (sortA < sortB) return -1
              if (sortA > sortB) return 1
              return 0
            }
          })
        }

        if (itemsPerPage > 0) {
          items = items.slice((page - 1) * itemsPerPage, page * itemsPerPage)
        }

        setTimeout(() => {
          resolve({
            items,
            total,
          })
        }, 1000)
      })
    },
    */
    editItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      this.desserts.splice(this.editedIndex, 1)
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
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
      } else {
        this.desserts.push(this.editedItem)
      }
      this.close()
    },
  },
}
</script>

<style scoped>

</style>
