package com.myself.rx.examples;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Example10 {

    public static void show() throws Exception {

        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<String> source2 = Observable.just("A", "B", "C", "D");

        Observable.merge(source1, source2)
                .subscribe(x -> System.out.println("[1] MERGED: " + x));

        source1.mergeWith(source1)
                .subscribe(x -> System.out.println("[2] MERGED: " + x));

        Observable<String> source3 = Observable.interval(1, TimeUnit.SECONDS)
                .map(x -> "[3][  1] MERGED: " + x);
        Observable<String> source4 = Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(x -> "[3][0.5] MERGED: " + x);
        Observable<String> merged1 = Observable.merge(source3, source4);

        merged1.subscribe(System.out::println);

        Thread.sleep(5000);

        Observable<String> source5 = Observable.just("AA", "BB BB", "CC CC CC");

        source5.flatMap(x -> Observable.fromArray(x.split(" ")))
            .subscribe(x -> System.out.println("[4] FLATMAP: " + x));

        Observable<List<String>> source6 = Observable.just(
                Arrays.asList("A0", "A1", "A2", "A3"),
                Arrays.asList("B4", "B5", "B6", "B7"),
                Arrays.asList("C9", "C9", "C9", "C9")
        );

        Observable<Integer> fm1 = source6.flatMap(x -> Observable.fromIterable(x))
                .flatMap(x -> Observable.fromArray(x.split("")))
                .filter(x -> x.matches("[0-9]+"))
                .distinct()
                .map(Integer::valueOf)
                .scan(0, (acc, next) -> acc + next);
        fm1.lastElement().subscribe(x -> System.out.println("[5] FLATMAP: " + x));

        Observable<String> source7 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source7.flatMap(s -> Observable.fromArray(s.split("")), (s,r) -> s + "-" + r)
                .subscribe(System.out::println);



    }




}
