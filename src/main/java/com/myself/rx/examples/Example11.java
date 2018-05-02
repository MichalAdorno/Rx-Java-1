package com.myself.rx.examples;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Example11 {

    public static void show() throws Exception {

        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta","Epsilon");

        Observable<Integer> source2 = Observable.range(1,2);
        Observable.zip(source1, source2, (s, i) -> "[1] " + s + "-" + i)
                .subscribe(System.out::println);

        Observable<Long> secObservable = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> milObservable = Observable.interval(300, TimeUnit.MILLISECONDS);

        Observable.combineLatest(milObservable, secObservable, (s, z) -> "[2] SOURCE-1: " + s + ", SOURCE-2: " + z)
                .subscribe(System.out::println);

        Thread.sleep(3000);

        milObservable.withLatestFrom(secObservable, (s, z) -> "[3] SOURCE-1: " + s + ", SOURCE-2: " + z)
                .subscribe(System.out::println);

        Thread.sleep(3000);

        secObservable.withLatestFrom(milObservable, (s, z) -> "[4] SOURCE-1: " + s + ", SOURCE-2: " + z)
                .subscribe(System.out::println);

        Thread.sleep(3000);

    }



}
