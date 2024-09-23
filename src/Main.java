import arrays.ArrayListOfInteger;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        int size = 100000;
        Integer[] originalArray = generateRandomArray(size);

        // Пузырьковая сортировка
        Integer[] arrayForBubbleSort = Arrays.copyOf(originalArray, originalArray.length);
        long start = System.currentTimeMillis();
        bubbleSort(arrayForBubbleSort);
        System.out.println("BubbleSort took: " + (System.currentTimeMillis() - start) + " ms");

        // Сортировка вставками
        Integer[] arrayForInsertionSort = Arrays.copyOf(originalArray, originalArray.length);
        start = System.currentTimeMillis();
        insertionSort(arrayForInsertionSort);
        System.out.println("InsertionSort took: " + (System.currentTimeMillis() - start) + " ms");

        // Сортировка выбором
        Integer[] arrayForSelectionSort = Arrays.copyOf(originalArray, originalArray.length);
        start = System.currentTimeMillis();
        selectionSort(arrayForSelectionSort);
        System.out.println("SelectionSort took: " + (System.currentTimeMillis() - start) + " ms");
    }

    // Генерация случайного массива
    public static Integer[] generateRandomArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100000); // случайные числа от 0 до 100000
        }
        return array;
    }


    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }


    public static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void insertionSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

}