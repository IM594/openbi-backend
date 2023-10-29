package com.elec5619.bi.controller;

import com.elec5619.bi.common.BaseResponse;
import com.elec5619.bi.common.ResultUtils;
import com.elec5619.bi.service.EmailNotificationService;
import com.elec5619.bi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {
    private final EmailNotificationService emailNotificationService;
    private final UserService userService;

    public EmailController(EmailNotificationService emailNotificationService, UserService userService) {
        this.emailNotificationService = emailNotificationService;
        this.userService = userService;
    }

    /**
     * 发送邮件给管理员
     *
     * @param userName ID of the liked post
     * @param email    Email address to send the notification
     * @param content  Message to send
     */
    @PostMapping("/sendToAdmin")
    public BaseResponse<Boolean> sendEmailToAdmin(@RequestParam String userName, String email, String content) throws MessagingException {
        // 使用userService的getAdminEmail查找管理员的邮箱
        ArrayList<String> adminEmail = new ArrayList<>();
        // 遍历所有管理员的邮箱，加入adminEmail
        for (int i = 0; i < userService.getAdminEmail().size(); i++) {
            adminEmail.add(userService.getAdminEmail().get(i).getUserEmail());
        }
        // 获取邮箱总数
        int emailNum = adminEmail.size();
        // 随机选择一个管理员的邮箱
        int randomNum = (int) (Math.random() * emailNum);
        String recipientEmail = adminEmail.get(randomNum);

        // 发送邮件通知
        boolean result = emailNotificationService.sendEmailToAdmin(userName, email, content, recipientEmail);
        if (result){
            return ResultUtils.success(true);
        }else {
            return ResultUtils.error(500, "error");
        }

    }
}
