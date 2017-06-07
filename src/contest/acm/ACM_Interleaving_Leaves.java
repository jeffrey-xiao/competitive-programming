package contest.acm;

import java.util.Scanner;

public class ACM_Interleaving_Leaves {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    for (int z = scan.nextInt(); z > 0; z--) {
      int length = scan.nextInt();
      scan.nextLine();
      String s1 = scan.nextLine();
      String s2 = scan.nextLine();
      String newS = "";
      for (int x = length - 1; x >= 0; x--) {
        newS += s2.charAt(x);
        newS += s1.charAt(x);
      }
      System.out.println(newS);
    }
  }
}
