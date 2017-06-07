package contest.ccc;

import java.util.Scanner;

public class CCC_2010_J4 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int input = scan.nextInt();
    while (input != 0) {
      int[] diff = new int[input - 1];

      int start = scan.nextInt();
      for (int x = 0; x < input - 1; x++) {
        int next = scan.nextInt();
        diff[x] = next - start;
        start = next;
      }
      if (diff.length == 0) {
        System.out.println(0);
        input = scan.nextInt();
        continue;
      }
      // assume cycle length is 1 and continue;
      int x = 1;
      main : for (; x < diff.length; x++) {
        for (int y = x; y < diff.length; y++) {
          if (diff[y - x] != diff[y])
            continue main;
        }
        break;
      }
      System.out.println(x);
      input = scan.nextInt();
    }
  }
}
