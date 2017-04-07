package com.tianxing.storage.memory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tianxing on 2017/4/6.
 *
 */

public class LruCache<K, V> {
    private android.util.LruCache<String, String> cache;

    private HashMap<K, V> map;
    private List<K> list;


    private int size;
    private int maxSize;

    public LruCache(){
        map = new HashMap<>();
        list = new LinkedList<>();
    }



    public final V get(K k){
        return null;
    }
}
