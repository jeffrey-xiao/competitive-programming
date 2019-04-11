package contest.ccc;

import java.util.Scanner;

public class CCC_2013_S5 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int n = scan.nextInt();
    int cost = 0;
    main:
    while (n > 1) {
      for (int x = 2; x * x <= n; x++) {
        if (n % x == 0) {
          int factor = n / x;
          n -= factor;
          cost += n / factor;
          continue main;
        }
      }
      n -= 1;
      cost += n;
    }
    System.out.println(cost);
  }
}
