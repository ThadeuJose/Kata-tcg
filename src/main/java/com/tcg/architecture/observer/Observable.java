package com.tcg.architecture.observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Observable {
    private final Map<String, Set<Observer>> topicMap = new HashMap<String, Set<Observer>>();

    public Observable() {
    }

    public void addObserver(String topic, Observer observer) {
        Set<Observer> observers = topicMap.getOrDefault(topic, new HashSet<Observer>());
        observers.add(observer);
        topicMap.put(topic, observers);
    }

    public void removeObserver(String topic, Observer observer) {
        if (topicMap.containsKey(topic)) {
            Set<Observer> observers = topicMap.get(topic);
            observers.remove(observer);
            topicMap.put(topic, observers);
        }
    }

    public void sendMessage(Message message) {
        Set<Observer> observers = topicMap.get(message.getTopic());
        if (Objects.nonNull(observers)) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }

    }

}
