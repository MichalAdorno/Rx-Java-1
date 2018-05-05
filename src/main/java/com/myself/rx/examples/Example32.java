package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public class Example32 {

    public static void show() throws Exception {

        //----------------------------------------------------------------------------------
        Observable.range(1, 999_999)
//                .subscribeOn(Schedulers.computation())
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {
                    Thread.sleep(100);
                    System.out.println("Received MyItem: " + myItem.id + " by [" + Thread.currentThread().getName() + "]");
                });
        Thread.sleep(10000);
        //----------------------------------------------------------------------------------


    }

    static final class MyItem{
        final int id;
        MyItem(int id){
            this.id = id;
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Constructing MyItem for: " + id + " by [" + Thread.currentThread().getName() + "]");
        }
    }

}
