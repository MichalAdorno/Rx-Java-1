package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

public class Example17 {

    public static void show() throws Exception {
        //----------------------------------------------------------------------------------
        Subject<String> subject = PublishSubject.create();

//        subject.map(String::length).subscribe(System.out::println); //CORRECT!
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
        subject.map(String::length).subscribe(System.out::println); //SHOULD PLACED BEFORE!

        //----------------------------------------------------------------------------------





    }



}
