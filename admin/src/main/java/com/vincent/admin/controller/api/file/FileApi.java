package com.vincent.admin.controller.api.file;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.FileClassification;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.service.FileClassificationService;
import com.vincent.admin.service.FileService;
import com.vincent.admin.service.LocalFileService;
import com.vincent.admin.service.SystemConfigService;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.FileClassificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 16:09
 */

@RestController
@RequestMapping("/file")
@CacheConfig(cacheNames = "file")
@Slf4j
public class FileApi {

    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileClassificationService fileClassificationService;

    @GetMapping("/getImageClassification")
    @Cacheable
    public String getImageClassification(){
        List<FileClassification> list = fileClassificationService.list();
        log.info("File Classification: "+ list);
        return Result.success("查询图片分类成功",list);
    }

    @PostMapping("/getImageListByPge")
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.classificationName")
    public String getImageListByPge(@RequestBody FileClassificationVO fileClassificationVO){
        Page<File> page = new Page<>();
        page.setSize(fileClassificationVO.getPageSize());
        page.setCurrent(fileClassificationVO.getCurrentPage());
        if (fileClassificationVO.getUid()==null){
            return Result.failure("获取图片列表失败！图片分类uid为null");
        }
        QueryWrapper<File> wrapper = new QueryWrapper<>();
        wrapper.eq("file_classification_uid",fileClassificationVO.getUid());
        wrapper.orderByDesc("create_time");
        IPage<File> imageListPage = fileService.page(page,wrapper);
        return Result.success("查询图片列表成功",imageListPage);
    }

    @PostMapping("/images")
    public String uploadImages(HttpServletRequest request, List<MultipartFile> imageList){
        SystemConfig config = systemConfigService.getById(1);
        return fileService.batchUploadFile(request,imageList,config);
    }
}
