package com.vincent.admin.service.impl;

import com.vincent.admin.entity.FileClassification;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.service.JsDelivrFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Vincent Tsai
 * @date 2021/4/20 13:40
 */
@Slf4j
@Service
public class JsDelivrFileServiceImpl implements JsDelivrFileService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String saveFile(String fileLocalPath) throws ResourceAccessException {
        String picGoUrl = "http://127.0.0.1:36677/upload";
        Map<String,Object> map = new HashMap<>();
        List<String> imgPath = new ArrayList<>();
        imgPath.add(fileLocalPath);
        map.put("list",imgPath);
        HttpEntity<Map<String,Object>> request = new HttpEntity<>(map);
        Map json = restTemplate.postForObject(picGoUrl,request, Map.class);

        if (json == null){
            return null;
        }
        log.info("上传至GitHub："+json);
        log.info("success:"+ json.get("success").getClass());
        log.info("result:"+ json.get("result").getClass());
        if (((boolean) json.get("success"))){
            String url = ((List<String>) json.get("result")).get(0);
            if (StringUtils.isEmpty(url))
                return null;
            return url;
        }
        return null;
    }
}
