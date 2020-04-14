package com.javarush.task.task25.task2512;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        method(e);
    }

    public static void method(Throwable cause) {
        if (cause == null) return;
        method(cause.getCause());
        System.out.println(cause.toString());
    }


    public static void main(String[] args) {
    }
}
