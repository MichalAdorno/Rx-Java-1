package com.myself.rx.examples;


import com.myself.rx.examples.tools.Tools;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class Example37 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        Observable<Integer> source = Observable.range(1,50);
        Disposable d = source.toFlowable(BackpressureStrategy.DROP)
                .doOnNext(x -> Tools.<Integer>print(1, x))
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e){}
                    Tools.<Integer>print(1, x);
                });
        Thread.sleep(1000);
        d.dispose();
        System.out.println("Completed!");
        //----------------------------------------------------------------------------------
        Flowable<Integer> integers = Flowable.range(1, 50)
                        .subscribeOn(Schedulers.computation());

        Observable.just("Alpha","Beta","Gamma","Delta","Epsilon")
                .flatMap(s -> integers.map(i -> i + "-" + s).toObservable())
                .subscribe(x -> Tools.<String>print(2, x));

        Thread.sleep(5000);
        //----------------------------------------------------------------------------------
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer(10,
                        () -> System.out.println("overflow!"),
                        BackpressureOverflowStrategy.DROP_LATEST)
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    Thread.sleep(5);
                    Tools.<Long>print(3, i);
                });
        Thread.sleep(5000);
        //----------------------------------------------------------------------------------
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(i ->
                        System.out.println("Dropping " + i)
                )
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    Thread.sleep(5);
                    Tools.<Long>print(4, i);
                });
        Thread.sleep(5000);
        //----------------------------------------------------------------------------------


    }




}
