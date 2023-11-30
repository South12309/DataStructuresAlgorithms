package ru.ali;

public class MyArray {
    int size;

    Object[] array;

    public MyArray(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    public Object getValueById(int i) {
        return array[i];
    }

    public void addValue(Object value) {
        if (size== array.length) {
            array=resizeArray(array);
        }
        array[size]=value;
        size++;
    }

    private Object[] resizeArray(Object[] arrayToResize) {
        Object[] arrayWithNewSize = new Object[2*arrayToResize.length];
        for (int i = 0; i < arrayToResize.length; i++) {
            arrayWithNewSize[i]=arrayToResize[i];
        }
        return arrayWithNewSize;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
           string = string.append(array[i]);
        }
        return string.toString();
    }
}
