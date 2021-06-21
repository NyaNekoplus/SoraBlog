package com.vincent.admin.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Vincent Tsai
 * @date 2021/1/20 14:52
 */
public class JsonUtil {

    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = mapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            T t = mapper.readValue(jsonData, beanType);
            return t;
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

    public static Map<String, Object> objectToMap(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            String json = mapper.writeValueAsString(obj);
            TypeReference<Map<String,Object>> type = new TypeReference<>() {};
            return mapper.readValue(json,type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
