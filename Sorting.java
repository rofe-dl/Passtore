import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] a4 = { 4, -1, 1, 11, 9, 0, 0, 5, 3, 5, 1, -6, -9 };
        int[] a3 = { 98, 2, 3, 1, 0, 0, 0, 3, 98, 98, 2, 2, 2, 0, 0, 0, 2 };
        int[] a2 = { 98, -2, 3, -1, 0, 0, 0, -3, 98, 98, 2, 2, 2, 0, 6, 0, -2 };
        int[] a = {5, 1, 5, 9, 4, -5, -5, -2, 1, 8, -8, 7, 8, 8, 5, 2, 0, 3, 1, 6 };

        insertionSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void shuffle() {
        // a = new int[] {4, -1, 1, 11, 9, 0, 0, 5, 3, 5, 1, -6, -9};
    }

    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex])
                    minIndex = j;
            }

            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }

    public static void mergeSort(int[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid + 1, end);
            merge(a, start, mid, end);
        }
    }

    public static void merge(int[] a, int start, int mid, int end) {

        int[] l = new int[mid - start + 1];
        int[] r = new int[end - mid];

        for (int i = 0, j = start; i < l.length; i++, j++)
            l[i] = a[j];
        for (int i = 0, j = mid + 1; i < r.length; i++, j++)
            r[i] = a[j];

        int i, j, k;
        i = start;
        j = k = 0;

        while (j < l.length && k < r.length) {
            if (l[j] < r[k])
                a[i++] = l[j++];
            else
                a[i++] = r[k++];
        }

        while (j < l.length)
            a[i++] = l[j++];
        while (k < r.length)
            a[i++] = r[k++];
    }

    public static void countingSort(int[] a) {
        int max = Math.abs(a[0]);
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i]) > max)
                max = Math.abs(a[i]);
        }

        int[] b = new int[max * 2 + 1];

        for (int i = 0; i < a.length; i++) {
            b[a[i] + max]++;
        }

        for (int i = 0, j = 0; i < b.length; i++) {
            while (b[i] > 0) {
                a[j++] = i - max;
                b[i]--;
            }
        }
    }

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int mark = i - 1;
            int temp = a[i];

            while (mark >= 0 && temp < a[mark]) {
                a[mark + 1] = a[mark];
                mark--;
            }

            a[mark + 1] = temp;
        }
    }
}
