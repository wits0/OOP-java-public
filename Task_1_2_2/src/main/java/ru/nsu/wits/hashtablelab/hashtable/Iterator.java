package ru.nsu.wits.hashtablelab.hashtable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class HashTableIterator<K, V> implements Iterator<Entry<K, V>> {

    private final HashTable<K, V> table;
    private final int expectedModCount;

    private int bucketIndex;
    private int entryIndex;
    private Entry<K, V> lastReturned;
    private boolean canRemove;

    HashTableIterator(HashTable<K, V> table) {
        this.table = table;
        this.expectedModCount = table.getModCount();
        this.bucketIndex = 0;
        this.entryIndex = -1;
        this.canRemove = false;
    }

    @Override
    public boolean hasNext() {
        checkForComodification();
        int b = bucketIndex;
        int e = entryIndex + 1;
        while (b < table.getCapacity()) {
            if (e < table.getBucket(b).size()) return true;
            b++;
            e = 0;
        }
        return false;
    }

    @Override
    public Entry<K, V> next() {
        checkForComodification();
        while (bucketIndex < table.getCapacity()) {
            var bucket = table.getBucket(bucketIndex);
            if (entryIndex + 1 < bucket.size()) {
                entryIndex++;
                lastReturned = bucket.get(entryIndex);
                canRemove = true;
                return lastReturned;
            }
            bucketIndex++;
            entryIndex = -1;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void checkForComodification() {
        if (table.getModCount() != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}