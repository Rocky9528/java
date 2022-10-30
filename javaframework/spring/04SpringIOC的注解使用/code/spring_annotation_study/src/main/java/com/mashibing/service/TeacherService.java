package com.mashibing.service;

import com.mashibing.bean.Teacher;
import com.mashibing.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseService<Teacher> {

    @Autowired
    TeacherDao teacherDao;

    public void save(){
        System.out.println("执行 TeacherService 的save");
        teacherDao.save();

    }
}
