package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public class Example28 {

    public static void show() throws Exception {

        List<Long> times = Arrays.asList(1000L,2000L,4000L,1000L,3000L,2000L,1000L,4000L);

        //----------------------------------------------------------------------------------
        //SEQUENTIAL

        System.out.println("[1] Starting: - " + LocalTime.now());
        Observable.fromIterable(times)
                .subscribeOn(Schedulers.newThread())
                .map(i -> intenseCalculation(i))
                .doOnNext(s -> System.out.println("[1] " + s + " on [" + Thread.currentThread().getName() + "]"))
                .reduce(0L, (s, x) -> s + x)
                .subscribe(x -> System.out.println("[1] Ending: " + x + " - " + LocalTime.now()));

        Thread.sleep(20000);

        //----------------------------------------------------------------------------------
        //PARALLEL

        System.out.println("[2] Starting: - " + LocalTime.now());
        Observable.fromIterable(times)
                .flatMap(j -> Observable.just(j)
                                    .subscribeOn(Schedulers.newThread())
                                    .map(i -> intenseCalculation(i))
                )
                .doOnNext(s -> System.out.println("[2] " + s + " on [" + Thread.currentThread().getName() + "]"))
                .reduce(0L, (s, x) -> s + x)
                .subscribe(x -> System.out.println("[2] Ending: " + x + " - " + LocalTime.now()));

        Thread.sleep(20000);

        //----------------------------------------------------------------------------------



    }
    public static <T extends Long> T intenseCalculation(T value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

}
