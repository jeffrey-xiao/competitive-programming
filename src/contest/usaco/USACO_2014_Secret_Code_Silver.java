package contest.usaco;

import java.util.Scanner;

public class USACO_2014_Secret_Code_Silver {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    String input = scan.nextLine();
    int ways = count(input);

    System.out.println(ways);
  }

  private static int count(String s) {
    if (s.length() % 2 == 0 || s.length() == 1)
      return 0;

    int ways = 0;
    int l = s.length();
    if (s.substring(0, l / 2).equals(s.substring(l / 2, l - 1)))
      ways += count(s.substring(l / 2, l)) + 1;
    if (s.substring(0, l / 2).equals(s.substring(l / 2 + 1, l)))
      ways += count(s.substring(l / 2, l)) + 1;

    if (s.substring(0, l / 2).equals(s.substring(l / 2 + 1, l)))
      ways += count(s.substring(0, l / 2 + 1)) + 1;
    if (s.substring(1, l / 2 + 1).equals(s.substring(l / 2 + 1, l)))
      ways += count(s.substring(0, l / 2 + 1)) + 1;
    return ways;

  }
}
