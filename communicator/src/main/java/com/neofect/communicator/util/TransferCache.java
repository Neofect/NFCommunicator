package com.neofect.communicator.util;

import android.util.Log;

/**
 * Created by jhchoi on 2020/04/23
 * jhchoi@neofect.com
 */
public class TransferCache extends TransferBaseCache<byte[]> {
    private static final String TAG = "TransferCache";
    private static final int DEQUE_SIZE = 100;
    public static TransferCache INSTANCE = new TransferCache();

    private TransferCache() {
        super();
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public int getDequeSize() {
        return DEQUE_SIZE;
    }

    @Override
    public byte[] createCache() {
        return new byte[0]; //not use.
    }

    public byte[] pollCache(int cacheSize) {
        synchronized (this) {
            if (deque.peek() == null && deque.size() < DEQUE_SIZE) {
                deque.push(new byte[cacheSize]);
            }

            byte[] cache = deque.poll();
            if (cache.length != cacheSize) {
                deque.push(new byte[cacheSize]);
                cache = deque.poll();
            }
            if (cache == null) {
                Log.w(TAG, "cache is none.");
            }
            return cache;
        }
    }
}
