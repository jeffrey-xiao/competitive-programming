package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: guard
 */
import java.util.StringTokenizer;

public class guard {
  static final int INF = 1 << 30;
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("guard.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("guard.out")));
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
    pr.println(ans == -INF ? "Mark is too tall" : ans);

    pr.close();
    System.exit(0);
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

  static class Cow {
    long height, weight, strength;

    Cow(long height, long weight, long strength) {
      this.height = height;
      this.weight = weight;
      this.strength = strength;
    }
  }

  static class State {
    long height, safe;

    State(long height, long safe) {
      this.height = height;
      this.safe = safe;
    }
  }
}
