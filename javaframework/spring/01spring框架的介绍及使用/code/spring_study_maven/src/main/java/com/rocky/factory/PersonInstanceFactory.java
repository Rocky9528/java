package com.rocky.factory;

import com.rocky.bean.Person;

public class PersonInstanceFactory {
    public Person getPerson(int id,String name,int age){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        return person;
    }
}