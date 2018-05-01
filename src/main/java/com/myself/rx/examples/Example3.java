package com.myself.rx.examples;

import com.myself.rx.examples.example3.ConsumerRunnable;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Example3 {
    public static void show() throws InterruptedException {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

        Observable<Integer> source = Observable.fromIterable(list);

        Thread[] observers = {
                new Thread(new ConsumerRunnable(source, 2)),
                new Thread(new ConsumerRunnable(source, 3)),
                new Thread(new ConsumerRunnable(source, 5))
        };
        for(Thread t: observers)
            t.start();
        for(Thread t: observers)
            t.join();
    }
}
