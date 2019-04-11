package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_1998_Return_Of_The_Plachta {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int x = 0; x <= 4; x++) {
      int a = scan.nextInt();
      int b = scan.nextInt();
      int c = scan.nextInt();
      int d = scan.nextInt();

      int gcf = gcf(b, d);

      int n2 = (b * d) / gcf;
      int n1 = n2 / b * a + n2 / d * c;
      gcf = gcf(n1, n2);
      n1 /= gcf;
      n2 /= gcf;
      System.out.println(n1 + (n2 == 1 ? "" : " " + n2));
    }

  }

  public static int gcf(int a, int b) {
    if (b == 1)
      return 1;
    if (b == 0)
      return a;
    return gcf(b, a % b);
  }
}
