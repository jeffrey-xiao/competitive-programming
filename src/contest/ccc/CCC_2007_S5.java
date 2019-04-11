package contest.ccc;

import java.util.Scanner;

public class CCC_2007_S5 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    for (int x = scan.nextInt(); x > 0; x--) {
      int numOfBowlingPins = scan.nextInt();
      int numOfBalls = scan.nextInt();
      int width = scan.nextInt();
      int[] pins = new int[numOfBowlingPins + 1];
      for (int y = 1; y < pins.length; y++)
        pins[y] = scan.nextInt();
      int[][] dp = new int[2][numOfBowlingPins + 1];
      for (int z = 1; z < numOfBalls + 1; z++) {
        int curr = 0;
        for (int y = 1; y < dp[z % 2].length; y++) {
          if (y < width + 1) {
            curr += pins[y];
            dp[z % 2][y] = curr;
            continue;
          } else
            curr = curr + pins[y] - pins[y - width];
          dp[z % 2][y] = Math.max(curr + dp[(z - 1) % 2][y - width], dp[z % 2][y - 1]);
        }
      }
      System.out.println(dp[numOfBalls % 2][numOfBowlingPins]);
    }
  }
}
