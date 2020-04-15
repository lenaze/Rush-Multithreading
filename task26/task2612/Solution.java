package com.javarush.task.task26.task2612;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
Весь мир играет комедию
*/
public class Solution {
    private Lock lock = new ReentrantLock();

    public void someMethod() {
        // Implement the logic here. Use the lock field
        boolean locked = lock.tryLock();
        try {
            if (!locked)
                actionIfLockIsBusy();
            else actionIfLockIsFree();
        }catch (Exception e) { e.getCause(); }
        finally {
            if (locked)
                lock.unlock();
        }
    }

    public void actionIfLockIsFree() {
    }

    public void actionIfLockIsBusy() {
    }
}
