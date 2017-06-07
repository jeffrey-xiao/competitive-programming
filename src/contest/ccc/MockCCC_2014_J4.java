package contest.ccc;

import java.util.Scanner;

public class MockCCC_2014_J4 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int b1 = toMinutes(scan.next());
    int b2 = toMinutes(scan.next());
    String[][] shifts = new String[scan.nextInt()][2];
    int max = 0;
    int index = 0;
    for (int x = 0; x < shifts.length; x++) {
      shifts[x][0] = scan.next();
      shifts[x][1] = scan.next();
      int a1 = toMinutes(shifts[x][0]);
      int a2 = toMinutes(shifts[x][1]);
      int union = 1 + a2 - a1 + b2 - b1 - Math.max(b2, a2) + Math.min(b1, a1);
      if (union > max) {
        max = union;
        index = x;
      }
    }
    if (max == 0)
      System.out.println("Call in a sick day.");
    else
      System.out.println(shifts[index][0] + " " + shifts[index][1]);
  }

  private static int toMinutes (String s) {
    int m = 0;
    m += Integer.parseInt(s.substring(0, 2)) * 60;
    m += Integer.parseInt(s.substring(3, 5));
    if (s.charAt(5) == 'P' && Integer.parseInt(s.substring(0, 2)) != 12)
      m += 720;
    return m;
  }
}
