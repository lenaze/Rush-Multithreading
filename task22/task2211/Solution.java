package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String nameFile = args[0];
        String nameFileTo = args[0];
        Charset charsetFrom = Charset.forName("Windows-1251");
        Charset charsetTo = Charset.forName("UTF-8");
        String string = "";

        try(InputStream reader = new FileInputStream(nameFile)) {

            byte[] b = new byte[1000];
            while (reader.available() != 0){
                reader.read(b);
                string = string.concat(new String(b, charsetFrom));
            }
        }

        try(OutputStream writer = new FileOutputStream(nameFileTo)){
            writer.write(string.getBytes(charsetTo));
        }
    }
}
