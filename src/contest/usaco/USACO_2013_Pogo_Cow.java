package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2013_Pogo_Cow {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[][] targets = new int[n][2];
    int[][] dp = new int[n][n];

    for (int x = 0; x < n; x++) {
      targets[x][0] = readInt();
      targets[x][1] = readInt();
    }
    Arrays.sort(targets, new Comparator<int[]>() {
      @Override
      public int compare(int[] arg0, int[] arg1) {
        return arg0[0] - arg1[0];
      }
    });
    int ans = 0;

    for (int x = n - 1; x >= 0; x--) {
      int maxValue = 0;
      int y = n - 1;
      for (int z = 0; z <= x; z++) {
        for (; y > x && targets[y][0] - targets[x][0] >= targets[x][0] - targets[z][0]; y--) {
          maxValue = Math.max(maxValue, targets[y][1] + dp[y][x]);
        }
        dp[x][z] = maxValue;
      }
      ans = Math.max(ans, targets[x][1] + maxValue);
    }
    Arrays.sort(targets, new Comparator<int[]>() {
      @Override
      public int compare(int[] arg0, int[] arg1) {
        return arg1[0] - arg0[0];
      }
    });
    for (int x = n - 1; x >= 0; x--) {
      int maxValue = 0;
      int y = n - 1;
      for (int z = 0; z <= x; z++) {
        for (; y > x && targets[y][0] - targets[x][0] <= targets[x][0] - targets[z][0]; y--) {
          maxValue = Math.max(maxValue, targets[y][1] + dp[y][x]);
        }
        dp[x][z] = maxValue;
      }
      ans = Math.max(ans, targets[x][1] + maxValue);
    }
    System.out.println(ans);
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
