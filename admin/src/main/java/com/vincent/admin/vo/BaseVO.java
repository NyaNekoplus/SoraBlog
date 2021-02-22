package com.vincent.admin.vo;

import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/1/24 14:38
 */

@Data
public class BaseVO<T> extends Pagination<T> {

    private Long id;

}
