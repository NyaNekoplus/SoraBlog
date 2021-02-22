package com.vincent.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Vincent Tsai
 * @date 2021/2/16 15:23
 */

@Data
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

}
