package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_Candy {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {

    int numOfCandies = readInt();
    int[][] candies = new int[numOfCandies + 1][2];
    int sum = 0;
    for (int x = 1; x < candies.length; x++) {
      candies[x][1] = readInt();
      candies[x][0] = readInt();
      sum += candies[x][0] * candies[x][1];
    }
    boolean[] dp = new boolean[sum / 2 + 1];
    dp[0] = true;
    int max = 0;
    for (int y = 1; y < candies.length; y++) {
      dp : for (int x = dp.length - 1; x >= 1; x--) {
        for (int z = candies[y][1]; z >= 1; z--) {
          if (x - candies[y][0] * z >= 0 && dp[x - candies[y][0] * z]) {
            dp[x] = true;
            max = Math.max(x, max);
            continue dp;
          }
        }
      }

    }
    System.out.println((sum - max) - max);
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
