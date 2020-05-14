package com.redbee.goldsample;

import org.junit.ClassRule;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class RxAndroidDemo {
    @ClassRule
    public static RxSchedulersOverrideRule sSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Test
    public void rxJavaTest1()
    {
        Observable novel = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Chapter 1");
                emitter.onNext("Chapter 2");
                emitter.onNext("chapter 3");
                emitter.onNext("finished");
                emitter.onNext("eggs");
                emitter.onComplete();
                emitter.onComplete();
            }
        });

        //Relationship between Observer and Observable
        final Disposable[] mDisposable = {null};
        Observer<String> reader1 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable[0] = d;
                System.out.print("Reader1 on subscriber.\n");
            }

            @Override
            public void onNext(String s) {
                if(s.equals("finished"))
                {
                    //Do not subscribe anymore.
                    mDisposable[0].dispose();
                }
                System.out.print("Reader1 on next:" + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                System.out.print("Reader1 on error:" + e.toString() + "\n");
            }

            @Override
            public void onComplete() {
                System.out.print("Reader1 on complete.\n");
            }
        };

        Observer<String> reader2 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.print("Reader2 on subscribe.\n");
            }

            @Override
            public void onNext(String s) {
                System.out.print("Reader2 on next:" + s + "\n");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.print("Reader2 on complete.\n");
            }
        };

        novel.subscribe(reader1);
        novel.subscribe(reader2);
    }
    @Test
    public void rxJavaTest2()
    {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                sleep(3000);
                emitter.onNext(456);
            }
        }).observeOn(AndroidSchedulers.mainThread())//
                .subscribeOn(Schedulers.io())//If not set then product and consumer in the same thread
                .subscribe(new Consumer<Integer>() {
                               @Override
                               public void accept(Integer integer) throws Exception {
                                    System.out.print("Accept:" + integer + "\n");
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
    }
}
