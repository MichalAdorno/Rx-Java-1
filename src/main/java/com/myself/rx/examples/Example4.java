package com.myself.rx.examples;

import com.myself.rx.examples.example4.ConsumerRunnable;
import com.myself.rx.examples.example4.Broadcaster;
import io.reactivex.Observable;

public class Example4 {
    public static void show() throws InterruptedException {
        Long[] integers = {1L,2L,3L,4L,5L,6L,7L,8L,9L,10L};
        Observable<Long> source = Observable.fromArray(integers);
        Broadcaster broadcaster = new Broadcaster(source);

        Thread[] observers = {
                new Thread(new ConsumerRunnable(broadcaster.getSource(), 2)),
                new Thread(new ConsumerRunnable(broadcaster.getSource(), 3)),
                new Thread(new ConsumerRunnable(broadcaster.getSource(), 5))
        };


//        broadcaster.getSource().connect();
        Thread tb = new Thread(broadcaster);
        tb.start();

        for(Thread t : observers)
            t.start();

        for(Thread t : observers)
            t.join();

        tb.join();

    }
}
