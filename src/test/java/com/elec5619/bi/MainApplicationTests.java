package com.elec5619.bi;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

/**
 * 主类测试
 *
 * @author Zhaohao Lu
 */
@SpringBootTest
class MainApplicationTests {

        @Resource
        private MainApplication mainApplication;

        @Autowired
        DataSource dataSource;

        @Test
        void connentToDB() throws SQLException {
                System.out.println(dataSource.getClass());
                System.out.println(dataSource.getConnection());
        }

        @Test
        void contextLoads() {
            assert mainApplication != null;
        }

}
