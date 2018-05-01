package com.myself.rx.examples;

import com.myself.rx.examples.example1.ObserverRunnable;
import io.reactivex.Observable;

public class Example1 {

    public static void show() throws InterruptedException {
        Observable<String> source = Observable.create(x -> {
            try {
                x.onNext("Ada");
                x.onNext("Basia");
                x.onNext("Czesława");
                x.onNext("Dusia");
                x.onNext("Ela");
                x.onNext("Feodosja");
                x.onNext("Gudrun");
                x.onNext("Henryka");
                x.onNext("Iga");
                x.onNext("Jadwiga");
                x.onNext("Katarzyna");
                x.onNext("Luba");
                x.onNext("Łucja");
                x.onNext("Mirosława");
                x.onNext("Nadia");
                x.onComplete();
            }catch(Exception e){
                x.onError(e);
            }
        });

        Thread[] observers = new Thread[7];
        for(int i = 0; i < observers.length; i++) {
            final int j = i + 1;
            Observable<String> filtered = source.filter(x -> x.length() <= j);
            observers[i] = new Thread(new ObserverRunnable<String>(filtered, j));
        }
        for(int i = 0; i < observers.length; i++)
            observers[i].start();
        for(int i = 0; i < observers.length; i++)
            observers[i].join();


    }
}
