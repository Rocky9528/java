package com.rocky.factory;

import com.rocky.bean.Person;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        Person person = new Person();
        person.setId(12);
        person.setName("zhang12");
        person.setAge(12);
        return person;
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
//        return false;
    }
}
