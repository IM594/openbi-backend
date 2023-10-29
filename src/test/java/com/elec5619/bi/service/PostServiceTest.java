package com.elec5619.bi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elec5619.bi.model.dto.post.PostQueryRequest;
import com.elec5619.bi.model.entity.Post;

import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子服务测试
 *
 * @author Zhaohao Lu
 */
@SpringBootTest
class PostServiceTest {

    @Resource
    private PostService postService;


}
