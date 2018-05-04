package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;


public class Example27 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        List<String> codes = Arrays.asList("WHISKEY/27653/TANGO", "6555/BRAVO", "232535/567675/FOXTROT");

        Observable.fromIterable(codes)

                .subscribeOn(Schedulers.io())       //the actual placing does not matter

                .flatMap(s -> Observable.fromArray(s.split("/")))
                .doOnNext(s -> System.out.println("[1]> " + s + " on [" + Thread.currentThread().getName() + "]"))

                .observeOn(Schedulers.computation())
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .reduce(0, (sum, x) -> sum + x)
                .doOnSuccess(i -> System.out.println("[1]> " + i + " on [" + Thread.currentThread().getName() + "]"))

                .observeOn(Schedulers.io())
                .map(i -> i.toString())
                .doOnSuccess(s -> System.out.println("[1]> Writing: " + s + " on [" + Thread.currentThread().getName() + "]"))

                .subscribe(s -> write(s, "/home/lxuserb/Desktop/output.txt"));


        Thread.sleep(3000);


        //----------------------------------------------------------------------------------

    }

    private static void write(String text, String path) {
        BufferedWriter writer = null;
        try {
            //create a temporary file
            File file = new File(path);
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }


}
