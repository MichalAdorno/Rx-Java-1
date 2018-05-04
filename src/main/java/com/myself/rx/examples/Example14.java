package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Example14 {

    public static void show() throws Exception {
    //----------------------------------------------------------------------------------
        Observable<Long> seconds = Observable
                .interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();

        seconds.subscribe(x -> System.out.println("[1] Observer 1: " + x));
        Thread.sleep(3000);

        seconds.subscribe(x -> System.out.println("[1] Observer 2: " + x));
        Thread.sleep(2000);

        seconds.subscribe(x -> System.out.println("[1] Observer 3: " + x));
        Thread.sleep(3000);
    //----------------------------------------------------------------------------------
        Observable<Integer> source = Observable
                .just(6,2,5,7,1,4,9,8,3)
                .scan(0, (acc, x) -> acc+x)
                .cache();

        source.subscribe(x -> System.out.println("[2] Observer 1: " + x));
        source.subscribe(x -> System.out.println("[2] Observer 2: " + x));
    //----------------------------------------------------------------------------------
        Observable<Long> seconds2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect(2);

        seconds2.subscribe(x -> System.out.println("[3] Observer 1: " + x));
        Thread.sleep(3000);

        seconds2.subscribe(x -> System.out.println("[3] Observer 2: " + x));
        Thread.sleep(2000);

        seconds2.subscribe(x -> System.out.println("[3] Observer 3: " + x));
        Thread.sleep(3000);
    //----------------------------------------------------------------------------------
        ConnectableObservable<Long> seconds3 = Observable
                .interval(1, TimeUnit.SECONDS)
                .publish();
        System.out.println("[4] After Publish-1, Before Connect-1");
        seconds3.subscribe(x -> System.out.println("[4] Observer 1: " + x));
        seconds3.subscribe(x -> System.out.println("[4] Observer 2: " + x));
        seconds3.subscribe(x -> System.out.println("[4] Observer 3: " + x));
        System.out.println("[4] Before Connect-2");
        seconds3.connect();
        System.out.println("[4] After Connect-1");
        Thread.sleep(2000);
        System.out.println("[4] After Connect-2");
        seconds3.subscribe(x -> System.out.println("[4] Observer 4: " + x));
        Thread.sleep(2000);
    //----------------------------------------------------------------------------------






    }



}
