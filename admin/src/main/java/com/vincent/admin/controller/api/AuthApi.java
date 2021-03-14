package com.vincent.admin.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Tsai
 * @date 2021/3/13 16:29
 */
//authenticate

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthApi {

    @GetMapping("/verify/{token}") // 验证token是否仍有效
    String loginAuthenticate(@PathVariable("token") String token){
        return null;
    }
}
