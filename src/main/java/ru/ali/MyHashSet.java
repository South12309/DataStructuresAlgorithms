package ru.ali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class MyHashSet {
    ArrayList<Integer> set = new ArrayList<>();

    public MyHashSet() {

    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        HashSet<Integer> set1 = new HashSet<>();
        new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        set.add(1);
        set.add(2);
        System.out.println(set.contains(1));
        System.out.println(set.contains(3));
        set.add(2);
        System.out.println(set.contains(2));
        set.remove(2);
        set.contains(2);
    }

    public void add(int key) {
        if (!set.contains(key)) {
            set.add(key);
        }
    }

    public void remove(int key) {
        if (set.contains(key)) {
            set.remove((Object)key);
        }
    }

    public boolean contains(int key) {
        return set.contains(key);
    }
}