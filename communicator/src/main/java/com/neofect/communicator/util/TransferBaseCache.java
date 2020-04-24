package com.neofect.communicator.util;

import android.util.Log;

import java.util.ArrayDeque;

/**
 * Created by jhchoi on 2020/04/23
 * jhchoi@neofect.com
 */
public abstract class TransferBaseCache<T> {
    public abstract String getTAG();

    public abstract int getDequeSize();

    public abstract T createCache();

    protected ArrayDeque<T> deque = new ArrayDeque();

    public TransferBaseCache() {
        clear();
    }

    public void clear() {
        synchronized (this) {
            deque.clear();
        }
    }

    public T pollCache() {
        T result;
        synchronized (this) {
            if (deque.peek() == null && deque.size() < getDequeSize()) {
                deque.push(createCache());
            }

            result = deque.poll();
            if (result == null) {
                Log.w(getTAG(), "cache is none.");
            }
        }
        return result;
    }

    public void returnCache(T cache) {
        synchronized (this) {
            deque.push(cache);
        }
    }

}
