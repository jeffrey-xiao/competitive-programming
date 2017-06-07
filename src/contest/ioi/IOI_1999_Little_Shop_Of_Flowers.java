package contest.ioi;

import java.util.Scanner;

public class IOI_1999_Little_Shop_Of_Flowers {
  static Scanner scan = new Scanner(System.in);
  static int[][] pointValues = new int[0][0];

  public static void main (String[] args) {
    int flowers = scan.nextInt();
    int vases = scan.nextInt();
    pointValues = new int[flowers + 1][vases + 1];
    for (int x = 1; x <= flowers; x++) {
      for (int y = 1; y <= vases; y++) {
        pointValues[x][y] = scan.nextInt();
      }
    }

    for (int x = 1; x <= flowers; x++) {
      for (int y = x; y <= vases; y++) {
        pointValues[x][y] = pointValues[x - 1][y - 1] + pointValues[x][y];
        if (y > 1)
          pointValues[x][y] = Math.max(pointValues[x][y], pointValues[x][y - 1]);
      }
    }

    String s = traverse(pointValues, flowers, vases);
    System.out.println(pointValues[flowers][vases]);
    System.out.println(s.trim());
  }

  private static String traverse (int[][] dp, int x, int y) {
    if (dp[x][y] == 0)
      return "";
    if (dp[x][y] == dp[x][y - 1])
      return traverse(dp, x, y - 1);
    return traverse(dp, x - 1, y - 1) + " " + y;
  }
}
