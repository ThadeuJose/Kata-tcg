package com.tcg.observable;

import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;

public class TelevisionObservable {
    private Observable observable;

    public TelevisionObservable(Observable observable) {
        this.observable = observable;
    }

    public void setNews(String value) {
        observable.sendMessage(new Message("all", value));
        observable.sendMessage(new Message("television", value));
    }

}
