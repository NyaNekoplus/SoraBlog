<template>
  <v-container id="user-profile-view" fluid tag="section">
    <v-row justify="center">
      <v-col cols="12" md="8">
        <material-card color="primary" icon="mdi-account-outline">
          <template #title>
            Edit Profile — <small class="text-body-1">Complete your profile</small>
          </template>

          <v-form>
            <v-container class="py-0">
              <v-row>
                <v-col cols="12">
                  <vue-tinymce v-model="about.detail" :setting="setting"/>
                </v-col>


                <v-col cols="12" class="text-right">
                  <v-btn @click="updateAboutMe" color="primary" min-width="150">
                    Update Profile
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </material-card>
      </v-col>

      <v-col cols="12" md="4">
        <app-card class="mt-4 text-center">
          <v-img
            class="rounded-circle elevation-6 mt-n12 d-inline-block"
            :src="about.avatarUrl"
            width="128"
          />

          <v-card-text class="text-center">
            <h6 class="text-h6 mb-2 text--secondary">
              CEO / FOUNDER
            </h6>

            <h4 class="text-h4 mb-3 text--primary">
              {{ about.name }}
            </h4>

            <p class="text--secondary">
              {{about.motto}}
              <!--
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ratione dolorem deserunt veniam tempora magnam
              quisquam quam error iusto cupiditate ducimus, et eligendi saepe voluptatibus assumenda similique
              temporibus placeat animi dicta?
              -->
            </p>

            <v-btn
              class="mr-0"
              color="primary"
              min-width="100"
              rounded
            >
              Follow
            </v-btn>
          </v-card-text>
        </app-card>
      </v-col>
    </v-row>


  </v-container>
</template>

<script>
import {get} from 'vuex-pathify'
import {getAboutMe, updateAboutMe} from "../api/user";
import {abort} from "../util/routes";
export default {
  name: 'UserProfileView',
  data: () => ({
    about: {},
    name: '',
    avatar: '',
    motto: '',
    contentMd: '<h1 style="text-align: center;">大丈夫生于天地之间,岂能郁郁久居人下!</h1>春宵苦短日高起，从此君王不早朝。<br />在天愿作比翼鸟，在地愿为连理枝。<br />天长地久有时尽，此恨绵绵无绝期。</p>',

    lang: 1,
  }),
  methods: {
    updateAboutMe(){
      console.log(this.about);
      updateAboutMe(this.about).then(response => {
        console.log(response.message);
      })
    }
  },
  computed: {
    setting: get('editor/setting'),

  },
  created() {
    getAboutMe().then(response => {
      if (response.state === this.$STATE.SUCCESS){
        let data = response.data;
        this.about = data;
      }else{
        console.log(response.message);
      }
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
