package com.myself.rx.examples.example4;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

public class ConsumerRunnable implements Runnable {

    private int nr;
    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable disposable) {
            //nothing
        }

        @Override
        public void onNext(Integer t) {
            if(t%nr == 0)
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

    private ConnectableObservable<Integer> source;

    public ConsumerRunnable(ConnectableObservable<Integer> source, int nr){
        this.source = source;
        this.nr = nr;

    }

    @Override
    public void run() {
        source.subscribe(observer);
    }
}
