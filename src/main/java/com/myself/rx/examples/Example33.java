package com.myself.rx.examples;


import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static com.myself.rx.examples.Example20.intenseCalculation;


public class Example33 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        try {
            Disposable d0 = Flowable.interval(1, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.io())
                    .map(i -> intenseCalculation(i))
                    .subscribe(x -> System.out.println("[1] " + x),Throwable::printStackTrace);


            Thread.sleep(10000);
            d0.dispose();
        }catch (MissingBackpressureException mbe){
            System.out.println("Note: this Flowable factory does not support backpressure!");
        }
        //----------------------------------------------------------------------------------
        Disposable d1 = Flowable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .map(i -> intenseCalculation(i))
                .subscribe(x -> System.out.println("[2] " + x),Throwable::printStackTrace);


        Thread.sleep(10000);
        d1.dispose();
        //----------------------------------------------------------------------------------
        Disposable d2 = Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .observeOn(Schedulers.io())
                .map(i -> intenseCalculation(i))
                .subscribe(x -> System.out.println("[3] " + x),Throwable::printStackTrace);


        Thread.sleep(10000);
        d2.dispose();

    }


}
