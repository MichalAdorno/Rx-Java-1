package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public class Example29 {

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
                        .subscribeOn(Schedulers.computation())
                        .map(i -> intenseCalculation(i))
                )
                .subscribe(s -> System.out.println("[1] " + s + " on [" + Thread.currentThread().getName() + "]"));

        Thread.sleep(60000);

        //----------------------------------------------------------------------------------
        /*
            [B, G, O]
            [AA, EE, MM, NN]
            [BBB, FFF, LLL, PPP]
            [CCCC, IIII, RRRR]
            [HHHHH, KKKKK, QQQQQ]
            [JJJJJJ]

            [1] B on [RxComputationThreadPool-1]
            [1] G on [RxComputationThreadPool-1]
            [1] O on [RxComputationThreadPool-1]

            [1] AA on [RxComputationThreadPool-2]
            [1] EE on [RxComputationThreadPool-2]
            [1] MM on [RxComputationThreadPool-2]
            [1] NN on [RxComputationThreadPool-2]

            [1] BBB on [RxComputationThreadPool-3]
            [1] FFF on [RxComputationThreadPool-3]
            [1] LLL on [RxComputationThreadPool-3]
            [1] PPP on [RxComputationThreadPool-3]

            [1] CCCC on [RxComputationThreadPool-4]
            [1] IIII on [RxComputationThreadPool-4]
            [1] RRRR on [RxComputationThreadPool-4]

            [1] HHHHH on [RxComputationThreadPool-1]
            [1] KKKKK on [RxComputationThreadPool-1]
            [1] QQQQQ on [RxComputationThreadPool-1]

            [1] JJJJJJ on [RxComputationThreadPool-2]
         */


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
