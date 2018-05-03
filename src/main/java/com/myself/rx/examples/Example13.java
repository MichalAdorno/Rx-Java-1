package com.myself.rx.examples;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.ThreadLocalRandom;

public class Example13 {
    public static void show() throws Exception {
    //----------------------------------------------------------------------------------
        Observable<Integer> threeRandoms1 = Observable
                .range(1,3)
                .map(i -> randomInt());
        System.out.println("test-obs-1");
        threeRandoms1.subscribe(i -> System.out.println("[1] Observer 1: " + i));
        System.out.println("test-obs-2");
        threeRandoms1.subscribe(i -> System.out.println("[1] Observer 2: " + i));
        System.out.println("test-obs-3");
    //----------------------------------------------------------------------------------
        ConnectableObservable<Integer> threeRandoms2 = Observable
                .range(1,3)
                .map(i -> randomInt())
                .publish();

        threeRandoms2.subscribe(i -> System.out.println("[2] Observer 1: " + i));
        threeRandoms2.subscribe(i -> System.out.println("[2] Observer 2: " + i));

        threeRandoms2.connect();
    //----------------------------------------------------------------------------------
        ConnectableObservable<Integer> threeRandoms3 = Observable
                .range(1,3)
                .map(i -> randomInt())
                .publish();
        System.out.println("test-conn-obs-1");
        threeRandoms3
                .map(i -> randomInt())
                .subscribe(i -> System.out.println("[3] Observer 1: " + i));
        System.out.println("test-conn-obs-2");
        threeRandoms3
                .map(i -> randomInt())
                .subscribe(i -> System.out.println("[3] Observer 2: " + i));
        System.out.println("test-conn-obs-3");
        threeRandoms3.connect();
        System.out.println("test-conn-obs-4");
    //----------------------------------------------------------------------------------
        ConnectableObservable<Integer> threeRandoms4 = Observable
                .range(1,3)
                .map(i -> randomInt())
                .publish();

        threeRandoms4
                .subscribe(i -> System.out.println("[4] Observer 1: " + i));

        threeRandoms4
                .reduce(0, (sum, next) -> sum + next)
                .subscribe(i -> System.out.println("[4] Observer 2: " + i));

        threeRandoms4.connect();
    //----------------------------------------------------------------------------------
        ConnectableObservable<Integer> threeRandoms5 = Observable
                .range(1,3)
                .map(i -> randomInt())
                .publish();

        threeRandoms5.subscribe(i -> System.out.println("[5] Observer 1: " + i));
        threeRandoms5.connect();
        threeRandoms5.subscribe(i -> System.out.println("[5] Observer 2: " + i));
        threeRandoms5.subscribe(i -> System.out.println("[5] Observer 3: " + i));
        threeRandoms5.connect();
    //----------------------------------------------------------------------------------
        Observable<Integer> threeRandoms6 = Observable
                .range(1,3)
                .map(i -> randomInt())
                .publish()
                .autoConnect(3); //not start with 4, with 2 Obs3 won't work
        //autoconnect->observable, connect->connectable observable
        threeRandoms6.subscribe(i -> System.out.println("[6] Observer 1: " + i));
        threeRandoms6.subscribe(i -> System.out.println("[6] Observer 2: " + i));
        threeRandoms6.subscribe(i -> System.out.println("[6] Observer 3: " + i));
    //----------------------------------------------------------------------------------

    }

    private static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }

}
