import java.util.Arrays;

public class ArrayListOfStrings implements StringList {
    private String[] elements;
    private int size;


    public ArrayListOfStrings(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0.");
        }
        elements = new String[initialCapacity];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void checkForNull(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Null values are not allowed.");
        }
    }

    @Override
    public String add(String item) {
        checkForNull(item);
        ensureCapacity();
        elements[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkForNull(item);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkForNull(item);
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        String oldValue = elements[index];
        elements[index] = item;
        return oldValue;
    }

    @Override
    public String remove(String item) {
        checkForNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new IllegalArgumentException("Element not found: " + item);
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        String removedItem = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        checkForNull(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkForNull(item);
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkForNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
        return false;
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
    public String[] toArray() {
        return Arrays.copyOf(elements, size);
    }
}
