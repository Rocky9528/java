package com.mashibing.dao;

import com.mashibing.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao extends BaseDao<Teacher>{

    @Autowired
    Teacher teacher;

    public void save() {
        teacher.name="99";
        System.out.println("Teacher 的save 保存老师"+teacher);
    }
}
