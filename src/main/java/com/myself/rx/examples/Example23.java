package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.myself.rx.examples.Example20.intenseCalculation;

public class Example23 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------

        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<Integer> source2 = Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<String> zipped1 = Observable.zip(source1, source2, (s,i) -> "[1] " + s + "-" + i);

        Observable<String> source3 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<Integer> source4 = Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<String> zipped2 = Observable.zip(source3, source4, (s,i) -> "[2] " + s + "-" + i);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("Disposed!");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Observer: " + s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
            }
        };
        zipped1.blockingSubscribe(x -> System.out.println("zipped1 " + x));
        zipped2.blockingSubscribe(x -> System.out.println("zipped2 " + x));

        //----------------------------------------------------------------------------------

    }




}
