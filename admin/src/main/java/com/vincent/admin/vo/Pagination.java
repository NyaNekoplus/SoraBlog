package com.vincent.admin.vo;

import lombok.Data;

/**
 * @author Vincent Tsai
 * @date 2021/1/24 14:43
 */

@Data
public class Pagination<T> {

    private Integer currentPage;

    // 分页大小
    private Integer pageSize;
}
