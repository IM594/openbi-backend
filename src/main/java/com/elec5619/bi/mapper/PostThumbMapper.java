package com.elec5619.bi.mapper;

import com.elec5619.bi.model.entity.Post;
import com.elec5619.bi.model.entity.PostThumb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 帖子点赞数据库操作
 *
 * @author Zhaohao Lu
 */
@Repository
public interface PostThumbMapper extends BaseMapper<PostThumb> {

    PostThumb searchThumb(long postId, long userId);

}




