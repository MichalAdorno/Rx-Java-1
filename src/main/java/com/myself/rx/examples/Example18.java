package com.myself.rx.examples;


import com.myself.rx.examples.example18.Pair;
import com.myself.rx.examples.example18.SourceThread;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;



public class Example18 {

    public static void show() throws Exception {
        //----------------------------------------------------------------------------------
        final int THREAD_NUMBER = 10;
        Subject<Pair> subject = PublishSubject
                .<Pair>create()
                .toSerialized();

        subject.subscribe(System.out::println); //CORRECT!

        Thread[] threads = new Thread[10];
        for(int i = 0; i < THREAD_NUMBER; i++){
            threads[i] = new Thread(new SourceThread(subject, i+1));
        }

        for(int i = 0; i < THREAD_NUMBER; i++){
            threads[i].start();
        }

        for(int i = 0; i < THREAD_NUMBER; i++){
            threads[i].join();
        }

        System.out.println("After Thread.join()");

        //----------------------------------------------------------------------------------





    }



}
