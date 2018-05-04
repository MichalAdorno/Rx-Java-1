package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class Example16 {

    public static void show() throws Exception {
        //----------------------------------------------------------------------------------
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> (l + 1) + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> ((l + 1) * 300) + " milliseconds");

        Subject<String> zubject = PublishSubject.create();
        Subject<String> subject = PublishSubject.create();
        zubject.subscribe(x -> System.out.println("[1] zubject: " + x));
        subject.subscribe(x -> System.out.println("[2] subject: " + x));
        source1.subscribe(zubject);
        source2.subscribe(zubject);
        zubject.subscribe(subject);
        Thread.sleep(3000);

        //----------------------------------------------------------------------------------

    }



}
