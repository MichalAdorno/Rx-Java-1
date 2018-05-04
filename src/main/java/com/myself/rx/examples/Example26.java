package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.myself.rx.examples.Example20.intenseCalculation;

public class Example26 {

    public static void show() throws Exception {
        //----------------------------------------------------------------------------------
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
        //--
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Scheduler scheduler = Schedulers.from(executorService);

        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(scheduler)
                .map(s -> intenseCalculation(s));
//                .doFinally(executorService::shutdown);

        Observable<Integer> source2 = Observable.range(1, 6)
                .subscribeOn(scheduler)
                .map(s -> intenseCalculation(s));
//                .doFinally(executorService::shutdown);

        Observable<String> zipped1 = Observable.zip(source1, source2, (s, i) -> "[1] " + s + "-" + i).doFinally(executorService::shutdown);


        zipped1.blockingSubscribe(observer);
        zipped1.doFinally(() -> executorService.awaitTermination(10, TimeUnit.SECONDS));
        System.out.println("Done!");

        //----------------------------------------------------------------------------------

    }

}
