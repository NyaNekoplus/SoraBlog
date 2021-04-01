package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.FileClassification;
import com.vincent.admin.mapper.FileClassificationMapper;
import com.vincent.admin.service.FileClassificationService;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Tsai
 * @date 2021/3/26 10:57
 */
@Service
public class FileClassificationServiceImpl extends ServiceImpl<FileClassificationMapper, FileClassification> implements FileClassificationService {
}
