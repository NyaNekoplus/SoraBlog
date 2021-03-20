package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.Link;
import com.vincent.admin.mapper.LinkMapper;
import com.vincent.admin.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 18:47
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
}
