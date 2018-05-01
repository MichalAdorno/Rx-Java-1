package com.myself.rx;

import io.reactivex.Observable;

public class ObserverRunnable<T> implements Runnable {

    Observable<T> observable;
    int nr;
    public ObserverRunnable(Observable<T> observable, int nr){
        this.observable = observable;
        this.nr = nr;
    }

    @Override
    public void run() {
        observable.subscribe(x -> System.out.println("[Thread-"+ nr +"]" + x));
    }
}
