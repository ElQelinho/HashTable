package com.structures;


class Node<K,V> {
        K key;
        V value;
        Node<K,V> nextNode;
}
class SuperHashMap<K,V> {
        static final int DEFAULT_LENGTH = 10;
        Node<K,V>[] data;
        int size = 0;
        SuperHashMap () {
                data = (Node<K, V>[]) new Node[DEFAULT_LENGTH];
        }
        public void put(K key, V value) {
                int hash = key.hashCode();
                int index = Math.abs(hash % data.length);
                Node<K,V> node =  new Node<K, V>();
                node.value = value;
                node.key = key;


                if (data[index] != null) {
                        int bucketLength = 0;
                        Node<K, V> last = data[index];
                        for (Node<K, V> n = data[index]; n != null; n = n.nextNode) {
                                if (n.key.equals(key)) {
                                        n.value = value;
                                        break;
                                }
                                last = n;
                                bucketLength += 1;
                        }
                        last.nextNode = node;
                        if (bucketLength > 2) {
                                rebuildArray();
                        }
                } else {
                        data[index] = node;
                }
                size += 1;
        }

        private void rebuildArray() {
                // TODO Создать новый массив х2, запутать в новый массив старые значения.
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
        SuperHashMap<String, Integer> namesToAges = new SuperHashMap<>();

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
