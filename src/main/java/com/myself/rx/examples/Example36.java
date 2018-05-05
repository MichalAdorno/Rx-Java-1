package com.myself.rx.examples;


import com.myself.rx.examples.tools.Tools;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


public class Example36 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        Flowable<Integer> source = Flowable.create(emitter -> {
            for (int i=0; i<=10000; i++) {
                if (emitter.isCancelled())
                    return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        source.observeOn(Schedulers.io())
                .subscribe(x -> {
                    Thread.sleep(10);
                    Tools.<Integer>print(1, x);
                });

        Thread.sleep(1000);
        //----------------------------------------------------------------------------------
        Flowable<Integer> source0 = Flowable.create(emitter -> {
            for (int i=0; i<=10000; i++) {
                if (emitter.isCancelled())
                    return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.LATEST);

        source0.observeOn(Schedulers.io())
                .subscribe(x -> {
                    Thread.sleep(100);
                    Tools.<Integer>print(2, x);
                });

        Thread.sleep(1000);
        //----------------------------------------------------------------------------------
        Flowable<Integer> source1 = Flowable.create(emitter -> {
            for (int i=0; i<=10000; i++) {
                if (emitter.isCancelled())
                    return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.MISSING);

        source1.onBackpressureBuffer()
                .observeOn(Schedulers.io())
                .subscribe(x -> {
                    Thread.sleep(10);
                    Tools.<Integer>print(3, x);
                });

        Thread.sleep(1000);
        //----------------------------------------------------------------------------------

    }

}
