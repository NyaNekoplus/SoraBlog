package com.vincent.admin;

import com.vincent.admin.entity.Comment;
import com.vincent.admin.service.CommentService;
import com.vincent.admin.service.RecordService;
import com.vincent.admin.util.IpUtil;
import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
@Slf4j
@SpringBootTest
class AdminApplicationTests {

    @Autowired
    RecordService recordService;
    @Autowired
    CommentService commentService;
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testObj() throws Exception{
        String picGoUrl = "http://127.0.0.1:36677/upload";
        String data = "{list: ['C:\\Users\\Kasugano Sora\\Desktop\\Reimu\\reimon\\34631407_p0.jpg']}";
        Map<String,Object> map = new HashMap<>();
        List<String> imgPath = new ArrayList<>();
        imgPath.add("C:\\Users\\Kasugano Sora\\Desktop\\Reimu\\reimon\\34631407_p0.jpg");
        map.put("list",imgPath);
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(map);
        //restTemplate.post
        Map json = restTemplate.postForObject(picGoUrl,request, Map.class);
        log.info("success:"+ json.get("success").getClass());
        log.info("result:"+ json.get("result").getClass());
        System.out.println("上传至GitHub："+json);
    }
    @Test
    void testDate(){
        Date date =new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("percent time： "+df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println("Date: "+date.toString());
        System.out.println(date);
    }



    @Test
    void testFindComment(){
        System.out.println("+++++:  "+recordService.getVisitorCountOfToday());
    }

}
