package com.elec5619.bi.controller;

import com.elec5619.bi.common.BaseResponse;
import com.elec5619.bi.common.ErrorCode;
import com.elec5619.bi.common.ResultUtils;
import com.elec5619.bi.exception.BusinessException;
import com.elec5619.bi.model.dto.postthumb.PostThumbAddRequest;
import com.elec5619.bi.model.entity.User;
import com.elec5619.bi.service.PostThumbService;
import com.elec5619.bi.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/post_thumb")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddRequest postThumbAddRequest,
                                         HttpServletRequest request) {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.doPostThumb(postId, loginUser);
        return ResultUtils.success(result);
    }

    @PostMapping("/search_thumb")
    public BaseResponse<Integer> searchThumb(@RequestBody Map<String, Object> json){
        long postId = Long.parseLong((String) json.get("postId"));
        long userId = Long.parseLong((String) json.get("userId"));
        int result = postThumbService.searchThumb(postId, userId);
        if (result == 1){
            return ResultUtils.success(result);
        }else {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }

    }

}
