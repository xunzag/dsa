//Lab 6

// Task 1

import java.util.Random;
import java.util.Scanner;

public class Sort {
    private static int comparisons, swaps;

    // Insertion Sort implementation
    public static void insertionSort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j = j - 1;
                swaps++;
            }
            arr[j + 1] = key;
            swaps++;
        }
    }

    // Utility function to print the array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Generate a random array
    public static int[] generateRandomArray(int size, int upperLimit) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(upperLimit + 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements (between 200 and 500): ");
        int n = sc.nextInt();

        int[] arr = generateRandomArray(n, 500);

        System.out.println("Unsorted array:");
        printArray(arr);

        long startTime = System.nanoTime();
        insertionSort(arr);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // Convert to milliseconds

        System.out.println("Sorted array:");
        printArray(arr);
        System.out.println("Insertion Sort completed in: " + duration + " ms");
        System.out.println("Comparisons: " + comparisons + ", Swaps: " + swaps);
    }
}

// Task 2

import java.util.Random;
import java.util.Scanner;

public class Sort {

    private static int comparisons = 0, swaps = 0;

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j = j - 1;
                swaps++;
            }
            arr[j + 1] = key;
            swaps++;
        }
    }

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
        }
    }

    // Generate random array
    public static int[] generateRandomArray(int size, int upperLimit) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(upperLimit + 1);
        }
        return arr;
    }

    // Utility function to print array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // prompt to ask user its choice
    public static void sortArray(int[] arr, String sortType) {
        switch (sortType.toLowerCase()) {
            case "insertion":
                insertionSort(arr);
                break;
            case "bubble":
                bubbleSort(arr);
                break;
            case "selection":
                selectionSort(arr);
                break;
            default:
                System.out.println("Invalid sorting technique.");
        }
    }

    // Perform analysis by counting comparisons and swaps
    public static void performAnalysis(int[] arr, String sortType) {
        int[] copyArray = arr.clone();  // copy of arry here
        sortArray(copyArray, sortType);
        System.out.println("Sorting using " + sortType + " Sort:");
        System.out.println("Comparisons: " + comparisons + ", Swaps: " + swaps);
    }

    public static void highlightBestWorst(int compInsertion, int compBubble, int compSelection) {
        // Determine best and worst by comparisons (lower is better)
        int minComparisons = Math.min(Math.min(compInsertion, compBubble), compSelection);
        int maxComparisons = Math.max(Math.max(compInsertion, compBubble), compSelection);

        System.out.println("\nBest Sorting Technique (Fewest Comparisons): ");
        if (compInsertion == minComparisons) System.out.println("Insertion Sort");
        if (compBubble == minComparisons) System.out.println("Bubble Sort");
        if (compSelection == minComparisons) System.out.println("Selection Sort");

        System.out.println("Worst Sorting Technique (Most Comparisons): ");
        if (compInsertion == maxComparisons) System.out.println("Insertion Sort");
        if (compBubble == maxComparisons) System.out.println("Bubble Sort");
        if (compSelection == maxComparisons) System.out.println("Selection Sort");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements (between 200 and 500): ");
        int n = sc.nextInt();
        int[] arr = generateRandomArray(n, 500);

        System.out.println("Unsorted array:");
        printArray(arr);

        int[] copyArray = arr.clone();  // Clone the array to use it in each sorting
        performAnalysis(copyArray, "insertion");
        int compInsertion = comparisons;

        copyArray = arr.clone();  // Clone again to reset
        performAnalysis(copyArray, "bubble");
        int compBubble = comparisons;

        copyArray = arr.clone();
        performAnalysis(copyArray, "selection");
        int compSelection = comparisons;

        highlightBestWorst(compInsertion, compBubble, compSelection);
    }
}


// Task 3

import java.util.Stack;

public class PostfixEvaluator {
    public static int evaluate(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (char ch : postfix.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push(ch - '0');
            } else {
                int b = stack.pop();
                int a = stack.pop();
                switch (ch) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String postfix = "231*+9-";
        System.out.println("Postfix: " + postfix);
        System.out.println("Evaluation result: " + evaluate(postfix));
    }
}


// Lab 7

// Task 1

public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; 
        }
        int mid = array.length / 2;

        
        int[] left = new int[mid]; // left half
        int[] right = new int[array.length - mid]; // right half

    
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        
        mergeSort(left);
        mergeSort(right);

        
        merge(array, left, right);
    }

    
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0; 
        int j = 0; 
        int k = 0; 

        
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        printArray(array);

        mergeSort(array);

        System.out.println("Sorted array:");
        printArray(array);
    }
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

// Task 2

public class QuickSort {

    
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

   
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; 
        int i = low - 1; 
 
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++; 
                swap(array, i, j); 
            }
        }
        
        swap(array, i + 1, high);
        return i + 1; 
    }

    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    

    
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original Array :");
        printArray(array);
    
        
        quickSort(array, 0, array.length - 1);
        System.out.println("Sorted Array:");
        printArray(array);
    }
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

// Lab 5

// Task 1:

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(301) + 200; 
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(501); 
        }

        // Perform Bubble Sort
        long startTime = System.nanoTime();
        bubbleSort(arr);
        long endTime = System.nanoTime();

        
        long timeElapsed = endTime - startTime;
        System.out.println("Bubble Sort Time: " + timeElapsed + " nanoseconds");
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}


// Task 2:

import java.util.Random;

public class BubbleSortOptimized {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(301) + 200;  
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(501); 
        }

        long startTime = System.nanoTime();
        bubbleSortOptimized(arr);
        long endTime = System.nanoTime();

        long timeElapsed = endTime - startTime;
        System.out.println("Optimized Bubble Sort Time: " + timeElapsed + " nanoseconds");
    }

    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

// Task 3:

import java.util.Random;

public class SelectionSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(301) + 200;  
        int[] arr = new int[n];

        
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(501);  
        }

        // Perform Selection Sort
        long startTime = System.nanoTime();
        selectionSort(arr);
        long endTime = System.nanoTime();

        long timeElapsed = endTime - startTime;
        System.out.println("Selection Sort Time: " + timeElapsed + " nanoseconds");
    }

    // Selection Sort Algorithm
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap arr[minIndex] and arr[i]
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}


// Lab 4:


public class Tasks {
    public static int multiply(int a, int b) {
        return a * b;
    }
    public static int factorial(int n) {
        if (n < 0) {
            System.out.println("No factorial for negative number");
        }

        int r = 1;
        int i = 1; 

        while (i <= n) {
            r *= i; 
            i++; 
        }
        return r;
    }
    public static int binarySearch(int[] arr, int target, int low, int hi) {
        if (low > hi) {
            return -1;  
        }
        int mid = (low + hi) / 2;  
        if (arr[mid] == target) {
            return mid;  
        }
        if (target < arr[mid]) {
            return binarySearch(arr, target, low, mid - 1); 
        }
        return binarySearch(arr, target, mid + 1, hi); 
    }

    public static void main(String[] args) {
        //Task 1
        int num1 = 5;
        int num2 = 10;
        int result = multiply(num1, num2);
        System.out.println("The product of " + num1 + " and " + num2 + " is: " + result);
        //Task3
        int number = 5;
        int r = factorial(number);
        System.out.println("Factorial of a "  + number + " is: "  + r);
        //Task6
        int[] arr = {1, 3, 5, 7, 9}; 
        int target = 7;  
        int output = binarySearch(arr, target, 0, arr.length - 1);
        if (output == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + output);
        }
    }
}


public class Tasks2 {
    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static int sum(int[] arr, int n) {
        if (n == 0) {
            return 0;
        }
        return arr[n - 1] + sum(arr, n - 1);
    }

    public static void main(String[] args) {
        //Task 2
        int a = 12, b = 25, c = 7;
        System.out.println("The maximum number is: " + max(a, b, c));
        //Task 4
        int n = 10; 
        System.out.println("The " + n + "th Fibonacci number is: " + fibonacci(n));
        //Task 5
        int[] arr = {1, 2, 3, 4, 5}; 
        System.out.println("The sum of the array is: " + sum(arr, arr.length));
    }
}


// Lab 3

// Task 1

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[15];
        int size = 10;

        System.out.println("Enter 10 elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the position (0-9) to insert a new element: ");
        int insertPos = scanner.nextInt();
        if (size < 15 && insertPos >= 0 && insertPos <= size) {
            System.out.print("Enter the new element: ");
            int newElement = scanner.nextInt();
            for (int i = size; i > insertPos; i--) {
                arr[i] = arr[i - 1];
            }
            arr[insertPos] = newElement;
            size++;
        } else {
            System.out.println("Invalid position or array is full!");
        }

        // Deleting an element from a random position
        System.out.print("Enter the position (0-" + (size - 1) + ") to delete: ");
        int deletePos = scanner.nextInt();
        if (deletePos >= 0 && deletePos < size) {
            for (int i = deletePos; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        } else {
            System.out.println("Invalid position!");
        }

        // Displaying the final array
        System.out.println("Final array:");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}


// Task 2

// 2D Array:


import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] arr = new int[3][5];
        int rows = 3, cols = 5, elements = 10;

        System.out.println("Enter 10 elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols && elements > 0; j++) {
                arr[i][j] = scanner.nextInt();
                elements--;
            }
        }

        System.out.print("Enter the row and column (0-2, 0-4) to insert: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (row >= 0 && row < rows && col >= 0 && col < cols && arr[row][col] == 0) {
            System.out.print("Enter the new element: ");
            int newElement = scanner.nextInt();
            arr[row][col] = newElement;
        } else {
            System.out.println("Invalid position or position is occupied!");
        }

        System.out.print("Enter the row and column (0-2, 0-4) to delete: ");
        row = scanner.nextInt();
        col = scanner.nextInt();
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            arr[row][col] = 0;
        } else {
            System.out.println("Invalid position!");
        }

        System.out.println("Final 2D array:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Task 3

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        Arrays.sort(arr);

        System.out.print("Enter the element to search: ");
        int key = scanner.nextInt();

        int result = binarySearch(arr, key);
        if (result == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }

    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
