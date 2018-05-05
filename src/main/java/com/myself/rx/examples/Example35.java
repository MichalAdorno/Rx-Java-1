package com.myself.rx.examples;


import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;


public class Example35 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            Subscription subscription;
            AtomicInteger count = new AtomicInteger(0);

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                System.out.println("Requesting 20 items!");
                subscription.request(20);
            }

            @Override
            public void onNext(Integer s) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Subscriber received " + s);
                if (count.incrementAndGet() % 10 == 0 && count.get() >= 20)
                    System.out.println("Requesting 10 more!");
                subscription.request(10);
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
