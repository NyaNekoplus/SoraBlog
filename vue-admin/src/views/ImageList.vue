<template>
  <v-container id="user-profile-view" fluid tag="section">
    <material-card color="primary">
      <template #heading>

      </template>
      <template #bar>
        <default-search/>
        <app-btn
          @click="addClassification"
          rel="noopener noreferrer"
          target="_blank"
          class="align-content-center"
          max-width="auto"
        >
          <v-icon left>mdi-playlist-check</v-icon>
          <span>新增分类</span>
        </app-btn>
      </template>

      <v-tabs v-model="currentTab" class="pa-8" vertical>
        <span class="subheading font-weight-light mx-3" style="align-self: center">Classification:</span>
        <v-tab v-for="tab in tabList" :key="tab.uid" @click="getImagesByClassification(tab)" class="mr-3">
          <v-icon class="mr-2">{{ tab.icon }}</v-icon>
          {{ tab.classificationName }}
        </v-tab>
        <v-tabs-items v-model="currentTab">
          <v-tab-item v-for="n in tabList.length" :key="n">
            <v-card-text>
              <v-row align="center">
                <v-col v-for="image in imageList" :key="image.uid" cols="3">
                  <v-hover v-slot="{ hover }">
                    <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }">
                      <v-img :src="image.jsDelivrUrl===null?image.url:image.jsDelivrUrl" class="white--text align-end" gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)" height="300px">
                        <v-card-title :class="{ 'show-btns': hover }" :color="transparent">
                          <v-row dense no-gutters justify="space-between">
                            <v-col cols="3">
                              <div>{{image.originalName}}</div>
                            </v-col>
                            <v-spacer/>
                            <v-col cols="5" align-self="end">
                              <div style="text-align: right">
                                <v-btn
                                  v-for="(icon, index) in ['clipboard-text-outline', 'mdi-trash-can-outline', 'mdi-fast-forward']"
                                  :key="index"
                                  @click="imageOperation(index, image)"
                                  :class="{ 'show-btns': hover }"
                                  :color="transparent"
                                  icon
                                >
                                  <v-icon :class="{ 'show-btns': hover }" :color="transparent">
                                    {{ icon }}
                                  </v-icon>
                                </v-btn>
                              </div>
                            </v-col>

                          </v-row>
                        </v-card-title>
                      </v-img>
                    </v-card>
                  </v-hover>
                </v-col>
              </v-row>
            </v-card-text>
          </v-tab-item>
        </v-tabs-items>
      </v-tabs>
    </material-card>
  </v-container>
</template>

<script>
import {getImageClassification, getImageListByPge} from '../api/file';
import MaterialCard from "../components/MaterialCard";
import DefaultSearch from "../layouts/default/widgets/Search";
import {setDefaultCover} from "../api/system";
export default {
  name: "ImageList",
  components: {DefaultSearch, MaterialCard},
  data: () => ({
    loading: false,
    pageSize: 10,
    currentPage: 1,
    currentTab: Number,
    tabList: [
      /*
      {
        uid: 1,
        name: 'Local',
        icon: 'mdi-cloud',
      },
      {
        uid: 2,
        name: 'Jsdeliver',
        icon: 'mdi-code-tags',
      }
      */
    ],
    imageList: [
      /*{
        uid: 1,
        url: '',
        originalName: 'Jsdeliver',
      }*/
    ],

    //icons: ['mdi-rewind', 'mdi-play', 'mdi-fast-forward'],
    items: [
      {
        title: 'New Releases',
        text: `It's New Release Friday`,
        subtext: 'Newly released songs. Updated daily.',
        img: 'https://lorempixel.com/output/nightlife-q-c-640-480-5.jpg',
      },
      {
        title: 'Rock',
        text: 'Greatest Rock Hits',
        subtext: 'Lose yourself in rock tunes.',
        img: 'https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2100&q=80',
      },
      {
        title: 'Mellow Moods',
        text: 'Ambient Bass',
        subtext: 'Chill beats to mellow you out.',
        img: 'https://lorempixel.com/output/abstract-q-c-640-480-6.jpg',
      },
    ],
    transparent: 'rgba(255, 255, 255, 0)',
  }),
  computed: {

  },
  watch: {
    /*
    currentTab: {
      handler(){
        console.log('watch current tab: '+this.currentTab+this.tabList[this.currentTab])
        //this.getImagesByClassification(this.tabList[this.currentTab]);
      }
    }*/
  },
  methods: {
    imageOperation(i, image){
      switch (i){
        case 0:
          this.$copyText(image.url).then(function (e) {
            alert('Copied')
            console.log(e)
          }, function (e) {
            alert('Can not copy')
            console.log(e)
          })
          break;
        case 1:
          break;
        case 2://设为默认封面
          let imageUrl = image.url;
          console.log('image url: '+imageUrl);
          setDefaultCover(image).then(response => {alert(response.message)})
          break;
        default:
          console.log('image operation: 无此操作');
          break;
      }
    },
    addClassification(){

    },
    getImagesByClassification(classification){
      console.log('classification: '+ classification.uid + classification.name);
      let param = {};
      param.pageSize = this.pageSize;
      param.currentPage = this.currentPage;
      param.uid = classification.uid;
      param.classificationName = classification.classificationName;
      getImageListByPge(param).then(response => {
        let blogData = response.data;
        console.log(response.data);
        console.log(response.data.records);
        console.log(blogData.records.length);
        this.imageList = blogData.records;
        //this.blogNum = blogData.records.length;
        //this.totalPage = blogData.pages;
        this.pageSize = blogData.size;
        this.currentPage = blogData.current;
        this.loading = false
      })
    }
  },
  mounted() {
    getImageClassification().then(response => {
      console.log('Classification List: '+response.data);
      this.tabList = response.data;
      this.getImagesByClassification(response.data[0])
    });
  },
  created() {

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
