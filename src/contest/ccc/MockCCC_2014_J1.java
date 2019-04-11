package contest.ccc;

import java.util.Scanner;

public class MockCCC_2014_J1 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    double d1 = scan.nextInt();
    int d2 = scan.nextInt();
    int dose = scan.nextInt();
    if (d1 > dose)
      System.out.println("Bob overdoses on day 1.");
    else if (d2 + d1 / 2.0 > dose)
      System.out.println("Bob overdoses on day 2.");
    else
      System.out.println("Bob never overdoses.");
  }
}
