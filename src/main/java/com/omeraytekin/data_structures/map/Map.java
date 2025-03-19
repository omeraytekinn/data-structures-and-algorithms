package com.omeraytekin.data_structures.map;

import java.util.NoSuchElementException;

import com.omeraytekin.data_structures.list.List;

public interface Map<K, V> {
    public void put(K key, V val);

    public V get(K key) throws NoSuchElementException;

    public V remove(K key) throws NoSuchElementException;

    public List<V> values();

    // when implemented set -> public Set<V> keySet();

    public void clear();

    public int size();
}