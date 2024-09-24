package arrays;


import java.util.Arrays;

public class ArrayListOfInteger implements IntegerList {

    private Integer[] elements;
    private int size;


    public ArrayListOfInteger(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0.");
        }
        elements = new Integer[initialCapacity];
        size = 0;
    }

    private void grow() {
        // Увеличиваем размер массива на 1.5 раза
        int newCapacity = (int) (elements.length * 1.5);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            grow();
        }
    }

    private void checkForNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }
    }


    @Override
    public Integer add(Integer item) {
        checkForNull(item);
        ensureCapacity();
        elements[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkForNull(item);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkForNull(item);
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Integer oldValue = elements[index];
        elements[index] = item;
        return oldValue;
    }

    @Override
    public Integer remove(Integer item) {
        checkForNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new IllegalArgumentException("Element not found: " + item);
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Integer removedItem = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedItem;
    }

    @Override
    public boolean contains(Integer item) {
        checkForNull(item);
        sort(); // Сортируем массив перед бинарным поиском
        return binarySearch(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkForNull(item);
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkForNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Null list is not allowed.");
        }
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.elements[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

//    private void selectionSort() {
//        for (int i = 0; i < size - 1; i++) {
//            int minIndex = i;
//            for (int j = i + 1; j < size; j++) {
//                if (elements[j] < elements[minIndex]) {
//                    minIndex = j;
//                }
//            }
//            // Swap elements[minIndex] and elements[i]
//            Integer temp = elements[minIndex];
//            elements[minIndex] = elements[i];
//            elements[i] = temp;
//        }
//    }

    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void sort() {
//        selectionSort(); // Вызываем сортировку выбором
        quickSort(elements, 0, elements.length - 1);

    }

    // Приватный метод бинарного поиска
    private int binarySearch(Integer item) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (elements[mid].equals(item)) {
                return mid;
            }
            if (elements[mid] < item) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
