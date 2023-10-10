package com.elec5619.bi.service;

import com.elec5619.bi.model.entity.PostThumb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elec5619.bi.model.entity.User;

/**
 * 帖子点赞服务
 *
 * @author Zhaohao Lu
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostThumb(long postId, User loginUser);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);

    int searchThumb(long postId, long userId);
}
