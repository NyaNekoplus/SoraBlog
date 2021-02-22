export function setCookie(name, value, time){
    let date = new Date();
    date.setDate(date.getDate()+time);
    document.cookie = name + '=' + value +';expires=' +date;
}

export function getCookie(name){
    let reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    let arr = document.cookie.match(reg);
    if (arr){
        return decodeURI(arr[2]);
    }else {
        return null;
    }
}

export function removeCookie(name){
    let expire = new Date();
    expire.setTime(expire.getTime()-1);
    let v = getCookie(name);
    if (v!=null){
        document.cookie = name + "=" + v + ";expires=" + expire.toUTCString();
    }
}