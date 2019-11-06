package com.company.HomeWork_2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MyMap<K, V> implements SimpleMap {

    private static final int START_SIZE = 16;
    private int size = 0;
    private Node[] table;
    private double load_factor = 0.75;

    public static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        final int hash;

        Node(K key, V value, int hash) {
            this.value = value;
            this.key = key;
            this.hash = hash;
        }
    }

    MyMap() {
        table = new Node[START_SIZE];
    }

    MyMap(int startSize) {
        table = new Node[startSize];
    }

    MyMap(double load_factor) {
        this.load_factor = load_factor;
        table = new Node[START_SIZE];
    }

    MyMap(int startSize, double load_factor) {
        this.load_factor = load_factor;
        table = new Node[startSize];
    }

    @Override
    public V put(Object key, Object value) {
        int hash = key.hashCode();
        int index = Math.abs(key.hashCode() % table.length);
        Node<K, V> node = table[index];
        if (node == null) {
            node = new Node(key, value, hash);
            table[index] = new Node(key, value, hash);
        } else {
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = (V) value;
                    break;
                }
                node = node.next;
            }
        }
        size++;
        if (size >= table.length * load_factor) {
            Node[] oldTable = table;
            table = new Node[2 * table.length];
            size = 0;
            for (int i = 0; i < oldTable.length; i++) {
                Node nodeForChange = oldTable[i];
                if (nodeForChange == null)
                    continue;
                while (nodeForChange != null) {
                    this.put(nodeForChange.key, nodeForChange.value);
                    nodeForChange = nodeForChange.next;
                }
            }
        }
        return node.value;
    }

    @Override
    public Object get(Object key) {
        int index = Math.abs(key.hashCode() % table.length);
        Node<K, V> node = table[index];
        if (node == null)
            return null;
        while (!node.key.equals(key)) {
            node = node.next;
            if (node == null)
                return null;
        }
        return node.value;
    }

    @Override
    public Object remove(Object key) {
        int index = Math.abs(key.hashCode() % table.length);
        Node<K, V> node = table[index];
        if (node == null)
            return null;
        while (node != null) {
            if (node.key.equals(key)) {
                Node<K, V> val = table[index];
                table[index] = node.next;
                size--;
                return val.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean contains(Object key) {
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            if (node == null)
                continue;
            while (node != null) {
                if (node.key.equals(key))
                    return true;
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set keySet() {
        Set set = new TreeSet();
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            if (node == null)
                continue;
            while (node != null) {
                set.add(node.key);
                node = node.next;
            }
        }

        return set;
    }

    @Override
    public Collection values() {
        Collection collection = new ArrayList();
        for (int i = 0; i < table.length; i++) {
            Node node = table[i];
            if (node == null)
                continue;
            while (node != null) {
                collection.add(node.value);
                node = node.next;
            }
        }
        return collection;
    }

    public int arrayLength() {
        return table.length;
    }
}
