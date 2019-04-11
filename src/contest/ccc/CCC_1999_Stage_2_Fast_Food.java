package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_1999_Stage_2_Fast_Food {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] r;

  public static void main(String[] args) throws IOException {

    int n = 0;
    while ((n = readInt()) != 0) {
      int k = readInt();
      r = new int[n];
      for (int x = 0; x < n; x++)
        r[x] = readInt();
      int[][] dp = new int[k + 1][n];
      int[][] prev = new int[k + 1][n];
      for (int x = 0; x <= k; x++)
        for (int y = 0; y < n; y++)
          prev[x][y] = -1;
      for (int x = 1; x <= k; x++) {  // WAREHOUSES LEFT
        for (int y = 0; y < n; y++) { // CURRENT RESTAURANT
          if (x > y) {
            dp[x][y] = 0;
            prev[x][y] = y - 1;
          } else {
            int next = Integer.MAX_VALUE;
            int nextPrev = -1;
            if (x == 1) {
              next = r[y] - r[0];
            } else
              for (int z = y - 1; z >= 0; z--) {
                int a = Math.max(dp[x - 1][z], cost(z, y));
                if (next > a) {
                  next = a;
                  nextPrev = z;
                }
              }
            dp[x][y] = next;
            prev[x][y] = nextPrev;
          }
        }
      }
      int min = Integer.MAX_VALUE;
      int minPos = -1;
      for (int x = 0; x < n; x++) {
        int next = Math.max(dp[k][x], r[n - 1] - r[x]);
        if (next < min) {
          min = next;
          minPos = x;
        }
      }
      Stack<Integer> s = new Stack<Integer>();
      while (minPos != -1) {
        s.push(r[minPos]);
        minPos = prev[k][minPos];
        k--;
      }

      for (int x = s.size(); x > 0; x--)
        System.out.print(s.pop() + " ");
      System.out.println("\n" + min + "\n");
    }
  }

  private static int cost(int y, int z) {
    int cost = 0;
    for (int x = y + 1; x <= z; x++) {
      cost = Math.max(cost, Math.min(r[x] - r[y], r[z] - r[x]));
    }
    return cost;
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
