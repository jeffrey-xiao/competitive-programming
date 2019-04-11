package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_1996_Different_Base_Multiplication {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    for (int x = 5; x > 0; x--) {

      String n1 = scan.next();
      int b1 = scan.nextInt();
      String n2 = scan.next();
      int b2 = scan.nextInt();
      int b3 = scan.nextInt();

      String answer = Integer.toString((Integer.valueOf(n1, b1)) * Integer.valueOf(n2, b2), b3);
      System.out.println(answer);
    }
    scan.close();
  }
}
