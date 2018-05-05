package com.myself.rx.examples;


import com.sun.media.jfxmediaimpl.MediaDisposer;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;


public class Example31 {

    public static void show() throws Exception {
    //----------------------------------------------------------------------------------
        Observable.range(1,50)
                .buffer(8, TreeSet::new)
                .subscribe(x -> System.out.println("[ 1] " + x));
    //----------------------------------------------------------------------------------
        Observable.range(1,10)
                .buffer(2,1)
                .subscribe(x -> System.out.println("[ 2] " + x));
    //----------------------------------------------------------------------------------
        Observable.range(1,10)
                .buffer(2,2)
                .subscribe(x -> System.out.println("[ 3] " + x));
    //----------------------------------------------------------------------------------
        Observable.range(1,10)
                .buffer(2,3)
                .subscribe(x -> System.out.println("[ 4] " + x));
    //----------------------------------------------------------------------------------
        Observable.range(1,10)
                .buffer(3,3)
                .subscribe(x -> System.out.println("[ 5] " + x));
    //----------------------------------------------------------------------------------
        Observable.range(1,10)
                .buffer(3,3)
                .filter(c -> c.size() == 3)
                .subscribe(x -> System.out.println("[ 6] " + x));
    //----------------------------------------------------------------------------------
        Disposable d1 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .buffer(900, TimeUnit.MILLISECONDS)
                .subscribe(x -> System.out.println("[ 7] " + x));
        Thread.sleep(3600);
        d1.dispose();
    //----------------------------------------------------------------------------------
        Disposable d2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .buffer(3)
                .subscribe(x -> System.out.println("[ 8] " + x));
        Thread.sleep(3600);
        d2.dispose();
    //----------------------------------------------------------------------------------
        Observable.range(1,50)
                .window(12)
                .flatMapSingle(
                        obs -> obs.reduce("", (t, n) -> t + (t.equals("") ? "" : "|") + n)
                )
                .subscribe(x -> System.out.println("[ 9] " + x));
    //----------------------------------------------------------------------------------
        Observable.range(1,50)
                .window(2,2)
                .flatMapSingle(
                        obs -> obs.reduce("", (t, n) -> t + (t.equals("") ? "" : "|") + n)
                )
                .subscribe(x -> System.out.println("[10] " + x));
    //----------------------------------------------------------------------------------
        Observable<String> s1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 100)
                .map(i -> "s1-" + i)
                .take(10);
        Observable<String> s2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .map(i -> "s2-" + i)
                .take(3);
        Observable<String> s3 = Observable.interval(2100, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 2100)
                .map(i -> "s3-" + i)
                .take(2);
        Observable<String> concatenated = Observable.concat(s1, s2, s3);
        Disposable d3 = concatenated.subscribe(x -> System.out.println("[11] " + x));
        Thread.sleep(4200);
        d3.dispose();
    //----------------------------------------------------------------------------------
        Observable<String> s12 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 100)
                .map(i -> "s1-" + i)
                .take(10);
        Observable<String> s22 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .map(i -> "s2-" + i)
                .take(3);
        Observable<String> s32 = Observable.interval(2100, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 2100)
                .map(i -> "s3-" + i)
                .take(2);
        Observable<String> concatenated2 = Observable.concat(s1, s2, s3);
        Disposable d32 = concatenated
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe(x -> System.out.println("[12] " + x));
        Thread.sleep(4200);
        d32.dispose();
    //----------------------------------------------------------------------------------


    }




}
