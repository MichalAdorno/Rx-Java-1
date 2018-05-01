package com.myself.rx.examples.example5;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

public class ConsumerRunnable implements Runnable {

    private int nr;
    private Observer<Long> observer = new Observer<Long>() {
        @Override
        public void onSubscribe(Disposable disposable) {
            //nothing
        }

        @Override
        public void onNext(Long t) {
                System.out.println("[" + nr + "] " + t);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("[" + nr + "] completed!");
        }
    };

    private ConnectableObservable<Long> source;

    public ConsumerRunnable(ConnectableObservable<Long> source, int nr){
        this.source = source;
        this.nr = nr;

    }

    @Override
    public void run() {
        source.subscribe(observer);
    }
}
