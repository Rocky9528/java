package com.mashibing;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class PrintlnTest {
    public static void main(String[] args) throws IOException {

//        Scanner scanner=new Scanner(System.in);
//        String s2 = scanner.nextLine();
//        System.out.println("输入的信息："+s2);

        File file=new File("1.txt");

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write("哈哈".getBytes());
        fileOutputStream.close();

//
//        Console console=System.console();
//        String s1 = console.readLine("hello:");
//        console.printf(s1);
//        String s = console.readLine();
//
//        System.out.println(s);
    }
}
