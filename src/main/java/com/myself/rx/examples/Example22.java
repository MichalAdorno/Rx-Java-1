package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.myself.rx.examples.Example20.intenseCalculation;

public class Example22 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------

        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<Integer> source2 = Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable.zip(source1, source2, (s,i) -> "[1] " + s + "-" + i)
                .blockingSubscribe(System.out::println);



        //----------------------------------------------------------------------------------

        Observable<String> source3 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<Integer> source4 = Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable.zip(source3, source4, (s,i) -> "[2] " + s + "-" + i)
                .blockingSubscribe(System.out::println);


        //----------------------------------------------------------------------------------

        Observable<String> source5 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable<Integer> source6 = Observable.range(1,6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation(s));

        Observable.merge(source5, source6)
                .map(s -> "[3] " + s)
                .blockingSubscribe(System.out::println); //hereby makes threads sequentially computing!


        //----------------------------------------------------------------------------------


    }




}
