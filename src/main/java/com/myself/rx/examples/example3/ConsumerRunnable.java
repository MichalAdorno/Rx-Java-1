package com.myself.rx.examples.example3;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ConsumerRunnable implements Runnable {

    private int nr;
    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable disposable) {
            //nothing
        }

        @Override
        public void onNext(Integer t) {
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

    private Observable<Integer> source;

    public ConsumerRunnable(Observable<Integer> source, int nr){
        this.source = source.filter(x -> x%nr == 0);
        this.nr = nr;

    }

    @Override
    public void run() {
        source.subscribe(observer);
    }
}
