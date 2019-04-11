package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_2002_Its_Party_Time {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int t = scan.nextInt(); t > 0; t--) {
      int a = scan.nextInt();
      int b = scan.nextInt();
      int c = scan.nextInt();
      System.out.println(a + (b * c) / gcf(b, c));
    }
  }

  private static int gcf(int b, int c) {
    if (c == 1)
      return 1;
    if (c == 0)
      return b;
    return gcf(c, b % c);
  }

}
