package com.vincent.admin.vo;

import com.vincent.admin.entity.BlogUser;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/1/29 20:33
 */

@Data
public class UserVO extends BaseVO<BlogUser>{

    private String userName;

    private String nickName;

    private String password;

    private String email;

    private String lastVisitDate;

    private String signupDate;
}
