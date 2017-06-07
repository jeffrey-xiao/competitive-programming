package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_2001_Mission_Impossible_3 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    for (int x = scan.nextInt(); x > 0; x--) {
      long R = scan.nextLong();
      long a = scan.nextLong();
      long b = scan.nextLong();
      long ans = 0;
      ans = R / a + R / b - R / ((a * b) / gcf(a, b));
      System.out.println(ans);
    }
  }

  private static long gcf (long a, long b) {
    if (b == 0)
      return a;
    if (b == 1)
      return 1;
    return gcf(b, a % b);
  }

}
