package com.vincent.admin.util;

import java.util.HashMap;

/**
 * @author Vincent Tsai
 * @date 2021/1/20 14:47
 */

public class Result {

    final static String STATE   = "state";
    final static String SUCCESS = "success";
    final static String FAILURE = "failure";
    final static String MESSAGE = "message";
    final static String DATA    = "data";


    public static String success(){
        return success(null);
    }
    public static String success(String message){
        return success(message,null);
    }
    public static String success(String message, Object data){
        HashMap<Object,Object> map = new HashMap<>(2);
        map.put(STATE,SUCCESS);
        map.put(DATA,data);

        map.put(MESSAGE,message);
        return JsonUtil.objectToJson(map);
    }

    public static String failure(){
        return failure(null);
    }
    public static String failure(String message){
        return failure(message,null);
    }
    public static String failure(String message, Object data){
        HashMap<Object,Object> map = new HashMap<>(2);
        map.put(STATE,FAILURE);
        map.put(DATA,data);

        map.put(MESSAGE,message);
        return JsonUtil.objectToJson(map);
    }
}
