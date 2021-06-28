/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/19 13:28
 */

// Pathify
import { make } from 'vuex-pathify'

const state = {
  setting: {
    //menubar: 'file edit insert view format table tools help',
    menubar: false,
    toolbar: "undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | codesample | code | kityformula-editor | anchor | preview |",
    toolbar_mode: "floating",
    quickbars_selection_toolbar: "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor",
    plugins: "link image media table lists fullscreen quickbars codesample code kityformula-editor preview toc anchor",

    inline: false,

    language: 'zh_CN', //本地化设置
    branding: false,
    statusbar: false,
    skin: 'oxide',
    height: '70vh',

    //images_upload_url: process.env.VUE_APP_FILE_API + '/images',
    images_upload_base_path: '/admin', //如果后端返回的图片地址是相对地址
    images_upload_handler: function (blobInfo, success, failure, progress) {
      let xhr, formData;
      xhr = new XMLHttpRequest();
      xhr.withCredentials = false;
      xhr.open('POST', process.env.VUE_APP_FILE_API + '/images');

      xhr.upload.onprogress = function(e){
        progress(e.loaded / e.total * 100);
      }

      xhr.onload = function() {
        let json;
        if (xhr.status === 403) {
          failure('HTTP Error: ' + xhr.status, { remove: true });
          return;
        }
        if (xhr.status < 200 || xhr.status >= 300 ) {
          failure('HTTP Error: ' + xhr.status);
          return;
        }
        json = JSON.parse(xhr.responseText);
        if (!json) { // || typeof json.data != 'string'
          failure('Invalid JSON: ' + xhr.responseText);
          return;
        }
        success(process.env.VUE_APP_IMAGE+json.data[0].url);
      };

      xhr.onerror = function () {
        failure('Image upload failed due to a XHR Transport error. Code: ' + xhr.status);
      }

      formData = new FormData();
      let imgConfig =  {
        userUid: 1,
        projectName: 'sora-admin',
        classificationName: 'blog-image'
      }
      for (let key in imgConfig){formData.append(key,imgConfig[key]);}
      formData.append('imageList', blobInfo.blob(), blobInfo.filename());

      xhr.send(formData);
    }
    //toc_depth: 3,
    //toc_class: 'toc-container',
    //toc_header: 'div'

  },
}

const mutations = make.mutations(state)

const actions = {}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
