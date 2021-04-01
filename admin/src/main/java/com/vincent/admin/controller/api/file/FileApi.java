package com.vincent.admin.controller.api.file;

import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.service.FileService;
import com.vincent.admin.service.LocalFileService;
import com.vincent.admin.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 16:09
 */

@RestController
@RequestMapping("/file")
@Slf4j
public class FileApi {

    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private FileService fileService;

    @PostMapping("/images")
    public String uploadImages(HttpServletRequest request, List<MultipartFile> imageList){
        SystemConfig config = systemConfigService.getById(1);
        return fileService.batchUploadFile(request,imageList,config);
    }
}
