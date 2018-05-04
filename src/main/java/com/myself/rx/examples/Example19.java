package com.myself.rx.examples;



import io.reactivex.subjects.*;


public class Example19 {

    public static void show() throws Exception {
        //----------------------------------------------------------------------------------
        Subject<String> subject = AsyncSubject.create();
        subject.subscribe(
                s -> System.out.println("[1] AsyncSubject 1: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[1] AsyncSubject 1 done!")
        );
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
        subject.subscribe(s ->
                        System.out.println("[1] AsyncSubject 2: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[1] AsyncSubject 2 done!")
        );

        //----------------------------------------------------------------------------------
        Subject<String> subject2 = ReplaySubject.create();
        subject2.subscribe(
                s -> System.out.println("[2] ReplaySubject 1: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[2] ReplaySubject 1 done!")
        );
        subject2.onNext("Alpha");
        subject2.onNext("Beta");
        subject2.onNext("Gamma");
        subject2.onComplete();
        subject2.subscribe(s ->
                        System.out.println("[2] ReplaySubject 2: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[2] ReplaySubject 2 done!")
        );
        //----------------------------------------------------------------------------------
        Subject<String> subject3 = PublishSubject.create();
        subject3.subscribe(
                s -> System.out.println("[3] PublishSubject 1: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[3] PublishSubject 1 done!")
        );
        subject3.onNext("Alpha");
        subject3.onNext("Beta");
        subject3.onNext("Gamma");
        subject3.onComplete();
        subject3.subscribe(s ->
                        System.out.println("[3] PublishSubject 2: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[3] PublishSubject 2 done!")
        );
        //----------------------------------------------------------------------------------
        Subject<String> subject4 = BehaviorSubject.create();
        subject3.subscribe(
                s -> System.out.println("[4] BehaviorSubject 1: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[4] BehaviorSubject 1 done!")
        );
        subject4.onNext("Alpha");
        subject4.onNext("Beta");
        subject4.onNext("Gamma");
        subject4.onComplete();
        subject4.subscribe(s ->
                        System.out.println("[4] BehaviorSubject 2: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("[4] BehaviorSubject 2 done!")
        );
        //----------------------------------------------------------------------------------



    }



}
