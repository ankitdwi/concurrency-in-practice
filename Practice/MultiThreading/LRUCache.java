package Practice.MultiThreading;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LRUCache {
    private Deque<String> doublyQueue;
    private Set<String> hashSet;
    private int CAPACITY = 10;

    LRUCache(int capacity) {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet();
        CAPACITY = capacity;
    }

    public void refer(String key) {
        if(!hashSet.contains(key)){
            if(doublyQueue.size() == CAPACITY) {
                String last = doublyQueue.removeLast();
                hashSet.remove(last);
            }
        } else {
            doublyQueue.remove(key);
            hashSet.remove(key);
        }

        doublyQueue.addFirst(key);
        hashSet.add(key);
    }
}
