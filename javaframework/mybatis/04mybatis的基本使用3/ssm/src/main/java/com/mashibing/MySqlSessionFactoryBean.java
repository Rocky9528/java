package com.mashibing;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

import java.io.IOException;

public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {
    protected SqlSessionFactory buildSqlSessionFactory() throws Exception {
        try {
            return super.buildSqlSessionFactory();
        } catch (Exception e) {
            //- XML有错误时打印异常
            e.printStackTrace();
            throw new NestedIOException("Failed to parse mapping resource: " + e);
        }
    }
}
