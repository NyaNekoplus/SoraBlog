package com.vincent.admin.controller.api.admin;

import com.vincent.admin.entity.About;
import com.vincent.admin.service.AboutMeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vincent Tsai
 * @date 2021/4/19 20:06
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class UserApi {
    @Autowired
    private AboutMeService aboutMeService;

    @GetMapping("/getAboutMe")
    public String getAboutMe(){
        return aboutMeService.getAboutMeByLang(1);
    }

    @PostMapping("/updateAboutMe")
    public String updateAboutMe(@RequestBody About about){
        return aboutMeService.updateAboutMe(about);
    }
}
