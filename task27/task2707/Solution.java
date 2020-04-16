package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {

        new Thread(() -> {
            //завладеваем монитором второго обьекта на половину минуты
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                //пытаемся завладеть монитором первого, но он уже занят вторым потоком
                synchronized (o2) {
                }
            }
        }).start();
        //второй поток, который завладевает монитором первого обьекта,
        // но не может завладеть монитором второго, потому что он занят
        Thread thread = new Thread(() -> solution.someMethodWithSynchronizedBlocks(o1, o2));
        thread.start();
        //первый поток просыпается, хочет завладеть мьютексом первого обьекта, но не может, потому как он
        // занят вторым потоком
        Thread.sleep(2000);
        //Если нить не заблокирована, то всё хорошо и мониторы захватываются в нужном порядке
        return thread.getState() != Thread.State.BLOCKED;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
