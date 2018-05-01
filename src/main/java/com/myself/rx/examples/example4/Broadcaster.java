package com.myself.rx.examples.example4;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Broadcaster implements Runnable{

    private ConnectableObservable<Long> source;
    public ConnectableObservable<Long> getSource(){
        return source;
    }
    public Broadcaster(Observable<Long> observable){
        source = observable.publish();
        this.source = source;
    }


    @Override
    public void run() {
        source.connect();
    }
}
