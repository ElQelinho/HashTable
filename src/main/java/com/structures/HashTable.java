package com.structures;


class Node<K,V> {
        K key;
        V value;
        Node<K,V> nextNode;
}
class SuperHashMap<K,V> {
        int size = 0;
        Node<K,V>[] data;
        SuperHashMap (int length) {
                data = (Node<K, V>[]) new Node[length];
        }
        public void put(K key, V value) {
                int hash = key.hashCode();
                int index = Math.abs(hash % data.length);
                Node<K,V> node =  new Node<K, V>();
                node.value = value;
                node.key = key;

                if (data[index] != null) {
                        Node<K, V> last = data[index];
                        for (Node<K, V> n = data[index]; n != null; n = n.nextNode) {
                                if (n.key.equals(key)) {
                                        n.value = value;
                                        break;
                                }
                                last = n;
                        }
                        last.nextNode = node;
                } else {
                        data[index] = node;
                }
                size += 1;
        }

        public V get(K key) {
                int hash = key.hashCode();
                int index = Math.abs(hash % data.length);
                if (data[index] == null || data[index].value == null) {
                        return null;
                }

                for (Node<K, V> n = data[index]; n != null; n = n.nextNode) {
                        if (n.key.equals(key)) {
                                return n.value;
                        }
                }
                return null;
        }
}

public class HashTable {
        public static void main(String[] args) {
        // String --> int
        SuperHashMap<String, Integer> namesToAges = new SuperHashMap<>(5);

        namesToAges.put("Artem", 35);
        namesToAges.put("Barsik", 7);
        namesToAges.put("Moscow", 850);

        System.out.println("Moscow " + namesToAges.get("Moscow"));
        System.out.println("Barsik " + namesToAges.get("Barsik"));
        System.out.println("Artem  " + namesToAges.get("Artem"));
        System.out.println("ABC    " + namesToAges.get("ABC"));

        System.out.println(namesToAges);
        }
}
