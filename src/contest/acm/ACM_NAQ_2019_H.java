import java.io.*;
import java.util.*;

public class H {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[][] adj;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    adj = new int[N][N];
    dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        adj[i][j] = readInt();
        dp[i][j] = -1;
      }
    }
    int ans = 0;
    for (int i = 0; i < N; i++) {
      ans = Math.max(ans, solve(i, (i - 1 + N) % N));
    }
    out.println(ans);
    out.close();
  }

  static int solve(int i, int end) {
    if (i == end) {
      return 0;
    }
    if (dp[i][end] != -1) {
      return dp[i][end];
    }
    dp[i][end] = 0;
    for (int j = i + 1;; j++) {
      if (adj[i][j % N] == 1) {
        int next = 1;
        if (j % N != end) {
          next += solve((j + 1) % N, end);
        }
        if ((i + 1) % N != j % N) {
          next += solve((i + 1) % N, (j - 1 + N) % N);
        }
        dp[i][end] = Math.max(dp[i][end], next);
      }
      if (j % N == end) {
        break;
      }
    }
    dp[i][end] = Math.max(dp[i][end], solve((i + 1) % N, end));
    return dp[i][end];
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
