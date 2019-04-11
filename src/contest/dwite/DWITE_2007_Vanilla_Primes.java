package contest.dwite;

import java.util.Scanner;

public class DWITE_2007_Vanilla_Primes {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println(isPrime(scan.nextInt()) ? "prime" : "not");
  }

  private static boolean isPrime(int n) {
    if (n <= 1)
      return false;
    if (n == 2)
      return true;
    if (n % 2 == 0)
      return false;
    if (n < 9)
      return true;
    if (n % 3 == 0)
      return false;

    long counter = 5;
    while ((counter * counter) <= n) {
      if (n % counter == 0)
        return false;
      if (n % (counter + 2) == 0)
        return false;
      counter += 6;
    }

    return true;
  }
}
