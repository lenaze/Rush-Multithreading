package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class StudentsDataBase {
    public static List<Student> students = new ArrayList<>();

    public static void addInfoAboutStudent(String name, int age, double averageGrade) {
        Student student = new Student(name, age, averageGrade);
        students.add(student);
        printInfoAboutStudent(student.getName(), student);

    }

    public static void printInfoAboutStudent(String name, Student student) {
        System.out.println("Имя: " + name + " Возраст: " + student.getAge());
    }

    public static void removeStudent(int index) throws IndexOutOfBoundsException {
        students.remove(index);
    }

    public static void findDimaOrSasha() {
        boolean found = false;
        for (Student student : students) {
            if (!found) {
                if (student.getName().equals("Dima")) {
                    System.out.println("Студент Dima есть в базе.");
                    found = true;
                }

                if (student.getName().equals("Sasha")) {
                    System.out.println("Студент Sasha есть в базе.");
                    found = true;
                }
            }
        }
    }
}