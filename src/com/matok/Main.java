package com.matok;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new FizzBuzzThread("Fizz", i -> i % 3 == 0 && i % 5 != 0);
        t1.setName("F");
        t1.start();

        Thread t2 = new FizzBuzzThread("Buzz", i -> i % 5 == 0 && i % 3 != 0);
        t2.setName("B");
        t2.start();

        Thread t3 = new FizzBuzzThread(null, i -> i % 5 != 0 && i % 3 != 0);
        t3.setName("n");
        t3.start();

        Thread t4 = new FizzBuzzThread("FizzBuzz", i -> i % 5 == 0 && i % 3 == 0);
        t4.setName("X");
        t4.start();
    }
}

class FizzBuzz {
    private int act;
    private int to;

    public FizzBuzz(int from, int to) {
        this.act = from;
        this.to = to;
    }

    public void next() {
        this.act ++;
    }

    public int act() {
        return act;
    }

    public boolean finished() {
        return act >= to;
    }
}

class FizzBuzzThread extends Thread {
    private static final FizzBuzz counter = new FizzBuzz(1, 100);
    private final Function<Integer, Boolean> condition;
    private final String message;


    public FizzBuzzThread(String message, Function<Integer, Boolean> condition) {
        super();
        this.condition = condition;
        this.message = message;
    }

    @Override
    public void run() {
        super.run();

        while (!this.counter.finished()) {
            synchronized (counter) {
                if (!this.counter.finished() && this.condition.apply(this.counter.act())) {
                    String print = this.message != null ? this.message : Integer.valueOf(this.counter.act()).toString();
                    System.out.println("[" + getName() + "] " + print);
                    this.counter.next();
                }
            }
        }
    }
}
