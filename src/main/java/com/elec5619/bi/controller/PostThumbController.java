package com.elec5619.bi.controller;

import com.elec5619.bi.common.BaseResponse;
import com.elec5619.bi.common.ErrorCode;
import com.elec5619.bi.common.ResultUtils;
import com.elec5619.bi.exception.BusinessException;
import com.elec5619.bi.model.dto.postthumb.PostThumbAddRequest;
import com.elec5619.bi.model.entity.User;
import com.elec5619.bi.service.EmailNotificationService;
import com.elec5619.bi.service.PostService;
import com.elec5619.bi.service.PostThumbService;
import com.elec5619.bi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 *
 * @author Zhaohao Lu
 */
@RestController
@Slf4j
public class PostThumbController {

    @Resource
    private PostThumbService postThumbService;

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @Autowired
    private EmailNotificationService emailNotificationService; // 注入邮件通知服务
    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/post_thumb")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddRequest postThumbAddRequest,
                                         HttpServletRequest request) throws MessagingException {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.doPostThumb(postId, loginUser);

        // 获取当前用户的邮箱
        String recipientEmail = loginUser.getUserEmail();
        // 检查点赞数是否达到阈值
        int likesCount = postService.getLikesCount(postId);
        if (likesCount == 5) {
            // 达到阈值，触发邮件通知
            sendEmailNotification(postId, recipientEmail);
        }


        return ResultUtils.success(result);
    }

    /**
     * 查询我是否点赞了某个帖子
     * @param postThumbAddRequest
     * @param request
     * @return
     * @throws MessagingException
     */
    @PostMapping("/search_thumb")
    public BaseResponse<Integer> searchThumb(@RequestBody PostThumbAddRequest postThumbAddRequest,
                                         HttpServletRequest request) throws MessagingException {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.searchThumb(postId, loginUser.getId());
        return ResultUtils.success(result);
    }

    private void sendEmailNotification(long postId, String email) throws MessagingException {
        // 构建邮件内容
        String subject = "Post Liked Notification";
        String content = "The post with ID " + postId + " has received 5 likes.";

        // 发送邮件通知
        emailNotificationService.sendNotificationEmail(email, subject, content);
    }
}
