package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_The_Lazy_Cow_Silver {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    int newSize = n * 2 - 1;
    int[][] dp = new int[newSize][newSize];
    int mid = newSize / 2;
    for (int x = 0; x < n; x++) {
      int y = mid - x;
      for (int z = 0; z < n; z++) {
        dp[z + x][y + z] = readInt();
      }
    }

    for (int x = 0; x < newSize; x++) {
      for (int y = 0; y < newSize; y++) {
        int a = 0;
        int b = 0;
        int c = 0;
        if (x - 1 >= 0)
          a = dp[x - 1][y];
        if (y - 1 >= 0)
          b = dp[x][y - 1];
        if (x - 1 >= 0 && y - 1 >= 0)
          c = dp[x - 1][y - 1];
        dp[x][y] += a + b - c;
      }
    }
    int max = 0;
    int size = k * 2 >= newSize ? newSize - 1 : k * 2;
    for (int x = 0; x < newSize - size; x++) {
      for (int y = 0; y < newSize - size; y++) {
        int a = 0;
        int b = 0;
        int c = 0;
        if (x - 1 >= 0)
          a = dp[x - 1][y + size];
        if (y - 1 >= 0)
          b = dp[x + size][y - 1];
        if (x - 1 >= 0 && y - 1 >= 0)
          c = dp[x - 1][y - 1];

        int sum = c + dp[x + size][y + size] - b - a;
        if (max < sum)
          max = sum;
      }
    }
    System.out.println(max);
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
