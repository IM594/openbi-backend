package com.elec5619.bi.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec5619.bi.model.entity.Post;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 帖子数据库操作
 *
 * @author Zhaohao Lu
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 查询帖子列表（包括已被删除的数据）
     */
    List<Post> listPostWithDelete(Date minUpdateTime);

    Post searchById(BigInteger id);

    List<Post> searchPostByPage(Page<Object> objectPage, QueryWrapper<Post> queryWrapper, String keyword);
}




