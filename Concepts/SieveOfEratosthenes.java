package sieveoferatosthenes;
import java.util.Scanner;

public class SieveOfEratosthenes{

    public static void main(String[] args) { 
        Scanner reader = new Scanner(System.in);
        int t = Integer.parseInt(reader.nextLine());
        for (int testCount = 1; testCount <= t; testCount++){
            int n = Integer.parseInt(reader.nextLine());
            int[] primes = new int[n + 1];
            for (int index = 2; index < primes.length; index++){
                primes[index] = 1;
            }
            
            for (int index = 2; index < Math.sqrt(n); index++){
                if (primes[index] == 1){
                    for (int i = 2; i * index <= n; i++){
                        primes[i * index] = 0;
                    }
                }
            }
            
            
        }
    }
    
}