package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class Example15 {

    public static void show() throws Exception {
        //----------------------------------------------------------------------------------
        Subject<String> subject = PublishSubject.create();
        subject.map(String::length)
                .subscribe(x -> System.out.println("[1] subject: " + x));

        String temp = "";
        for(int i = 0; i < 5; i++)
            subject.onNext((temp = temp + "A"));
        subject.onComplete();

        //----------------------------------------------------------------------------------
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                        .map(l -> (l + 1) + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                        .map(l -> ((l + 1) * 300) + " milliseconds");

        Subject<String> zubject = PublishSubject.create();
        zubject.subscribe(x -> System.out.println("[2] zubject: " + x));
        source1.subscribe(zubject);
        source2.subscribe(zubject);
        Thread.sleep(3000);

        //----------------------------------------------------------------------------------
        Observable<String> source3 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> (l + 1) + " seconds");
        Observable<String> source4 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> ((l + 1) * 300) + " milliseconds");

        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {}

            @Override
            public void onNext(String s) {
                System.out.println("[3] observer1: " + s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("[3] observer1: Done!");
            }
        };

        source1.subscribe(observer1);
        source2.subscribe(observer1);
        Thread.sleep(3000);

        //----------------------------------------------------------------------------------
        Observable<String> merged = Observable.merge(source3, source4);


        merged.subscribe(x -> System.out.println("[4] Merged: " + x));

        Thread.sleep(3000);






    }



}
