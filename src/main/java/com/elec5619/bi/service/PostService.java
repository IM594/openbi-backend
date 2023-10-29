package com.elec5619.bi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec5619.bi.model.dto.post.PostQueryRequest;
import com.elec5619.bi.model.entity.Post;
import com.elec5619.bi.model.vo.PostVO;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务
 *
 * @author Zhaohao Lu
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     * @param postQueryRequest
     * @return
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param post
     * @param request
     * @return
     * @description 看当前登录用户是否点赞收藏指定id的帖子
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @param request
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);

    int getLikesCount(long postId);


    Page<Post> searchPostByPage(Page<Object> objectPage, QueryWrapper<Post> queryWrapper, String keyword);
}
