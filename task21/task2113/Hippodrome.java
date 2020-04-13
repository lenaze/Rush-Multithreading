package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses = new ArrayList<>();
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public Hippodrome() {
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) { }
        }
    }
    public void move(){
        for (Horse horse: horses) {
            horse.move();
        }
    }
    public void print(){
        for (Horse horse: horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner(){
        double maxDistance = 0;
        Horse winner = null;
        for (Horse h : horses) {
            maxDistance = Math.max(h.getDistance(), maxDistance);
            if(maxDistance == h.getDistance()) winner = h;
        }

        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse horse1 = new Horse("Vesta", 3, 0);
        Horse horse2 = new Horse("Lada", 3, 0);
        Horse horse3 = new Horse("Lyuba", 3, 0);

        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);

        game.run();
        game.printWinner();
    }
}
