import java.util.*;

public class DataSorter {

    static int[] data = null;

    public static int[] bubbleSort(int[] arr, long[] steps) {
        int n = arr.length;
        int[] a = Arrays.copyOf(arr, n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                steps[0]++;

                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    public static int[] mergeSort(int[] arr, long[] steps) {
        int[] a = Arrays.copyOf(arr, arr.length);
        mergeSortRecursive(a, 0, a.length - 1, steps);
        return a;
    }

    private static void mergeSortRecursive(int[] arr, int left, int right, long[] steps) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSortRecursive(arr, left, mid, steps);
            mergeSortRecursive(arr, mid + 1, right, steps);

            merge(arr, left, mid, right, steps);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, long[] steps) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            steps[0]++;

            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static int[] quickSort(int[] arr, long[] steps) {
        int[] a = Arrays.copyOf(arr, arr.length);
        quickSortRecursive(a, 0, a.length - 1, steps);
        return a;
    }

    private static void quickSortRecursive(int[] arr, int low, int high, long[] steps) {
        if (low < high) {
            int pi = partition(arr, low, high, steps);
            quickSortRecursive(arr, low, pi - 1, steps);
            quickSortRecursive(arr, pi + 1, high, steps);
        }
    }

    private static int partition(int[] arr, int low, int high, long[] steps) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            steps[0]++;

            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void enterNumbersManually(Scanner sc) {
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        data = new int[n];
        System.out.println("Enter " + n + " numbers:");

        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }
    }

    public static void generateRandomData(Scanner sc) {
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        Random r = new Random();
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = r.nextInt(1000);
        }

        System.out.println("Random data generated successfully!");
    }

    public static void compareAll() {
        if (data == null) {
            System.out.println("No dataset found. Please enter or generate numbers first.");
            return;
        }

        long[] bubbleSteps = {0}, mergeSteps = {0}, quickSteps = {0};

        long start, end;

        start = System.nanoTime();
        bubbleSort(data, bubbleSteps);
        end = System.nanoTime();
        long bubbleTime = end - start;

        start = System.nanoTime();
        mergeSort(data, mergeSteps);
        end = System.nanoTime();
        long mergeTime = end - start;

        start = System.nanoTime();
        quickSort(data, quickSteps);
        end = System.nanoTime();
        long quickTime = end - start;

        System.out.println("\n--- Performance Comparison Table ---");
        System.out.println("Algorithm\t| Time (ns)\t| Steps");
        System.out.println("Bubble Sort\t| " + bubbleTime + "\t| " + bubbleSteps[0]);
        System.out.println("Merge Sort\t| " + mergeTime + "\t| " + mergeSteps[0]);
        System.out.println("Quick Sort\t| " + quickTime + "\t| " + quickSteps[0]);
        System.out.println("-------------------------------------------\n");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    enterNumbersManually(sc);
                    break;

                case 2:
                    generateRandomData(sc);
                    break;

                case 3:
                    if (data == null) System.out.println("No data available!");
                    else {
                        long[] steps = {0};
                        int[] result = bubbleSort(data, steps);
                        System.out.println("Bubble Sorted: " + Arrays.toString(result));
                        System.out.println("Steps: " + steps[0]);
                    }
                    break;

                case 4:
                    if (data == null) System.out.println("No data available!");
                    else {
                        long[] steps = {0};
                        int[] result = mergeSort(data, steps);
                        System.out.println("Merge Sorted: " + Arrays.toString(result));
                        System.out.println("Steps: " + steps[0]);
                    }
                    break;

                case 5:
                    if (data == null) System.out.println("No data available!");
                    else {
                        long[] steps = {0};
                        int[] result = quickSort(data, steps);
                        System.out.println("Quick Sorted: " + Arrays.toString(result));
                        System.out.println("Steps: " + steps[0]);
                    }
                    break;

                case 6:
                    compareAll();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
