package contest.ccc;

import java.util.Scanner;

public class CCC_2004_S5 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    int width = scan.nextInt();
    scan.nextLine();
    while (width != 0) {
      char[][] grid = new char[width][];

      for (int x = 0; x < grid.length; x++) {
        grid[x] = scan.nextLine().toCharArray();
      }
      int[][] dp = new int[grid.length][grid[0].length + 1];
      for (int x = 1; x < dp[0].length; x++) {
        int[] currentValues1 = new int[dp.length];
        int[] currentValues2 = new int[dp.length];
        // up
        for (int y = dp.length - 1; y >= 0; y--) {
          int curr = 0;
          if (grid[y][x - 1] == '*') {
            dp[y][x] = -1;
            currentValues1[y] = -1;
            continue;
          }
          if (((x - 1 >= 0 && dp[y][x - 1] == -1) && (y + 1 <= grid.length - 1 && currentValues1[y + 1] == -1))) {
            currentValues1[y] = -1;
            continue;
          }
          if (grid[y][x - 1] != '.') {
            curr = grid[y][x - 1] - 48;
          }
          int next = 0;
          if (y + 1 < dp.length)
            next = currentValues1[y + 1];
          currentValues1[y] = Math.max(dp[y][x - 1], next) + curr;
        }

        // down
        if (x != 1)
          for (int y = 0; y < dp.length; y++) {
            int curr = 0;
            if (grid[y][x - 1] == '*') {
              dp[y][x] = -1;
              currentValues2[y] = -1;
              continue;
            }
            if (((x - 1 >= 0 && dp[y][x - 1] == -1) && (y > 0 && currentValues2[y - 1] == -1))) {
              currentValues2[y] = -1;
              continue;
            }
            if (grid[y][x - 1] != '.') {
              curr = grid[y][x - 1] - 48;
            }

            int next = 0;
            if (y - 1 >= 0)
              next = currentValues2[y - 1];
            currentValues2[y] = Math.max(dp[y][x - 1], next) + curr;
          }
        for (int y = 0; y < dp.length; y++) {
          if ((currentValues1[y] == -1 && currentValues2[y] == -1)) {
            dp[y][x] = -1;
          } else
            dp[y][x] = Math.max(currentValues2[y], currentValues1[y]);
        }
      }
      System.out.println(dp[dp.length - 1][dp[0].length - 1]);
      width = scan.nextInt();
      scan.nextLine();
    }

  }
}
