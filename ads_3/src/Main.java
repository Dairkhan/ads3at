import java.util.*;

class MyTestingClass {
    private String key;

    public MyTestingClass(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash;
    }
}

class MyHashTable<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private List<List<V>> buckets;

    public MyHashTable() {
        this(INITIAL_CAPACITY);
    }

    public MyHashTable(int capacity) {
        buckets = new java.util.List<java.util.List<V>>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        int index = Math.abs(key.hashCode() % buckets.size());
        buckets.get(index).add(value);
    }

    public int[] countElementsInBuckets() {
        int[] counts = new int[buckets.size()];
        for (int i = 0; i < buckets.size(); i++) {
            counts[i] = buckets.get(i).size();
        }
        return counts;
    }
}

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            String key = "Key" + i;
            MyTestingClass test = new MyTestingClass(key);
            table.put(test, i);
        }

        int[] counts = table.countElementsInBuckets();
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Bucket " + i + ": " + counts[i] + " elements");
        }
    }
}
