package contest.dwite;

import java.util.Scanner;

public class DWITE_2005_Odometer {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    main:
    for (int x = 0; x < 5; x++) {
      int n = scan.nextInt();
      int x1 = scan.nextInt();
      int x2 = scan.nextInt();
      int occurences = countOccurences(pad(n), x1);
      for (int y = n + 1; ; y++) {
        if (y > 999999)
          y = 0;
        if (countOccurences(pad(y), x2) == occurences) {
          System.out.print(pad(y) + " ");
          System.out.println((y - n) < 0 ? y - n + 1000000 : y - n);
          continue main;
        }
      }
    }
  }

  private static String pad(int y) {
    StringBuilder s = new StringBuilder(Integer.toString(y));
    while (s.length() != 6)
      s.insert(0, 0);
    return s.toString();
  }

  private static int countOccurences(String string, int x1) {
    int occurences = 0;
    for (int x = 0; x < string.length(); x++)
      if (string.charAt(x) - 48 == x1)
        occurences++;
    return occurences;
  }

}
