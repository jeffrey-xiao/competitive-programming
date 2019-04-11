package contest.ccc;

import java.util.Scanner;

class CCC_2010_J3 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int a = 0, b = 0;
    int input = scan.nextInt();
    while (input != 7) {
      String next = "";
      String next1 = "";
      switch (input) {

        case 1:
          next = scan.next();
          if (next.equals("A"))
            a = scan.nextInt();
          else if (next.equals("B"))
            b = scan.nextInt();
          break;
        case 2:
          next = scan.next();
          if (next.equals("A"))
            System.out.println(a);
          else if (next.equals("B"))
            System.out.println(b);
          break;
        case 3:
          next = scan.next();
          next1 = scan.next();
          if (next.equals("A") && next1.equals("B"))
            a += b;
          else if (next.equals("B") && next1.equals("A"))
            b += a;
          else if (next.equals("A") && next1.equals("A"))
            a += a;
          else if (next.equals("B") && next1.equals("B"))
            b += b;
          break;
        case 4:
          next = scan.next();
          next1 = scan.next();
          if (next.equals("A") && next1.equals("B"))
            a *= b;
          else if (next.equals("B") && next1.equals("A"))
            b *= a;
          else if (next.equals("A") && next1.equals("A"))
            a *= a;
          else if (next.equals("B") && next1.equals("B"))
            b *= b;
          break;
        case 5:
          next = scan.next();
          next1 = scan.next();
          if (next.equals("A") && next1.equals("B"))
            a -= b;
          else if (next.equals("B") && next1.equals("A"))
            b -= a;
          else if (next.equals("A") && next1.equals("A"))
            a -= a;
          else if (next.equals("B") && next1.equals("B"))
            b -= b;
          break;
        case 6:
          next = scan.next();
          next1 = scan.next();
          if (next.equals("A") && next1.equals("B"))
            a /= b;
          else if (next.equals("B") && next1.equals("A"))
            b /= a;
          else if (next.equals("A") && next1.equals("A"))
            a /= a;
          else if (next.equals("B") && next1.equals("B"))
            b /= b;
          break;
      }
      input = scan.nextInt();
    }
  }
}