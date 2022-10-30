package com.mashibing.dao;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

    public void save(){
        System.out.println("PersonDao 的save 保存成功");
    }

    public void update(){
        System.out.println("PersonDao 更新成功");
    }
}
