package com.myself.rx.examples.tools;

import java.util.concurrent.ThreadLocalRandom;

public final class Tools {

    public static final <T> void print(int nr, T x){
        System.out.println("[" + nr + "] " + x + " - [" + Thread.currentThread().getName() + "]");
    }

    private static final <T> T intenseCalculation(T value) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

}
