package me.std.flutterbridge.bridge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roger Huang on 2019/3/16.
 */

public class BridgeEventSubscribeManager<T> {

    private Map<String, Set<T>> subscriberMap = new HashMap<>();

    public void subscribe(String name, T subsriber) {
        if (name == null || subsriber == null) return;

        if (!subscriberMap.containsKey(name)) {
            subscriberMap.put(name, new HashSet<T>());
        }

        Set<T> subscribers = subscriberMap.get(name);
        subscribers.add(subsriber);
    }

    public void unsubscribe(T subscriber) {
        for (String name: subscriberMap.keySet()) {
            unsubscribe(name, subscriber);
        }
    }

    public void unsubscribe(String name, T subscriber) {
        if (name == null || subscriber == null) return;

        Set<T> subscribers = subscriberMap.get(name);

        if (subscribers != null) {
            subscribers.remove(subscriber);
        }
    }

    public Set<T> getSubscribers(String name) {
        if (subscriberMap.containsKey(name)) {
            return subscriberMap.get(name);
        }

        return new HashSet<>();
    }

    public boolean hasSubscriber(String name) {
        return getSubscribers(name).size() > 0;
    }
}
