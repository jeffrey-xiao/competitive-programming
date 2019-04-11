package contest.ccc;

import java.util.Scanner;

public class CCC_2011_S3 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int x = scan.nextInt(); x > 0; x--) {
      System.out.println(isCrystal(scan.nextInt(), scan.nextInt(), scan.nextInt()) ? "crystal" : "empty");
    }
  }

  public static boolean isCrystal(int m, int x, int y) {
    if (m == 0)
      return false;
    int newX = (int)Math.floor(x / (Math.pow(5, m) / 5));
    int newY = (int)Math.floor(y / (Math.pow(5, m) / 5));
    if (newX > 0 && newX < 4 && newY == 0) {
      return true;
    } else if (newX == 2 && newY == 1) {
      return true;
    } else if (((newX == 1 || newX == 3) && newY == 1) || (newY == 2 && newX == 2))
      return isCrystal(m - 1, (int)(x % (Math.pow(5, m) / 5)), (int)(y % (Math.pow(5, m) / 5)));
    return false;
  }
}
