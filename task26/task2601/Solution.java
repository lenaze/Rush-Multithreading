package com.javarush.task.task26.task2601;

import java.util.*;
import java.util.stream.Collector;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
/*        Integer[] array = {13, 8, 15, 14, 5, 17};
        System.out.println(Arrays.toString(sort(array)));*/
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        if (array != null){
            int mediana;
            Arrays.sort(array);
            if (array.length % 2 == 0)
                mediana = (array[array.length / 2 - 1] + array[ array.length / 2 ]) / 2;
            else mediana = array[array.length / 2];

            System.out.println(mediana);

            Comparator<Integer> comparator = (o1, o2) -> {
                int result = Math.round(Math.abs(mediana - o1) - Math.abs(mediana - o2));
                return (result == 0) ? (o1 - o2) : result;
            };

            Arrays.sort(array, comparator);
        }

        return array;
    }
}
