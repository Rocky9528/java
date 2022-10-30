package com.mashibing.dao;

import com.mashibing.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {

    @Autowired
    Student student;

    public void save() {
        System.out.println("StudentDao 的 save 保存学生"+student);
    }
}
