package com.myself.rx.examples;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Example8 {

    private static Object BlockingQueue;

    public static void show() throws InterruptedException {
        BlockingQueue<String> bq = new LinkedBlockingDeque<>();
        initQueue(bq);
        Observable<String> source = Observable.create(observableEmitter -> {
            try {
                while (!observableEmitter.isDisposed() && bq.size() > 0) {
                    String elem = bq.poll();
                    if (elem != null)
                        observableEmitter.onNext(elem);
                }
            } catch (Exception e) {
                observableEmitter.onError(e);
            }
        });

        Disposable disposable = source.subscribe(x -> {
//            Thread.sleep(100);
            System.out.println(x);
        });
//        Thread.sleep(1000);
        disposable.dispose();
    }

    private static void initQueue(BlockingQueue<String> bq) {
        BufferedReader reader;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(new File("/home/lxuserb/text.txt")));
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (int i = 0; i < words.length; i++) {
                    bq.offer(words[i]);
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}