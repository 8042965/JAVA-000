package com.jvm1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test{

    static HelpClassLoaderUtil helpClassLoaderUtil = new HelpClassLoaderUtil();
    static String classPath = "D:\\IdeaProject-3\\jvm-project\\src\\com\\jvm1\\Hello.xlass";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object hello = helpClassLoaderUtil.findClass(classPath).newInstance();
        //1、通过反射拿到该类
        Class<?> helloClass = hello.getClass();
        //2、通过类拿到名字为hello的方法
        Method helloMethod = helloClass.getDeclaredMethod("hello");
        //3、执行hello这个方法
        helloMethod.invoke(hello);

//        String[] classArray = classPath.split("\\\\");
//        System.out.println(Arrays.toString(classPath.split("\\\\")));
//        System.out.println(Arrays.toString(classArray[classArray.length-1].split("\\.")));

    }
}