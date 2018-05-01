package com.myself.rx.examples;

import com.myself.rx.examples.example5.ConsumerRunnable;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example5 {
    public static void show() throws InterruptedException {
        ConnectableObservable<Long> source = Observable
                .interval(1, TimeUnit.SECONDS)
                .publish();

        source.connect();

        Thread t0 = new Thread(new ConsumerRunnable(source, 0));
        t0.start();
        Thread.currentThread().sleep(4000);
        Thread t1 = new Thread(new ConsumerRunnable(source, 1));
        t1.start();
        Thread.currentThread().sleep(4000);
        Thread t2 = new Thread(new ConsumerRunnable(source, 2));
        t2.start();


        t0.join();
        t1.join();
        t2.join();

    }
}
