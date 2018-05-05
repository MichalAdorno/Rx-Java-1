package com.myself.rx.examples;


import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.util.concurrent.ThreadLocalRandom;


public class Example34 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Subscriber received: " + integer);

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

        Flowable<Integer> flowable = Flowable.range(1,1000)
                .doOnNext(s -> System.out.println("Source pushed: " + s))
                .observeOn(Schedulers.io())
                .map(i -> intenseCalculation(i));

        flowable.subscribe(subscriber);


        Thread.sleep(20000);

        //----------------------------------------------------------------------------------


    }

    private static <T> T intenseCalculation(T value) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(200));
        return value;
    }

}
