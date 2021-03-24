package com.vincent.admin.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/1/20 14:52
 */
public class JsonUtil {

    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,Object> jsonToMap(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            TypeReference<Map<String,Object>> type = new TypeReference<>() {};
            return mapper.readValue(json,type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
