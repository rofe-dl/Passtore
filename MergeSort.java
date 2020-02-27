import java.util.Arrays;

public class MergeSort{
    public static void main(String[] args){
        int[] a = {5,6,2,88,2,625,1,14,15,1,588,34,26,84,123,73,61};

        mergeSort(a, 0, a.length-1);

        System.out.println(Arrays.toString(a));

    }

    public static void mergeSort(int[] a, int start, int end){
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid+1, end);
            merge(a, start, mid, end);
        }
    }

    public static void merge(int[] a, int start, int mid, int end){

        int[] l = new int[mid - start + 1];
        int[] r = new int[end - mid];

        for (int i = 0, j = start; i < l.length; i++, j++) l[i] = a[j];
        for (int i = 0, j = mid+1; i < r.length; i++, j++) r[i] = a[j];

        int i, j , k; 
        i = start;
        j = k = 0;

        while(j < l.length && k < r.length){
            if (l[j] < r[k]) a[i++] = l[j++];
            else a[i++] = r[k++];
        }

        while(j < l.length) a[i++] = l[j++];
        while(k < r.length) a[i++] = r[k++];
    }
}