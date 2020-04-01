import java.util.Arrays;

class Node{
    
    Node next, prev;
    int value;

    public Node(int value, Node next){
        this.value = value;
        this.next = next;
    }

    public Node(int value, Node next, Node prev){
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

public class Recursion{
    public static void main(String[] args){
        System.out.println(reverseAndMerge("Xyz", "And"));
        System.out.println(numOfhi("xhixhixhihiihHi"));
        System.out.println(numOf7(774703));
        
        int[] a = {-1, -1, 0, 4, 7, 8, 9, 11, 14};
        System.out.println(binarySearch(a, 0, 8, 4));
        
        Node n1 = new Node(5, null);
        Node n2 = new Node(90, n1);
        Node n3 = new Node(16, n2);
        Node n4 = new Node(15, n3);
        Node n5 = new Node(2, n4);
        Node n6 = new Node(61, n5);
        Node n7 = new Node(24, n6);

        printOddPos(n7); System.out.println();

        int[] a2 = {-5, 22, -9, 2, 45, 3, 129, 6, -67, 1, 0, 1, 2, 22, -5};
        insertionSort(a2, a2.length);
        System.out.println(Arrays.toString(a2));

        System.out.println(factorial(5));
        System.out.println(nthFib(6));
        System.out.println(intToBinary(17));
        System.out.println(pow(3, 3));
    } 

    public static String reverseAndMerge(String s1, String s2){
        int l = s1.length();
        if(l == 1) return s1 + s2;

        return s1.substring(l - 1) + s2.substring(l - 1) + reverseAndMerge(s1.substring(0, l-1), s2.substring(0, l-1));
    }

    public static int numOfhi(String s){
        if(s.length() < 2) return 0;
        else if(s.charAt(0) == 'h' && s.charAt(1) == 'i'){
            return 1 + numOfhi(s.substring(1));
        } 

        return numOfhi(s.substring(1));
    }

    public static int numOf7(int n){
        if(n == 0) return 0;
        else if(n % 10 == 7) return 1 + numOf7(n/10);

        return numOf7(n/10);
    }

    public static int binarySearch(int[] a, int l, int h, int val){
        if(l > h) return -1;

        int m = (l + h) / 2;

        if (a[m] == val) return m;
        else if(a[m] > val) return binarySearch(a, l, m-1, val);

        return binarySearch(a, m+1, h, val);

    }

    public static void insertionSort(int[] a, int i){
        if(i <= 1) return;
        insertionSort(a, i - 1);

        int elem = a[i - 1];
        int mark = i - 2;

        while(mark >= 0 && elem < a[mark]){
            a[mark + 1] = a[mark];
            mark--;
        }

        a[mark + 1] = elem;
    }

    public static int evenSum(Node n){
        if (n == null) return 0;

        if(n.value % 2 == 0) return n.value + evenSum(n.next);
        return evenSum(n.next);
    }

    public static void printOddPos(Node n){
        if (n != null){
            System.out.print(n.value + " ");
            if (n.next != null) printOddPos(n.next.next);
        }
    }

    public static long factorial(long n){
        if(n <= 0) return 1;

        return n * factorial(n - 1);
    }

    public static int nthFib(int n){
        if (n == 1)
            return 0;
        else if (n == 2)
            return 1;
        else
            return nthFib(n - 1) + nthFib(n - 2);
    }

    public static String intToBinary(int n){
        if (n == 1) return "1";
        else if (n == 0) return "0";

        return "" + intToBinary(n / 2) + n % 2; 
    } 

    public static long pow(int m, int n){
        if (n == 0) return 1;
        return m * pow(m, n - 1);
    }
}