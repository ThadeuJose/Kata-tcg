package com.tcg.observable;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.tcg.architecture.observer.Observable;

public class ObservableTest {

    @Test
    public void shouldSendMessage() {
        Observable observable = new Observable();
        NewsObservable newsObservable = new NewsObservable(observable);

        ObserverNews observer = new ObserverNews(observable, "news");

        newsObservable.setNews("news");

        assertEquals("news", observer.getNews());
    }

    @Test
    public void shouldSendMessageToMultipleObservers() {
        Observable observable = new Observable();
        NewsObservable newsObservable = new NewsObservable(observable);
        TelevisionObservable televisionObservable = new TelevisionObservable(observable);

        ObserverAll observerAll = new ObserverAll(observable, "all");

        ObserverNews observerNews = new ObserverNews(observable, "news");

        ObserverNews observerTelevision = new ObserverNews(observable, "television");

        newsObservable.setNews("news");
        televisionObservable.setNews("television");

        assertEquals("news", observerNews.getNews());
        assertEquals("television", observerTelevision.getNews());
        assertEquals(Arrays.asList("news", "television"), observerAll.getNews());
    }

    @Test
    public void shouldRemoveObserver() {
        Observable observable = new Observable();
        NewsObservable newsObservable = new NewsObservable(observable);

        ObserverAll observerAll = new ObserverAll(observable, "news");

        newsObservable.setNews("news");
        observable.removeObserver("news", observerAll);
        newsObservable.setNews("news");

        assertEquals(Arrays.asList("news"), observerAll.getNews());
    }
    /*
     * TODO
     * Should raise exception if no listener
     * Should raise exception if there is no listener in this channel and try to
     * remove
     * Pass observable no constructor
     */
}
