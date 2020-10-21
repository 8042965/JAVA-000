package com.jvm1;

import java.io.*;

/**
 * @program: jvm-project
 *
 * @description: ClassLoader工具类
 *
 * @author: zhengh
 *
 * @create: 2020-10-20 23:07 extends ClassLoader
 **/
public class HelpClassLoaderUtil  extends ClassLoader{


    public Class<?> findClass(String classPath) throws ClassNotFoundException {
        String[] classArray = classPath.split("\\\\");

        byte[] classData = decodeClass(classPath);
        if (classData == null) {
            return null;
        }

        //拿到class的名字 ，并且把该文件的数据读出
        return defineClass(classArray[classArray.length-1].split("\\.")[0], classData, 0, classData.length);
    }


    private byte[] decodeClass(String classPath) {
        byte[] enBuf = readClassFile(classPath);
        if (enBuf == null) {
            return null;
        }

        byte[] deBuf = new byte[enBuf.length];
        for (int i = 0; i < deBuf.length; i++) {
            //恢复出来class文件中的字节流数据
            deBuf[i] = (byte) (255 - enBuf[i]);
        }

        return deBuf;
    }


    /**
     * 读取class的数据
     * @param path
     * @return
     */
    private byte[] readClassFile(String path) {

        InputStream inputStream = null;
        try {
            File file = new File(path);
            inputStream = new FileInputStream(path);
            int value = 0, bufIndex = 0;

            byte[] buffer = new byte[(int) file.length()];
            while (inputStream!=null) {
                try {
                    value = inputStream.read();
                    if (value == -1) {
                        break;
                    }

                    buffer[bufIndex++] = (byte)value;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return buffer;
        } catch (FileNotFoundException e) {
            System.out.println("出错了:"+e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
