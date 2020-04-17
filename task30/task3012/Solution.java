package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        int[] arr = {1, 3, 9, 27, 81, 243, 729, 2187};

        StringBuilder s = new StringBuilder();
        int n = number;
        while (n > 0) {
            if (n % 3 == 0) {
                s.append("0");
                n /= 3;
            } else if (n % 3 == 1) {
                s.append("+");
                n /= 3;
            } else {
                s.append("-");
                n /= 3;
                n++;
            }
        }
        int sum = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                str.append(" + ").append(arr[i]);
                sum += arr[i];
            } else if (s.charAt(i) == '-') {
                str.append(" - ").append(arr[i]);
                sum -= arr[i];
            }
        }
        System.out.println(sum + " =" + str);
    }
}