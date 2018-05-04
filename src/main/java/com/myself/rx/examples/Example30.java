package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public class Example30 {

    public static void show() throws Exception {

        List<String> words = Arrays.asList("AA", "B", "BBB", "CCCC", "EE", "FFF", "G", "HHHHH", "IIII", "JJJJJJ", "KKKKK", "LLL", "MM", "NN", "O", "PPP", "QQQQQ", "RRRR");

        //----------------------------------------------------------------------------------
        //PARALLEL
        System.out.println("[1] Starting: - " + LocalTime.now());
        Observable.fromIterable(words)
                .groupBy(s -> s.length())
                .flatMapSingle(i -> i.toList())
                .doOnNext(System.out::println)
                .flatMap(j -> Observable.fromIterable(j)
                        .observeOn(Schedulers.io())
                        .map(i -> intenseCalculation(i))
                )
                .subscribe(s -> System.out.println("[1] " + s + " on [" + Thread.currentThread().getName() + "]"));

        Thread.sleep(60000);

        //----------------------------------------------------------------------------------


    }
    public static String intenseCalculation(String value) {
        try {
            Thread.sleep(value.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

}
