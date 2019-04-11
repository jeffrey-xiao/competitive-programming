package contest.coci;

import java.util.Scanner;

public class COCI_2007_TRI {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int a = scan.nextInt();
    int b = scan.nextInt();
    int c = scan.nextInt();
    if (a + b == c)
      System.out.printf("%d+%d=%d", a, b, c);
    else if (a - b == c)
      System.out.printf("%d-%d=%d", a, b, c);
    else if (a * b == c)
      System.out.printf("%d*%d=%d", a, b, c);
    else if (a / b == c)
      System.out.printf("%d/%d=%d", a, b, c);
    else if (b - c == a)
      System.out.printf("%d=%d-%d", a, b, c);
    else if (b * c == a)
      System.out.printf("%d=%d*%d", a, b, c);
    else if (b + c == a)
      System.out.printf("%d=%d+%d", a, b, c);
    else
      System.out.printf("%d=%d/%d", a, b, c);
  }
}
