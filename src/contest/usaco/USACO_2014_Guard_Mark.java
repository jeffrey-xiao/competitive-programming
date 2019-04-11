package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2014_Guard_Mark {

  static final int INF = 1 << 30;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    long h = readLong();
    Cow[] c = new Cow[n];
    for (int x = 0; x < n; x++)
      c[x] = new Cow(readLong(), readLong(), readLong());
    State[] dp = new State[1 << n];
    for (int x = 0; x < 1 << n; x++)
      dp[x] = new State(0, 0);
    dp[0] = new State(0, INF);
    long ans = -INF;
    for (int x = 0; x < 1 << n; x++) {
      if (dp[x].height >= h)
        ans = Math.max(ans, dp[x].safe);
      for (int y = 0; y < n; y++) {
        if ((x & 1 << y) == 0) {
          int next = x | 1 << y;
          long nextSafe = Math.min(dp[x].safe - c[y].weight, c[y].strength);
          if (dp[next].safe < nextSafe) {
            dp[next] = new State(dp[x].height + c[y].height, nextSafe);
          }
        }
      }
    }
    System.out.println(ans == -INF ? "Mark is too tall" : ans);
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

  static class State {
    long height, safe;

    State(long height, long safe) {
      this.height = height;
      this.safe = safe;
    }
  }

  static class Cow {
    long height, weight, strength;

    Cow(long height, long weight, long strength) {
      this.height = height;
      this.weight = weight;
      this.strength = strength;
    }
  }
}
