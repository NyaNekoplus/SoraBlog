package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.SystemConfig;
import com.vincent.admin.mapper.SystemConfigMapper;
import com.vincent.admin.service.SystemConfigService;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Tsai
 * @date 2021/3/25 18:48
 */
@Service
public class SystemConfigImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {
}
