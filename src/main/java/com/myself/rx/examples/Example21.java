package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;

import static com.myself.rx.examples.Example20.intenseCalculation;

public class Example21 {

    public static void show() throws Exception {

        Observable.just("Alpha", "Beta", "Gamma", "Delta",
                "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation((s)))
                .subscribe(System.out::println);
        Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation((s)))
                .subscribe(System.out::println);

//        Thread.sleep(20000); //or:
        intenseCalculation(1);

    }




}
