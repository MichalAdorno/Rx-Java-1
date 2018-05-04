package com.myself.rx.examples;


import io.reactivex.Observable;

import java.util.concurrent.ThreadLocalRandom;

public class Example20 {

    public static void show() throws Exception {

        Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon")
                .map(s -> intenseCalculation((s)))
                .subscribe(System.out::println);
        Observable.range(1,6)
                .map(s -> intenseCalculation((s)))
                .subscribe(System.out::println);
        //sequentially in the same thread!

    }

    public static <T> T intenseCalculation(T value) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }


}
