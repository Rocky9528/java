package com.mashibing.converter;

import com.mashibing.bean.Person;
import com.mashibing.bean.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class MyConverter2 implements Converter<String, Person> {

    @Override
    public Person convert(String source) {
        Person person = null;
        if ((source != null) && !"".equals(source) && (source.split("-").length == 6)){
            person = new Person();
            person.setId(Integer.parseInt(source.split("-")[0]));
            person.setName(source.split("-")[1]);
            person.setAge(Integer.parseInt(source.split("-")[2]));
            person.setGender(source.split("-")[3]);
            String str=source.split("-")[4];
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String format = dateFormat.format(str);
            Date date=new Date(str);
            String format1 = dateFormat.format(date);
            person.setBirth(date);
            person.setEmail(source.split("-")[5]);
        }
        return person;
    }
}
