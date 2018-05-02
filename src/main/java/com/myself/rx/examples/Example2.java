package com.myself.rx.examples;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Example2 {

    public static void show() {
        Observable<String> source = Observable.create(x -> {
            try {
                x.onNext("Ada");
                x.onNext("Basia");
                x.onNext("Czesława");
                x.onNext("Dusia");
                x.onNext("Ela");
                x.onNext("Fieodosja");
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
            } catch (Exception e) {
                x.onError(e);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                //do nothing
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Length is: " + value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };

        source
                .map(x -> x.length())
                .sorted()
                .subscribe(observer);


    }
}
