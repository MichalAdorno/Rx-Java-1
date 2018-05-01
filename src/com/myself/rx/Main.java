package com.myself.rx;


import io.reactivex.Observable;


public class Main {

    public static void main(String[] args){
        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta");
        Thread[] observers = new Thread[3];
        for(int i=0; i < observers.length; i++)
            observers[i] = new Thread(new ObserverRunnable<String>(myStrings, i));

        for(Thread observer : observers)
            observer.start();

        for(Thread observer : observers)
            try {
                observer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        System.out.println("Finished!");
    }
}
