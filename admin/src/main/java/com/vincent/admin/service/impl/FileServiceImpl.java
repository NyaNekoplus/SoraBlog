package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.FileClassification;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.mapper.FileMapper;
import com.vincent.admin.service.FileClassificationService;
import com.vincent.admin.service.FileService;
import com.vincent.admin.service.JsDelivrFileService;
import com.vincent.admin.service.LocalFileService;
import com.vincent.admin.util.FileUtil;
import com.vincent.admin.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 16:56
 */
@Service
@Slf4j
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
    @Autowired
    private FileClassificationService fileClassificationService;
    @Autowired
    private LocalFileService localFileService;
    @Autowired
    private JsDelivrFileService jsDelivrFileService;

    @Override
    public String batchUploadFile(HttpServletRequest request, List<MultipartFile> filedatas, SystemConfig systemConfig) {
        Boolean uploadLocal = systemConfig.getUploadLocal();
        Boolean uploadJsDelivr = systemConfig.getUploadGithub();


        // 判断来源
        String source = request.getParameter("source");
        //如果是用户上传，则包含用户uid
        String userUid = "";
        //项目名
        String projectName = "";
        //模块名
        String classificationName = "";

        userUid = request.getParameter("userUid");
        projectName = request.getParameter("projectName");
        classificationName = request.getParameter("classificationName");

        /*
        // 判断图片来源
        if (SysConf.PICTURE.equals(source)) {
            // 当从vue-mogu-web网站过来的，直接从参数中获取
            userUid = request.getParameter("userUid");
            projectName = request.getParameter("projectName");
            classificationName = request.getParameter("classificationName");
        } else if (SysConf.ADMIN.equals(source)) {
            // 当图片从mogu-admin传递过来的时候
            userUid = request.getAttribute("userUid").toString();
            projectName = request.getAttribute("projectName").toString();
            classificationName = request.getAttribute("classificationName").toString();
        } else {
            userUid = request.getParameter("userUid");
            projectName = request.getParameter("projectName");
            classificationName = request.getParameter("classificationName");
        }
        */

        //projectName现在默认sora-front
        if (StringUtils.isEmpty(projectName)) {
            projectName = "sora-front";
        }

        //TODO 检测用户上传，如果不是网站的用户就不能调用
        /*
        if (StringUtils.isEmpty(userUid)) {
            return Result.failure("请先注册再上传图片");
        }*/

        QueryWrapper<FileClassification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("classification_name", classificationName);
        queryWrapper.eq("project_name", projectName);
        List<FileClassification> fileClassificationList = fileClassificationService.list(queryWrapper);

        FileClassification fileClassification = null;
        if (fileClassificationList.size() >= 1) {
            fileClassification = fileClassificationList.get(0);
        } else {
            return Result.failure("无此分类");
        }

        if (filedatas != null && filedatas.size() > 0){
            log.info("图片文件：" + filedatas);
            List<File> fileList = new ArrayList<>();
            String localUrl = "";
            String jsDelivrUrl = null;
            for (MultipartFile filedata : filedatas){
                long fileSize = filedata.getSize();
                String originalFileName = filedata.getOriginalFilename();
                String extension = FileUtil.getFileExtension(originalFileName);
                String newFileName = System.currentTimeMillis() + "." + extension;
                try {
                    if (uploadLocal){
                        localUrl = localFileService.saveFile(filedata,fileClassification);
                    }
                    if (uploadJsDelivr && fileSize <= 1048576){ // 小于1M上传Github
                        jsDelivrUrl = jsDelivrFileService.saveFile(systemConfig.getLocalImageBaseUrl()+localUrl);
                        log.info("FileService upload jsdelivr: "+jsDelivrUrl);
                    }
                } catch (IOException | ResourceAccessException e) {
                    log.error("FileService: batchUploadFile: upload file failed.");
                    e.printStackTrace();
                }
                File file = new File();
                file.setNewName(newFileName);
                file.setOriginalName(originalFileName);
                file.setFileSize(fileSize);
                file.setFileClassificationUid(fileClassification.getUid());
                file.setExtension(FileUtil.getFileExtension(filedata.getName()));
                if (!StringUtils.isEmpty(userUid)) {
                    file.setUserUid(Long.parseLong(userUid));
                }
                file.setUrl(localUrl);
                file.setJsDelivrUrl(jsDelivrUrl);
                file.insert();
                fileList.add(file);
            }
            return Result.success("文件上传成功", fileList);
        }


        return Result.failure("文件上传失败，上传文件为空");
    }
}
