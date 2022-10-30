package com.mashibing.service;

import com.mashibing.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public void save(){
        System.out.println("自动注入的对象："+baseDao);
        baseDao.save();
    }
}
