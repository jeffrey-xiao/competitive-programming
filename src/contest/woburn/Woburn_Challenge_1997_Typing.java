package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_1997_Typing {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int testcases = scan.nextInt();
    scan.nextLine();
  main:
    for (int x = testcases; x > 0; x--) {
      int[] alpha = new int[26];
      String s = scan.nextLine().toLowerCase();
      for (int y = 0; y < s.length(); y++) {
        if ((s.charAt(y)) - 97 >= 0 && (s.charAt(y)) - 97 < 26)
          alpha[(s.charAt(y)) - 97]++;
      }
      for (int y : alpha)
        if (y != 1) {
          System.out.println("Nope.");
          continue main;
        }
      System.out.println("OK.");
    }
  }
}
