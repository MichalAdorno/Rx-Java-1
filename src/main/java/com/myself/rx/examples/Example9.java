package com.myself.rx.examples;

import io.reactivex.Observable;

import java.util.HashSet;

public class Example9 {
    public static void show() throws Exception {
        Observable.range(1, 100)
                .map(i -> i % 20)
                .skip(50)
                .skipWhile(i -> i > 0)
                .takeWhile(i -> i < 10)
                .distinct()
                .map(i -> Integer.toString(i))
                .startWith("SELECTED")
                .repeat(2)
                .startWith("NICHT WIEDERHOLENDER UBERTITEL")
                .subscribe(System.out::println);

        Observable.range(1, 100)
                .map(i -> i % 20)
                .skip(50)
                .skipWhile(i -> i > 0)
                .takeWhile(i -> i < 10)
                .scan(0, (acc, x) -> acc + x)
                .startWith(-1)
//                .all(x -> x < 50)
//                .contains(true)
//                .toMap(x -> x%10)
//                .toList()
                .collect(HashSet::new, HashSet::add)
                .subscribe(System.out::println);
    }


}
