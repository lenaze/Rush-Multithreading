package com.javarush.task.task22.task2202;

import com.sun.org.apache.bcel.internal.generic.FSUB;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        int counter = 0;
        String str = null;
        try {
            for (int i = 0; i < string.length(); i++) {
                if (string.startsWith(" ", i)) counter++;
                if (counter == 4) {
                    str = string.substring(string.indexOf(" ") + 1, i + 1);
                    string = string.substring(i + 1);
                    break;
                }
            }
            if (string.contains(" ")) string = string.substring(0, string.indexOf(" "));
            str = str.concat(string);
        }catch (Exception e) {throw new TooShortStringException();}
        return str;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
