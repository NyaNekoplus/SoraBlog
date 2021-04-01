package com.vincent.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.File;
import com.vincent.admin.entity.SystemConfig;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 16:55
 */
public interface FileService extends IService<File> {

    String batchUploadFile(HttpServletRequest request, List<MultipartFile> filedatas, SystemConfig systemConfig);
}
