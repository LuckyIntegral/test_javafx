package com.example.testfx.reactive;

import com.example.testfx.config.LoaderPage;
import io.reactivex.Observer;
import io.reactivex.subjects.BehaviorSubject;

public class PubSubRX {
    private static final PubSubRX instance = new PubSubRX();
    private final BehaviorSubject<LoaderPage> behaviorSubject =
            BehaviorSubject.createDefault(LoaderPage.DEPARTMENT);

    public static PubSubRX getInstance() {return instance;}
    private PubSubRX() {}

    public void publish(LoaderPage loaderPage) {
        behaviorSubject.onNext(loaderPage);
    }

    public void subscribe(Observer<LoaderPage> observable) {
        behaviorSubject.subscribe(observable);
    }
}
