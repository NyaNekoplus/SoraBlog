package com.vincent.admin.service.impl;

import com.vincent.admin.entity.FileClassification;
import com.vincent.admin.service.LocalFileService;
import com.vincent.admin.util.DateUtil;
import com.vincent.admin.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author Vincent Tsai
 * @date 2021/3/26 10:04
 */
@Slf4j
@Service
public class LocalFileServiceImpl implements LocalFileService {
    @Value(value = "${file.upload-folder}")
    private String path;

    @Override
    public String saveFile(MultipartFile multipartFile, FileClassification classification) throws IOException {
        String url = classification.getUrl();
        if (StringUtils.isEmpty(url)){
            url = "/home/images";
        }
        String oldName = multipartFile.getOriginalFilename();
        String extension = FileUtil.getFileExtension(oldName);

        /*
//获得系统的时间，单位为毫秒,转换为秒
        long totalMilliSeconds = System.currentTimeMillis();
        long totalSeconds = totalMilliSeconds / 1000;

        //求出现在的秒
        long currentSecond = totalSeconds % 60;

        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;

        //求出现在的小时
        long totalHour = totalMinutes / 60;
        long currentHour = totalHour % 24;

        //显示时间
        log.info("总毫秒为： " + totalMilliSeconds);
        log.info(currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");
*/
        /*
        Boolean windows = true;
        String backSlash = "/";
        if (windows){
            backSlash = "\\";
        }
         */

        String newName = System.currentTimeMillis() + "." + extension;
        String newPath = path + url + "/" + extension + "/" + DateUtil.getYears() + "/" + DateUtil.getMonth() + "/" + DateUtil.getDay() + "/";

        String imgurl = url + "/" + extension + "/" + DateUtil.getYears() + "/" + DateUtil.getMonth() + "/" + DateUtil.getDay() + "/" + newName;
        String saveUrl = newPath + newName;

        log.info("Upload file path: " + newPath);
        log.info("Upload file: image url: " + imgurl);

        // 保存本地，创建目录
        java.io.File file1 = new java.io.File(newPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        java.io.File saveFile = new java.io.File(saveUrl);
        // 序列化文件到本地
        saveFile.createNewFile();
        multipartFile.transferTo(saveFile);
        return imgurl;
    }
}
