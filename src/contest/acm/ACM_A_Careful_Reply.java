package contest.acm;

import java.util.Scanner;

public class ACM_A_Careful_Reply {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int numOfStrings = scan.nextInt();
    scan.nextLine();
    for (; numOfStrings > 0; numOfStrings--) {
      int num = 1;
      String s = scan.nextLine();
      for (int x = 0; x < s.length() - 1; x++) {
        if (s.charAt(x) == '<' && s.charAt(x + 1) == '3')
          num++;
      }
      for (int x = 0; x < num; x++) {
        if (x == num - 1)
          System.out.print("<3");
        else
          System.out.print("<3 ");
      }
      System.out.println();
    }

  }
}
