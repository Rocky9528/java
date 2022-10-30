package com.mashibing.service;

import com.mashibing.bean.Student;
import com.mashibing.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student>{

    @Autowired
    StudentDao studentDao;

    public void save(){
        System.out.println("执行 StudentService 的save");
        studentDao.save();

    }

}
