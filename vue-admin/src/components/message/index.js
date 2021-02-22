/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/18 11:41
 */

import vue from 'vue'
import Main from './Message'

// 创建vue组件实例
const MessageConstructor = vue.extend(Main);
//创建一个存放通知的div


//添加通知节点(用来存放通知的元素)
let msgWrap = document.createElement('div');
//msgWrap.className = "notify-wrap"
msgWrap.style = "width:10rem; height: 80px;position: fixed; right: 0px; top: 90px; transition-duration: .5s;"
document.body.appendChild(msgWrap);

// {content, type, time = 1500,}
const Message = (options) => {
  options = options || {};
  if (typeof options === 'string') { // 仅有一个字符串参数
    options = {
      message: options,
      //color: color,
      //icon: icon,
      //typeClass: typeClass
    };
  }
  const notifyDom = new MessageConstructor({
    //el: document.createElement('div'),
    //data: options,
    data: options,

  });

  document.addEventListener('DOMContentLoaded',function(){
    notifyDom.$vuetify = { breakpoint: {}}
  });
  notifyDom.$mount(document.createElement('v-alert'))
  //往notifyWrap里面添加通知
  msgWrap.appendChild(notifyDom.$el);
}


//注册
function register(){
  vue.prototype.$message = Message;
}

export default {Message,register};
