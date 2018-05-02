package com.myself.rx.examples;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

import java.util.concurrent.TimeUnit;

public class Example7 {
    public static final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static void show() throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);


        ResourceObserver<Long> myObserver1 = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };
        ResourceObserver<Long> myObserver2 = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };
        Disposable disposable1 = source.subscribeWith(myObserver1);
        Disposable disposable2 = source.subscribeWith(myObserver2);

        compositeDisposable.addAll(disposable1, disposable2);
        Thread.sleep(5000);
        compositeDisposable.dispose();
    }
}
