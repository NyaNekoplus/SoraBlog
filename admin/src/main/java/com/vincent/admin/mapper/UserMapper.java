package com.vincent.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/1/30 11:20
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> getUserByPageWithAvatar(Page<User> page, @Param("enableComment") Boolean enableComment);
}
