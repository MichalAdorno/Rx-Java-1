package com.myself.rx.examples;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class Example12 {

    public static void show() throws Exception {

        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta","Epsilon");

        Observable<GroupedObservable<Integer,String>> byLengths = source.groupBy(s -> s.length());

        byLengths
                .flatMapSingle(grp -> grp.toList())
                .subscribe(System.out::println);


    }


}
