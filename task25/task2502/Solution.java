package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            String[] wheelsString = loadWheelNamesFromDB();
            if (wheelsString.length != 4) throw new IllegalArgumentException();
            wheels = new ArrayList<>();
            for (String s : wheelsString) {
                if (s.equals(Wheel.BACK_LEFT.toString())) wheels.add(Wheel.BACK_LEFT);
                else if (s.equals(Wheel.BACK_RIGHT.toString())) wheels.add(Wheel.BACK_RIGHT);
                else if (s.equals(Wheel.FRONT_RIGHT.toString())) wheels.add(Wheel.FRONT_RIGHT);
                else if (s.equals(Wheel.FRONT_LEFT.toString())) wheels.add(Wheel.FRONT_LEFT);
                else throw new IllegalArgumentException();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
