package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String nameFile;
        StringBuilder strings = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            nameFile = reader.readLine();
            try(BufferedReader readerFile = new BufferedReader(new FileReader(nameFile))) {
                while (readerFile.ready()) {
                    strings.append(readerFile.readLine()).append(" ");
                }
            }
        } catch (IOException e) { System.err.println(e.getCause()); }
        
        String[] words = strings.toString().split(" ");
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[i] != null && words[j] != null
                        && words[i].contentEquals(new StringBuilder(words[j]).reverse())) {
                    result.add(new Pair(words[i], words[j]));
                    words[i] = null;
                    words[j] = null;
                }
            }
        }
        for (Pair pair : result) {
            System.out.println(pair);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
