package com.tcg.observable;

import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;
import com.tcg.architecture.observer.Observer;

public class ObserverNews implements Observer {

    private String news;

    public ObserverNews() {

    }

    public ObserverNews(Observable observable, String topic) {
        observable.addObserver(topic, this);
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public void update(Message message) {
        setNews(message.getPayload(String.class));
    }
}
