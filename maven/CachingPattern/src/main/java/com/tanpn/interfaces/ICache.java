package com.tanpn.interfaces;

public interface ICache<K, V> {
    public V get(K pKey);
    public default V getIfPresent(K key) {
        return this.get(key);
    }
    public void set(K pKey, V pValue);
}
