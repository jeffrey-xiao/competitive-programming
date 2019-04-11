package contest.dwite;

import java.util.Scanner;

public class DWITE_2006_Prime_Palindrome {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    boolean[] primes = new boolean[999999];
    primes[0] = true;
    for (int x = 1; x < primes.length; x += 2)
      if (x + 1 % 2 == 0)
        primes[x] = true;
    for (int x = 1; x * x < primes.length; x++)
      if (primes[x] == false)
        for (int y = (x + 1) * (x + 1) - 1; y < primes.length; y += (x + 1))
          primes[y] = true;

    for (int z = 5; z > 0; z--) {
      int count = 0;
      int start = scan.nextInt();
      int end = scan.nextInt();
      for (int x = start; x <= end; x++)
        if (!primes[x - 1] && isPalindrome(Integer.toString(x)))
          count++;

      System.out.println(count);
    }
    scan.close();
  }

  private static boolean isPalindrome(String x) {
    return new StringBuilder(x).reverse().toString().equals(x);
  }
}
