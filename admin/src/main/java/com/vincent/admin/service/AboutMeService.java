package com.vincent.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vincent.admin.entity.About;

/**
 * @author Vincent Tsai
 * @date 2021/4/19 19:42
 */
public interface AboutMeService extends IService<About> {

    String getAboutMeByLang(Integer lang);

    String updateAboutMe(About about);
}
