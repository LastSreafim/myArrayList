import arrays.ArrayListOfInteger;
import arrays.ArrayListOfStrings;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        System.out.println(sumRec(5));


        ArrayListOfStrings arrayListOfStrings = new ArrayListOfStrings(10);

        arrayListOfStrings.add("0");
        arrayListOfStrings.add("1");
        arrayListOfStrings.add("2");
        arrayListOfStrings.add("3");

        arrayListOfStrings.add(1, "test");
        arrayListOfStrings.remove("1");
        arrayListOfStrings.remove(0);
        System.out.println(Arrays.toString(arrayListOfStrings.toArray()));


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

    public static int sum(int input) {
        int temp = 0;
        for (int i = 1; i <= input; i++) {
            temp += i;

        }
        return temp;
    }

    public static int sumRec(int input) {
        if (input > 0) {
            return input + sumRec(input - 1);
        }
        return 0;
    }

}