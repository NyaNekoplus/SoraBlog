package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.Tag;
import com.vincent.admin.mapper.TagMapper;
import com.vincent.admin.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 18:49
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
