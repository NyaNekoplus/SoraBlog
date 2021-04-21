package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.About;
import com.vincent.admin.mapper.AboutMeMapper;
import com.vincent.admin.service.AboutMeService;
import com.vincent.admin.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Tsai
 * @date 2021/4/19 19:43
 */
@Service
public class AboutMeServiceImpl extends ServiceImpl<AboutMeMapper, About> implements AboutMeService {

    @Override
    public String getAboutMeByLang(Integer lang) {
        About about = getOne(new QueryWrapper<About>().eq("lang",lang));
        if (about == null)
            return Result.failure("获取About me失败, 不支持这种语言");
        return Result.success("获取About me成功",about);
    }

    @Override
    public String updateAboutMe(About about) {
        return saveOrUpdate(about)?Result.success("更新about me成功"):Result.failure("更新about me失败");
    }
}
