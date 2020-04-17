package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        try {
            String str = args[0];
/*
            Pattern pattern = Pattern.compile("\\W");
            if (pattern.matcher(str).find()) System.out.println("incorrect");
            else {
*/
                for (int i = 2; i < 37; i++) {
                    if (isNumberOfThisNumberSystem(str, i)) {
                        System.out.println(i);
                        break;
                    }
                    else if ( i == 36 ) System.out.println("incorrect");
                }
            } catch (Exception ignored) {}

    }

    public static boolean isNumberOfThisNumberSystem(String s, int i){
        boolean bool = false;
        try{
            new BigDecimal(new BigInteger(s, i));
            bool = true;
        } catch (Exception ignored) {}
        return bool;
    }
}