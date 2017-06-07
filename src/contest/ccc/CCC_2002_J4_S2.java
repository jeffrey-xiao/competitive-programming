package contest.ccc;

import java.util.Scanner;

public class CCC_2002_J4_S2 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int a = scan.nextInt();
    int b = scan.nextInt();
    int whole = a / b;
    a -= whole * b;
    if (a != 0) {
      int gcf = gcf(b, a);
      a /= gcf;
      b /= gcf;
    }
    if (a != 0 && whole != 0)
      System.out.printf("%d %d/%d", whole, a, b);
    else if (whole == 0)
      System.out.printf("%d/%d", a, b);
    else
      System.out.println(whole);
  }

  private static int gcf (int a, int b) {
    if (b == 0)
      return a;
    if (b == 1)
      return 1;
    return gcf(b, a % b);
  }
}
