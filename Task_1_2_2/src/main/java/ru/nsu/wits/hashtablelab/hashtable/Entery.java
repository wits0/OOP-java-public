package ru.nsu.wits.hashtablelab.hashtable;

import java.util.Objects;

final class Entry<K,V> {
    private K key;
    private V value;

    public K getKey(){
        return key;
    }
    public V getValue() {
        return value;
    }
    public V setValue(V newValue){
        V oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    public Entry(K key, V value){
        this.key = Objects.requireNonNull(key,"key must not be null!");
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString(){
        return key + "=" + value;
    }
}
