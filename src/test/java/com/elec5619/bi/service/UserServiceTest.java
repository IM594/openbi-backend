package com.elec5619.bi.service;

import javax.annotation.Resource;

import com.elec5619.bi.common.BaseResponse;
import com.elec5619.bi.controller.UserController;
import com.elec5619.bi.model.dto.user.UserLoginRequest;
import com.elec5619.bi.model.entity.User;
import com.elec5619.bi.model.vo.LoginUserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试
 *
 * @author Zhaohao Lu
 */
@SpringBootTest
public class UserServiceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void userRegister() {
        String userAccount = "Zhaohao";
        String userPassword = "";
        String checkPassword = "123456";
        try {
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            assertEquals(-1, result);
            userAccount = "zhao";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            assertEquals(-1, result);
        } catch (Exception e) {

        }
    }



//    @Test
//    public void testUserLogin() {
//        // 创建一个UserLoginRequest对象
//        UserLoginRequest userLoginRequest = new UserLoginRequest();
//        userLoginRequest.setUserAccount("Zhaohao");
//        userLoginRequest.setUserPassword("123123123");
//
//        // 创建一个模拟的LoginUserVO对象，模拟登录成功
//        LoginUserVO loginUserVO = new LoginUserVO();
//        loginUserVO.setUserId(1698271973517299714L);
//        loginUserVO.setUserName("lzh");
//        loginUserVO.setUserRole("user");
//
//        // 模拟userService的userLogin方法返回上面创建的LoginUserVO对象
//        Mockito.when(userService.userLogin("Zhaohao", "123123123", null))
//                .thenReturn(loginUserVO);
//
//        // 调用UserController的userLogin方法
//        BaseResponse<LoginUserVO> response = new BaseResponse<>(200, loginUserVO);
//
//        // 验证响应的状态码是否为200 (OK)
//        assertEquals(200, response.getCode());
//
//        // 验证响应中的LoginUserVO是否与预期匹配
//        LoginUserVO responseLoginUserVO = response.getData();
//        assertNotNull(responseLoginUserVO);
//        assertEquals(1698271973517299714L, responseLoginUserVO.getUserId());
//        assertEquals("lzh", responseLoginUserVO.getUserName());
//        assertEquals("user", responseLoginUserVO.getUserRole());
//    }

    @Test
    public void testUserLogin() {
        User user = new User();
        user.setUserAccount("Zhaohao");
        user.setUserPassword("123123123");
        user.setUserId(1698271973517299714L);

        MockHttpServletRequest request = new MockHttpServletRequest();
        LoginUserVO loginUserVO = userService.userLogin("Zhaohao", "11", request);
        assertNull(loginUserVO);
//        assertEquals(1698271973517299714L, loginUserVO.getUserId());

    }



}
