package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int capacity;

    public LRUCache(int capacity){
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> myCache = new LRUCache<>(3);
        myCache.put(1,1);
        myCache.put(2,2);
        System.out.println("myCache = " + myCache);
        myCache.put(3,3);
        myCache.put(4,4);
        System.out.println("myCache = " + myCache);
        System.out.println(" Check first index = " + myCache.get(1));
        System.out.println(" Check second index = " + myCache.get(2));
        myCache.put(9,9);
        System.out.println("myCache = " + myCache);
    }
}
