package contest.acm;

import java.util.Scanner;

public class ACM_Max_Flow {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    for (int x = scan.nextInt(); x > 0; x--) {
      int max = Integer.MIN_VALUE;
      for (int y = scan.nextInt(); y > 0; y--) {
        int a = scan.nextInt();
        max = Math.max(max, a);
      }
      System.out.println(max);
    }

  }
}
