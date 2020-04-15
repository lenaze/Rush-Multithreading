package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable{
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        try {
            int i = 1;
            String value, key;
            while (!currentThread.isInterrupted()) {
                key = String.valueOf(i);
                value = String.format("Some text for %d", i);
                map.put(key, value);
                Thread.sleep(500);
                i++;
            }
        }catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }

}
