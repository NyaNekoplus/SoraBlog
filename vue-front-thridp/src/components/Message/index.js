/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/23 11:42
 */
export function message(msg){
    let msgWrap = document.createElement('div');
    msgWrap.classList.add('butterBar','butterBar--center')
    msgWrap.innerHTML = '<div class="butterBar-message">'+msg+'</div>'
    document.body.appendChild(msgWrap)
    setTimeout(()=>{
        document.body.removeChild(msgWrap);
    },4000)
}