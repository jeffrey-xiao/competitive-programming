package contest.ccc;

import java.util.Scanner;

public class CCC_1997_C {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    for (int x = scan.nextInt(); x > 0; x--, System.out.println()) {
      int a = scan.nextInt();
      int b = 0;
      int c = 0;
      int round = 0;
      while (b != 1 || a != 1) {
        System.out.printf("Round %d: %d undefeated, %d one-loss, %d eliminated\n", round, a, b, c);
        c += b / 2;
        b = (int)Math.ceil(b / 2.0);
        b += a / 2;
        a = (int)Math.ceil(a / 2.0);
        round++;
      }
      System.out.printf("Round %d: %d undefeated, %d one-loss, %d eliminated\n", round, a, b, c);
      a -= 1;
      b += 1;
      round++;
      System.out.printf("Round %d: %d undefeated, %d one-loss, %d eliminated\n", round, a, b, c);
      b -= 1;
      c += 1;
      round++;
      System.out.printf("Round %d: %d undefeated, %d one-loss, %d eliminated\n", round, a, b, c);
      System.out.println("There are " + (round) + " rounds.");
    }
  }
}
