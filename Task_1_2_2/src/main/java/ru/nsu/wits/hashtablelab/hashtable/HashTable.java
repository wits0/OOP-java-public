package ru.nsu.wits.hashtablelab.hashtable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashTable<K, V> implements Iterable<Entry<K, V>> {

    private static final int DEF_CAPACITY = 16;
    private static final float DEF_LOAD_FACTOR = 0.75f;

    private int capacity;
    private int size;
    private float loadFactor;
    private int modCount;
    private ArrayList<List<Entry<K, V>>> buckets;

    public HashTable() {
        this(DEF_CAPACITY);
    }

    public HashTable(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
        this.loadFactor = DEF_LOAD_FACTOR;
        this.buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) buckets.add(new ArrayList<>());
    }

    public V put(K key, V value) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexHash(key);
        List<Entry<K, V>> bucket = buckets.get(index);
        for (Entry<K, V> e : bucket) {
            if (e.getKey().equals(key)) {
                return e.setValue(value);
            }
        }
        if (size >= capacity * loadFactor) {
            resize();
            index = indexHash(key);
            bucket = buckets.get(index);
        }
        bucket.add(new Entry<>(key, value));
        modCount++;
        size++;
        return null;
    }

    public V update(K key, V value) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexHash(key);
        for (Entry<K, V> e : buckets.get(index)) {
            if (e.getKey().equals(key)) {
                return e.setValue(value);
            }
        }
        return null;
    }

    public V get(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexHash(key);
        for (Entry<K, V> e : buckets.get(index)) {
            if (e.getKey().equals(key)) return e.getValue();
        }
        return null;
    }

    public V remove(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexHash(key);
        List<Entry<K, V>> bucket = buckets.get(index);
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> e = bucket.get(i);
            if (e.getKey().equals(key)) {
                bucket.remove(i);
                modCount++;
                size--;
                return e.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexHash(key);
        for (Entry<K, V> e : buckets.get(index)) {
            if (e.getKey().equals(key)) return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTable<?, ?> other)) return false;
        if (this.size != other.size) return false;
        try {
            for (Entry<K, V> e : this) {
                Object otherValue = ((HashTable<K, Object>) other).get(e.getKey());
                if (!Objects.equals(e.getValue(), otherValue)) return false;
            }
            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (Entry<K, V> e : this) {
            h += Objects.hashCode(e.getKey()) ^ Objects.hashCode(e.getValue());
        }
        return h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Entry<K, V> e : this) {
            if (!first) sb.append(", ");
            sb.append(e);
            first = false;
        }
        return sb.append("}").toString();
    }

    int getCapacity() {
        return capacity;
    }

    int getModCount() {
        return modCount;
    }

    List<Entry<K, V>> getBucket(int index) {
        return buckets.get(index);
    }

    private int indexHash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        ArrayList<List<Entry<K, V>>> newBuckets = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) newBuckets.add(new ArrayList<>());
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> e : bucket) {
                int index = (e.getKey().hashCode() & 0x7fffffff) % newCapacity;
                newBuckets.get(index).add(e);
            }
        }
        this.buckets = newBuckets;
        this.capacity = newCapacity;
        this.modCount++;
    }
}