package com.myself.rx.examples.example18;


import io.reactivex.subjects.Subject;

import java.util.concurrent.ThreadLocalRandom;

public class SourceThread implements Runnable {

    public Subject getSubject() {
        return subject;
    }

    public int getThreadName() {
        return threadName;
    }

    private Subject subject;
    private int threadName;

    public SourceThread(Subject subject, int threadName){
        this.subject = subject;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        int rndValue = ThreadLocalRandom.current().nextInt(100000);
        Pair pair = new Pair(threadName, rndValue);
        subject.onNext(pair);

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        subject.onComplete(); //non-deterministic behaviour

    }

    @Override
    public String toString() {
        return "SourceThread{" +
                "subject=" + subject +
                ", threadName=" + threadName +
                '}';
    }
}
