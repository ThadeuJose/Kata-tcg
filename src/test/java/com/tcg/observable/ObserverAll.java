package com.tcg.observable;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import com.tcg.architecture.observer.Message;
import com.tcg.architecture.observer.Observable;
import com.tcg.architecture.observer.Observer;

public class ObserverAll implements Observer {

    private List<String> news;

    public ObserverAll() {
        news = new ArrayList<>();
    }

    public ObserverAll(Observable observable, String topic) {
        observable.addObserver(topic, this);
        news = new ArrayList<>();
    }

    public List<String> getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news.add(news);
    }

    @Override
    public void update(Message message) {
        setNews(message.getPayload(String.class));
    }
}
