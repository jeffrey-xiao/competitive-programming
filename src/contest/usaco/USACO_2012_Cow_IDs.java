package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class USACO_2012_Cow_IDs {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintStream ps = new PrintStream(System.out);
  static StringTokenizer st;
  static final int SIZE = 6000;
  static final int MAX = 1 << 25;
  static int[][] dp;
  static boolean leading = true;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    if (k == 1) {
      ps.print("1");
      ps.print(new String(new char[n - 1]).replace("\0", "0"));
      return;
    }
    dp = new int[SIZE + 1][k + 1];
    for (int x = 1; x <= SIZE; x++) {
      for (int y = 0; y <= k; y++) {
        if (y == 0)
          dp[x][y] = 1;
        else
          dp[x][y] = dp[x - 1][y - 1] + dp[x - 1][y];
        if (dp[x][y] > MAX)
          dp[x][y] = MAX;
      }
    }

    State state = new State(n, k, SIZE);
    while (state.k != 0 || state.curr != 1) {
      if (state.k == 0 || dp[state.curr - 1][state.k] >= state.n) {
        if (!leading)
          ps.print("0");
        state = new State(state.n, state.k, state.curr - 1);
      } else {
        leading = false;
        ps.print("1");
        state = new State(state.n - dp[state.curr - 1][state.k], state.k - 1, state.curr - 1);
      }
    }
  }

  static class State {
    int n, k, curr;

    State (int n, int k, int curr) {
      this.n = n;
      this.k = k;
      this.curr = curr;
    }
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
