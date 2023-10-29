package com.elec5619.bi.mapper;

import com.elec5619.bi.model.entity.Post;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子数据库操作测试
 *
 * @author Zhaohao Lu
 */
@SpringBootTest
class PostMapperTest {

    @Resource
    private PostMapper postMapper;

    @Test
    void listPostWithDelete() {
        List<Post> postList = postMapper.listPostWithDelete(new Date());
        Assertions.assertNotNull(postList);
    }

    @Test
    void searchById(){
        BigInteger bigInteger = new BigInteger("1718574207740231682");
        Post post = postMapper.searchById(bigInteger);
        System.out.println(post);
    }
}
