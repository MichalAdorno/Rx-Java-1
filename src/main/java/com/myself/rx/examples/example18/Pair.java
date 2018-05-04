package com.myself.rx.examples.example18;


public class Pair {

    public int threadName;
    public int rndValue;

    public Pair(int threadName, int rndValue){
        this.threadName = threadName;
        this.rndValue = rndValue;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "threadName=" + threadName +
                ", rndValue=" + rndValue +
                '}';
    }
}
