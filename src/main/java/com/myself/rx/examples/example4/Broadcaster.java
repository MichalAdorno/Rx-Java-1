package com.myself.rx.examples.example4;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Broadcaster implements Runnable{

    private ConnectableObservable<Integer> source;
    public ConnectableObservable<Integer> getSource(){
        return source;
    }
    public Broadcaster(Observable<Integer> observable){
        source = observable.publish();
        this.source = source;
    }


    @Override
    public void run() {
        source.connect();
    }
}
