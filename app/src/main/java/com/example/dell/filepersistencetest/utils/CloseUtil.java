package com.example.dell.filepersistencetest.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by DELL on 2017/5/7.
 */

/*
* 创建一个CloseUtil的工具类，这样涉及到的接口等等的操作就可以直接写在里面
* */

public class CloseUtil {

    public static void closeQuietly(Closeable closeable)//调用Closeable的接口
    /*
    * 因为Writer implements Appendable, Closeable, Flushable(Writer这个类实现Closeable这个接口)
    * 而BufferedWriter又继承Writer这个类
    * 所以，
    */
    {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
