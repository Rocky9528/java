package com.rocky.bean;

import javax.swing.*;
import java.util.*;

public class Person {

    public  final static String constantName="张三";

    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Address address;
    private String[] hobbies;
    private List<Book> books;
    private Set<Integer> sets;
    private Map<String,Object> maps;
    private Properties properties;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Set<Integer> getSets() {
        return sets;
    }

    public void setSets(Set<Integer> sets) {
        this.sets = sets;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", books=" + books +
                ", sets=" + sets +
                ", maps=" + maps +
                ", properties=" + properties +
                '}';
    }

    public Person() {
        System.out.println("默认构造Person");
    }

    public Person(Integer id, String name, Integer age, String gender) {
        System.out.println("构造Person");
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(Integer id,Integer age, String name,  String gender) {
        System.out.println("构造Person");
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(Address address) {
        System.out.println("构造Person");
        this.address=address;
    }

    public  void init(){
        //编写N行逻辑代码完成初始化功能
        System.out.println("person对象初始化完成");
    }

    public  void destroy(){
        System.out.println("person对象被销毁");
    }
}