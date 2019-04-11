package contest.ccc;

import java.util.Arrays;
import java.util.Scanner;

public class CCC_2003_S4 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int length = scan.nextInt();
    scan.nextLine();
    for (; length > 0; length--) {
      String s = scan.nextLine();
      // get suffix array
      String[] suffix = new String[s.length()];
      for (int x = 0; x < suffix.length; x++) {
        suffix[x] = s;
        s = s.substring(1, s.length());
      }
      Arrays.sort(suffix);
      int count = suffix[0].length();
      for (int x = 0; x < suffix.length - 1; x++) {
        count += suffix[x + 1].length() - LCP(suffix[x], suffix[x + 1]);
        ;
      }
      System.out.println(count + 1);
    }
  }

  private static int LCP(String string, String string2) {
    int counter = 0;
    for (int x = 0; x < Math.min(string.length(), string2.length()); x++) {
      if (string.charAt(x) != string2.charAt(x))
        break;
      counter++;
    }
    return counter;
  }
}
