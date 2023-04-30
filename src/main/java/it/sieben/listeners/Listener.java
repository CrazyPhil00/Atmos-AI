package it.sieben.listeners;

import java.util.ArrayList;
import java.util.List;

public interface Listener {

    List<Object> listeners = new ArrayList<>();

    default void addListener(Object listener) {
        listeners.add(listener);
    }

    default void dispatchEvent() {
        for (Object listener : listeners) {


        }
    }
}

