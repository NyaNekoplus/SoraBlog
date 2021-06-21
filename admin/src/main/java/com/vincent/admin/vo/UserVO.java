package com.vincent.admin.vo;

import com.vincent.admin.entity.User;
import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/1/29 20:33
 */

@Data
public class UserVO extends BaseVO<User>{

    private String username;

    private Long avatarUid;

    private String code;

    private String password;

    private String qqAvatar;

    private String email;

    private String lastVisitDate;

    private String signupDate;

    private Boolean remember;

    private Boolean enableComment;

}
