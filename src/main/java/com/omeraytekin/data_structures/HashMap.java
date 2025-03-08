package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

/**
 * Chaining HashMap with LinkedList
 */
public class HashMap<K, V> extends AbstractMap<K, V> {
    List<List<Node<K, V>>> map;
    int mapSize;
    int nodeSize;

    public HashMap() {
        this(1000);
    }

    public HashMap(int mapSize) {
        map = new ArrayList<>(mapSize);
        this.mapSize = mapSize;
        this.nodeSize = 0;
    }

    public void put(K key, V val) {
        Node<K, V> newNode = new Node<>(key, val);
        List<Node<K, V>> nodes = getNodes(key);
        if (nodes == null) {
            nodes = new LinkedList<>();
            nodes.insert(newNode);
        } else {
            nodes.insert(newNode);
        }
        setNodes(key, nodes);
        nodeSize++;
    }

    public V get(K key) throws NoSuchElementException {
        List<Node<K, V>> nodes = getNodes(key);
        Node<K, V> nodeToSearch = new Node<>(key, null);
        if (nodes == null) {
            throw new NoSuchElementException("Key could not be found in map.");
        }

        try {
            int index = nodes.findIndex(nodeToSearch);
            return nodes.getAt(index).val();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Key could not be found in map.");
        }
    }

    public V remove(K key) throws NoSuchElementException {
        List<Node<K, V>> nodes = getNodes(key);
        Node<K, V> nodeToSearch = new Node<>(key, null);
        if (nodes == null) {
            throw new NoSuchElementException("Key could not be found in map.");
        }

        try {
            int index = nodes.findIndex(nodeToSearch);
            nodeSize--;
            return nodes.removeAt(index).val();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Key could not be found in map.");
        }
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            List<Node<K, V>> nodes = map.getAt(i);
            if (nodes != null) {
                for (int j = 0; j < nodes.size(); j++) {
                    values.insert(nodes.getAt(j).val());
                }
            }
        }
        return values;
    }

    public void clear() {
        for (int i = 0; i < map.size(); i++) {
            List<Node<K, V>> nodes = map.getAt(i);
            if (nodes != null) {
                for (int j = 0; j < nodes.size(); j++) {
                    Node<K, V> node = nodes.getAt(j);
                    node.setKey(null);
                    node.setVal(null);
                    nodes.setAt(j, null);
                }
                map.setAt(i, null);
            }
        }
        map = new ArrayList<>(mapSize);
        nodeSize = 0;
    }

    public int size() {
        return nodeSize;
    }

    private List<Node<K, V>> getNodes(K key) throws IndexOutOfBoundsException {
        int index = getHash(key);
        return map.getAt(index);
    }

    private void setNodes(K key, List<Node<K, V>> nodes) throws IndexOutOfBoundsException {
        int index = getHash(key);
        map.setAt(index, nodes);
    }

    private int getHash(K key) {
        int index;
        if (key == null) {
            index = 0;
        } else {
            index = key.hashCode() % mapSize;
        }
        return index;
    }

    private class Node<K, V> {
        private K key;
        private V val;

        private Node() {
        }

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K key() {
            return key;
        }

        public V val() {
            return val;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setVal(V val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            Node<K, V> node = (Node<K, V>) o;
            return key.equals(node.key());
        }
    }
}
