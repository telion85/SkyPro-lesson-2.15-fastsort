package site.telion.skyprolesson215fastsort.service;

import site.telion.skyprolesson215fastsort.exception.MyIndexOutOfBoundsException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] array;
    private int arraySize;

    public IntegerListImpl() {
        array = new Integer[10];
        arraySize = 0;
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        increaseArraySize();
        array[arraySize] = item;

        return array[arraySize++];
    }

    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);

        if (index < 0 || index > arraySize) {
            throw new MyIndexOutOfBoundsException("Введен некорректный индекс");
        }
        increaseArraySize();

        if (arraySize - index >= 0) {
            System.arraycopy(array, index, array, index + 1, arraySize - index);
        }

        array[index] = item;
        arraySize++;
        return array[index];
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullPointerException("Значение не должно быть равно null");
        }
    }

    private void increaseArraySize() {
        if (arraySize == (array.length - 1)) {
            int newSize = array.length * 2 + 1;
            array = Arrays.copyOf(array, newSize);
        }
    }

    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        array[index] = item;
        return array[index];
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new MyIndexOutOfBoundsException("Элемент не найден");
        }

        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer element = array[index];

        if (arraySize - index >= 0) {
            System.arraycopy(array, index + 1, array, index, arraySize - index);
        }

        arraySize--;
        return element;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < arraySize; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = arraySize-1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (arraySize != otherList.size()) {
            return false;
        }

        for (int i = 0; i < arraySize; i++) {
            if (!array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    @Override
    public void clear() {
        arraySize = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, arraySize);
    }

    private void validateIndex(int index) {
        try {
            if (array[index] == null || index >= arraySize) {
                throw new MyIndexOutOfBoundsException("Такого элемента не существует");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MyIndexOutOfBoundsException("Превышен размер внутреннего массива");
        }
    }

}
