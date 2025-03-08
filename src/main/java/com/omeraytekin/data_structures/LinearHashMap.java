package com.omeraytekin.data_structures;

import java.util.NoSuchElementException;

/**
 * Linear Probing HashMap
 */
public class LinearHashMap<K, V> extends AbstractMap<K, V> {
    List<Node<K, V>> map;
    int mapSize;
    int nodeSize;
    int offset;
    float fillRate;
    float growFactor;

    public LinearHashMap() {
        this(1000);
    }

    public LinearHashMap(int mapSize) {
        this(mapSize, 2.0f, 0.75f, 1);
    }

    public LinearHashMap(int mapSize, float growFactor, float fillRate, int offset) {
        map = new ArrayList<>(mapSize);
        this.mapSize = mapSize;
        this.growFactor = growFactor;
        this.fillRate = fillRate;
        this.offset = offset;
        this.nodeSize = 0;
    }

    public void put(K key, V val) {
        if (size() > mapSize * fillRate) {
            growMap();
        }

        Node<K, V> newNode = new Node<>(key, val);
        int index = getFirstAvailableIndex(key);
        map.setAt(index, newNode);
        nodeSize++;
    }

    public V get(K key) throws NoSuchElementException {
        int index = findIndex(key);
        Node<K, V> node = map.getAt(index);

        if (node == null) {
            throw new NoSuchElementException("Key could not be found in map.");
        }
        return node.val();
    }

    public V remove(K key) throws NoSuchElementException {
        int index = findIndex(key);
        Node<K, V> node = map.getAt(index);

        if (node == null) {
            throw new NoSuchElementException("Key could not be found in map.");
        }

        node.setDeleted(true);
        node.setKey(null);
        node.setVal(null);
        nodeSize--;
        return node.val();
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            Node<K, V> node = map.getAt(i);
            if (node != null) {
                values.insert(node.val());
            }
        }
        return values;
    }

    public void clear() {
        for (int i = 0; i < map.size(); i++) {
            Node<K, V> node = map.getAt(i);
            if (node != null) {
                node.setKey(null);
                node.setVal(null);
                map.setAt(i, null);
            }
        }
        map = new ArrayList<>(mapSize);
        nodeSize = 0;
    }

    public int size() {
        return nodeSize;
    }

    private void growMap() {
        mapSize = (int) growFactor * mapSize;
        List<Node<K, V>> newMap = new ArrayList<>(mapSize);
        List<Node<K, V>> oldMap;

        // Here map and newMap swapped.
        // Hence new map act like map and putting values of old map will be
        // inserted to new map.
        oldMap = map;
        map = newMap;
        for (Node<K, V> node : oldMap) {
            put(node.key(), node.val());
        }

        // Here maps swapped again to clear old map.
        // With assigning old map to map, when clear method runs
        // it will clear the old map.
        newMap = map;
        map = oldMap;
        clear();

        // Lastly swap again to replace new map with old map to use
        // and assignin null to other variables and cleaning is done.
        map = newMap;
        oldMap = null;
        newMap = null;
    }

    private int getFirstAvailableIndex(K key) {
        int index = getHash(key);
        while (map.getAt(index) != null) {
            if (map.getAt(index).deleted() == true) {
                return index;
            }
            index = (index + offset) % mapSize;
        }
        return index;
    }

    private int findIndex(K key) {
        int index = getHash(key);
        while (map.getAt(index) != null) {
            if (key == null) {
                if (map.getAt(index).deleted() != true && map.getAt(index).key() == null) {
                    return index;
                }
            } else {
                if (map.getAt(index).deleted() != true && map.getAt(index).key().equals(key)) {
                    return index;
                }
            }
            index = (index + offset) % mapSize;
        }
        return index;
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

    private class Node<Y, Z> {
        private Y key;
        private Z val;
        private boolean deleted;

        @SuppressWarnings("unused")
        private Node() {
        }

        public Node(Y key, Z val) {
            this.key = key;
            this.val = val;
            deleted = false;
        }

        public Y key() {
            return key;
        }

        public Z val() {
            return val;
        }

        public boolean deleted() {
            return deleted;
        }

        public void setKey(Y key) {
            this.key = key;
        }

        public void setVal(Z val) {
            this.val = val;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }
}
