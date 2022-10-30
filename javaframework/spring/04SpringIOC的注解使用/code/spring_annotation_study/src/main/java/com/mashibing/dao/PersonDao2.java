package com.mashibing.dao;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDao2 extends PersonDao {

    public void save(){
        System.out.println("PersonDao1 的save 保存成功");
    }

    public void update(){
        System.out.println("PersonDao1 更新成功");
    }
}
