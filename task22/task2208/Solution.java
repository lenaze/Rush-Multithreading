package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        //Map<String, String> map = new HashMap<>();
        //map.put("name", "Ivanov");
        //map.put("country", "Ukraine");
        //map.put("city", "Kyiv");
        //System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        params.entrySet().removeIf(stringStringEntry -> stringStringEntry.getValue() == null);
        StringBuilder str = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> pair = iterator.next();
            str.append(String.format("%s = '%s'", pair.getKey(), pair.getValue()));
            if (iterator.hasNext()) str.append(" and ");
        }
        return str.toString();
    }
}
