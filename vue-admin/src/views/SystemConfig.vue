<template>
  <v-container id="system-config" fluid tag="section">
    <v-row>
      <v-col>
        <material-card full-header color="primary">
          <template #heading>
            <v-tabs v-model="currentTab" background-color="transparent" slider-color="white" class="pa-8">
              <span class="subheading font-weight-light mx-3" style="align-self: center">System Config:</span>
              <v-tab class="mr-3">
                <v-icon class="mr-2">mdi-code-tags</v-icon>
                系统
              </v-tab>
              <v-tab class="mr-3">
                <v-icon class="mr-2">mdi-code-tags</v-icon>
                邮件
              </v-tab>
              <v-tab class="mr-3">
                <v-icon class="mr-2">mdi-cloud</v-icon>
                文件
              </v-tab>
              <v-tab class="mr-3">
                <v-icon class="mr-2">mdi-cloud</v-icon>
                文件
              </v-tab>
              <!--
              <v-tab v-for="tab in tabList" :key="tab.uid" class="mr-3">
                <v-icon class="mr-2">{{ tab.icon }}</v-icon>
                {{ tab.classificationName }}
              </v-tab>
              -->
            </v-tabs>
          </template>
          <v-tabs-items v-model="currentTab" background-color="transparent">
            <v-tab-item>
              <v-card-text>
                <v-sheet>
                  <v-row>
                    <v-radio-group v-model="imagePriority" row>
                      <template v-slot:label>
                        <div>图片源<strong>优先级</strong></div>
                      </template>
                      <v-radio value="0">
                        <template v-slot:label>
                          <div>默认 <strong class="success--text">使用本地</strong></div>
                        </template>
                      </v-radio>
                      <v-radio value="1">
                        <template v-slot:label>
                          <div><strong class="primary--text">Github</strong></div>
                        </template>
                      </v-radio>
                    </v-radio-group>
                  </v-row>
                  <v-row>
                    <v-switch
                      v-model="enableEmailNotification"
                      inset
                      :label="`开启邮件提醒: ${enableEmailNotification}`"
                    ></v-switch>
                  </v-row>
                  <v-row></v-row>
                </v-sheet>
              </v-card-text>
            </v-tab-item>
            <v-tab-item>
              <v-card-text>
                <v-sheet>
                  <v-text-field
                    v-model="smtpPort"
                    outlined
                    label="SMTP 端口"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>
                  <v-text-field
                    v-model="smtpAddress"
                    outlined
                    label="SMTP 地址"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>
                  <v-text-field
                    v-model="email"
                    outlined
                    label="邮箱地址"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>
                  <v-text-field
                    v-model="emailPassword"
                    outlined
                    label="邮箱密码"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>
                  <v-text-field
                    v-model="emailSender"
                    outlined
                    label="发件人"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>
                </v-sheet>
              </v-card-text>
            </v-tab-item>
            <v-tab-item>
              <v-card-text>
                <v-sheet>
                  <v-radio-group v-model="imagePriority">
                    <template v-slot:label>
                      <div>Your favourite <strong>search engine</strong></div>
                    </template>
                    <v-radio value="Google">
                      <template v-slot:label>
                        <div>Of course it's <strong class="success--text">Google</strong></div>
                      </template>
                    </v-radio>
                    <v-radio value="Duckduckgo">
                      <template v-slot:label>
                        <div>Definitely <strong class="primary--text">Duckduckgo</strong></div>
                      </template>
                    </v-radio>
                  </v-radio-group>
                </v-sheet>
              </v-card-text>
            </v-tab-item>
            <v-tab-item>
              <v-card-text>
                <v-sheet>
                  <v-switch
                    v-model="uploadLocal"
                    inset
                    :label="`上传文件时在本地储存: ${uploadLocal}`"
                  ></v-switch>
                  <v-text-field
                    v-model="localImageBaseUrl"
                    outlined
                    label="本地图片URL"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>

                  <v-switch
                    v-model="uploadGithub"
                    inset
                    :label="`上传文件时在GitHub备份(超过50m时应另起仓库): ${uploadGithub}`"
                  ></v-switch>
                  <v-text-field
                    v-model="jsdelivrImageBaseUrl"
                    outlined
                    label="Github Base URL"
                    prepend-icon="mdi-map-marker"
                  ></v-text-field>
                </v-sheet>
              </v-card-text>
            </v-tab-item>
          </v-tabs-items>
          <v-card-actions>
            <app-btn
              @click="handleSubmit"
              large
              rel="noopener noreferrer"
              target="_blank"
              class="align-content-center"
              max-width="auto"
            >
              <v-icon left>mdi-playlist-check</v-icon>
              <span>保存</span>
            </app-btn>
          </v-card-actions>
        </material-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import MaterialCard from "../components/MaterialCard";
import {getSystemConfig, updateSystemConfig} from "../api/system";
export default {
  name: "SystemConfig",
  components: {MaterialCard},
  data: () => ({
    currentTab: 0,

    uid: null,
    smtpPort: '40',
    smtpAddress: '127.0.0.1',
    email: '1359643068@qq.com',
    emailSender: 'Vincent',
    emailPassword: '123456',

    uploadLocal: true,
    uploadGithub: true,
    imagePriority: 0,
    localImageBaseUrl: '',
    jsdelivrImageBaseUrl: '',
    enableEmailNotification: true,
  }),
  methods: {
    handleSubmit(){
      let param = {};
      param.uid = this.uid;
      param.smtpPort=this.smtpPort;
      param.smtpAddress=this.smtpAddress;
      param.email=this.email;
      param.emailSender=this.emailSender;
      param.emailPassword=this.emailPassword;

      param.uploadLocal=this.uploadLocal;
      param.uploadGithub=this.uploadGithub;
      param.imagePriority=this.imagePriority;
      param.localImageBaseUrl=this.localImageBaseUrl;
      param.jsdelivrImageBaseUrl=this.jsdelivrImageBaseUrl;
      param.enableEmailNotification=this.enableEmailNotification;
      updateSystemConfig(param).then(response=>{
        if (response.state === this.$STATE.SUCCESS){
          alert(response.message);
          //this.contentMd = '';
          //this.$message.show(response.data.message);
          //this.$message.success(response.data.message);
        }else {
          //this.$message.error(response.data.message);
          alert(response.message);
        }
      });
    }

  },
  computed: {

  },
  watch: {
    imagePriority: {
      handler(){
        console.log('imagePriority: '+ this.imagePriority);
      }
    }
  },
  created() {
    getSystemConfig().then(response => {
      let config = response.data;
      console.log(response.data);
      //console.log(response.data.data.records);
      //console.log(config.records.length);
      //this.imageList = blogData.records;
      //this.blogNum = blogData.records.length;
      //this.totalPage = blogData.pages;
      //this.loading = false
      this.uid = config.uid;
      this.smtpPort=config.smtpPort;
      this.smtpAddress=config.smtpAddress;
      this.email=config.email;
      this.emailSender=config.emailSender;
      this.emailPassword=config.emailPassword;

      this.uploadLocal=config.uploadLocal;
      this.uploadGithub=config.uploadGithub;
      this.imagePriority=config.imagePriority;
      this.localImageBaseUrl=config.localImageBaseUrl;
      this.jsdelivrImageBaseUrl=config.jsdelivrImageBaseUrl;
      this.enableEmailNotification=config.enableEmailNotification;
    })
  }
}
</script>

<style scoped>

</style>
