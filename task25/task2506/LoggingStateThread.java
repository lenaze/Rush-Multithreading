package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread currentThread;

    public LoggingStateThread(Thread thread) {
        currentThread = thread;
    }

    @Override
    public void run() {
        super.run();
        State currentState = currentThread.getState();
        System.out.println(currentState);
        while (true) {

            if (currentThread.getState() != currentState) {
                currentState = currentThread.getState();
                System.out.println(currentState);
            }
            if (currentState == State.TERMINATED) {
                break;
            }
        }
        interrupt();
    }
}
