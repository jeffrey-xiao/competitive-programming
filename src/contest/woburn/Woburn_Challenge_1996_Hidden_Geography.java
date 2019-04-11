package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_1996_Hidden_Geography {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    for (int d = 0; d < 5; d++) {
      StringBuilder s = new StringBuilder(scan.nextLine().toLowerCase());
      for (int x = s.length() - 1; x >= 0; x--) {
        if (alpha.indexOf(s.charAt(x)) == -1)
          s.deleteCharAt(x);
      }
      if (s.indexOf("britishcolumbia") != -1)
        System.out.println("British Columbia");
      else if (s.indexOf("alberta") != -1)
        System.out.println("Alberta");
      else if (s.indexOf("saskatchewan") != -1)
        System.out.println("Saskatchewan");
      else if (s.indexOf("manitoba") != -1)
        System.out.println("Manitoba");
      else if (s.indexOf("ontario") != -1)
        System.out.println("Ontario");
      else if (s.indexOf("quebec") != -1)
        System.out.println("Quebec");
      else if (s.indexOf("novascotia") != -1)
        System.out.println("Nova Scotia");
      else if (s.indexOf("newfoundland") != -1)
        System.out.println("Newfoundland");
      else if (s.indexOf("newbrunswick") != -1)
        System.out.println("New Brunswick");
      else if (s.indexOf("pei") != -1)
        System.out.println("PEI");
      else
        System.out.println("NO PROVINCE FOUND");
    }
  }
}
