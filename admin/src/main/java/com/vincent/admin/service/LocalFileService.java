package com.vincent.admin.service;

import com.vincent.admin.entity.FileClassification;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Vincent Tsai
 * @date 2021/3/26 10:03
 */
public interface LocalFileService {

    String saveFile(MultipartFile multipartFile, FileClassification classification) throws IOException;
}
