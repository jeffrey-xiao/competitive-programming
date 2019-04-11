package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Bookshelf_Silver {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int width = readInt();
    int[][] books = new int[n + 1][2];
    int[] dp = new int[n + 1];
    for (int x = 1; x <= n; x++) {
      books[x][0] = readInt();
      books[x][1] = readInt();// 0 height, 1 width
      dp[x] = Integer.MAX_VALUE; // lowest bookshelf with the first x
      // books
    }

    for (int x = 0; x < dp.length; x++) {
      int sum = 0;
      int height = 0;
      for (int y = 1; x + y <= n && sum + books[x + y][1] <= width; y++) {
        sum += books[x + y][1];
        height = Math.max(books[x + y][0], height);
        dp[x + y] = Math.min(dp[x + y], dp[x] + height);
      }
    }
    System.out.println(dp[n]);
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
