package ru.ali.array;
//import org.graalvm.compiler.graph.IterableNodeType;

import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<Item> implements Iterable<Item> {
    private int size = 0;
    private Object[] list = new Object[1];

    public MyArrayList() {}

    public MyArrayList(MyArrayList<Item> myArrayList) {
        list = new Object[myArrayList.size];
        System.arraycopy(myArrayList.list, 0, this.list, 0, myArrayList.size);
        this.size = myArrayList.size;
    }

    public Item get(int index) { //arr[index]
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (Item) list[index];
    }

    public void set(int index, Item item) { //arr[index] = item;
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        list[index] = item;
    }

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        //System.arraycopy();
        for (int i = 0; i < list.length; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }

    public void add(Item item) {
        if (size == list.length) {
            resize(2 * list.length);
        }
        list[size] = item;
        size++;
    }

    public boolean remove(Item item) {
        int index = indexOf(item);
        if (index == - 1) { return false; }
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
        if (size == list.length / 4 && size > 0) {
            resize(list.length / 2);
        }

        //0 1 2 3 4
        //1 5 7 6
        return true;
    }

    public int indexOf(Item item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Item item) {
        return indexOf(item) != - 1;
    }


    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class MyArrayListIterator implements Iterator<Item> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            Item item = (Item) list[cursor];
            cursor++;
            return item;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(list[i] + ", ");
        }
        return s.toString();
    }

    public int size() {
        return size;
    }

    private void exch(int i, int j) {
        Object temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    private boolean less(Item item1, Item item2, Comparator<Item> cmp) {
        return cmp.compare(item1, item2) < 0;
    }

    public void selectionSort(Comparator<Item> cmp) {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((Item) list[j], (Item) list[min], cmp)) {
                    min = j;
                }
            }
            exch(i, min);
        }
    }

    public void insertionSort(Comparator<Item> cmp) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less((Item) list[j], (Item) list[j - 1], cmp)) {
                    exch(j, j - 1);
                }
                else {
                    break;
                }
            }
        }
    }

    public boolean binarySearch(Item item, Comparator<Item> cmp) {
        return binarySearch(item, 0, size - 1, cmp);
        /*int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; //(low + high) / 2
            if (cmp.compare(item, (Item) list[mid]) < 0) {
                high = mid - 1;
            }
            else if (cmp.compare(item, (Item) list[mid]) > 0) {
                low = mid + 1;
            }
            else { //cmp.compare(item, (Item) list[mid]) == 0
                return true;
            }
        }
        return false;*/
    }

    private boolean binarySearch(Item item, int low, int high, Comparator<Item> cmp) {
        if (low > high) {
            return false;
        }
        int mid = low + (high - low) / 2;
        if (cmp.compare(item, (Item) list[mid]) == 0) {
            return true;
        }
        else if (cmp.compare(item, (Item) list[mid]) > 0) {
            return binarySearch(item, mid + 1, high, cmp);
        }
        else { //if (cmp.compare(item, (Item) list[mid]) < 0)
            return binarySearch(item, low, mid - 1, cmp);
        }
    }
}