package com.elec5619.bi;


import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 *
 * @author Zhaohao Lu
 */
@SpringBootTest
class MainApplicationTests {

        @Resource
        private MainApplication mainApplication;

        @Test
        void contextLoads() {
            assert mainApplication != null;
        }

}
