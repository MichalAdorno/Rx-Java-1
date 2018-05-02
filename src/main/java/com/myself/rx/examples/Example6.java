package com.myself.rx.examples;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class Example6 {
    public static void show() throws InterruptedException {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable = seconds.subscribe(System.out::println);

        Thread.sleep(5000);

        disposable.dispose();

        Thread.sleep(5000);

        Disposable disposable2 = seconds.subscribe(System.out::println);

        Thread.sleep(3000);

    }
}
