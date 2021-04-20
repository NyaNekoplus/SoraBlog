package com.vincent.admin.service;

import com.vincent.admin.entity.FileClassification;
import com.vincent.admin.entity.SystemConfig;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Vincent Tsai
 * @date 2021/4/20 12:49
 */
public interface JsDelivrFileService {

    String saveFile(String fileLocalPath);
}
