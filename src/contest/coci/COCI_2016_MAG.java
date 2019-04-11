package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2016_MAG {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static State[][] dp;
  static int[] val;
  static Fraction best;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    dp = new State[N][2];
    val = new int[N];

    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Integer>());

    int min = 1 << 30;

    for (int i = 0; i < N - 1; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    for (int i = 0; i < N; i++)
      min = Math.min(min, val[i] = readInt());

    if (min != 1)
      out.printf("%d/%d\n", min, 1);
    else {
      best = new Fraction(min, 1);
      dfs(0, -1);
      compute(0, -1, 0);
      out.println(best);
    }
    out.close();
  }

  static void compute(int u, int par, int maxLen) {
    // consider one path of ones
    if (val[u] == 1) {
      Fraction curr = new Fraction(1, dp[u][0].max + dp[u][1].max - 1);
      if (curr.compareTo(best) < 0)
        best = curr;
    }

    int maxChild = 0;
    for (int v : adj.get(u)) {
      if (v != par) {
        compute(v, u, val[u] == 1 ? maxLen + 1 : 0);
        maxChild = Math.max(maxChild, dp[v][0].max);
      }
    }

    // consider two paths of ones
    if (par != -1) {
      int maxPar = 0;
      if (dp[par][0].index != u)
        maxPar = dp[par][0].max;
      else
        maxPar = dp[par][1].max;
      maxPar = Math.max(maxPar, maxLen);

      Fraction curr = new Fraction(val[u], maxChild + maxPar + 1);
      if (curr.compareTo(best) < 0)
        best = curr;
    }
  }

  static void dfs(int u, int par) {
    dp[u][0] = new State(0, -1);
    dp[u][1] = new State(0, -1);
    if (val[u] != 1) {
      for (int v : adj.get(u))
        if (v != par)
          dfs(v, u);
    } else {
      dp[u][0] = new State(1, -1);
      dp[u][1] = new State(1, -1);
      for (int v : adj.get(u)) {
        if (v == par)
          continue;
        dfs(v, u);
        State currState = new State(dp[v][0].max + 1, v);
        replace(u, currState);
      }
    }
  }

  static void replace(int u, State s) {
    if (dp[u][0].max < s.max) {
      dp[u][1] = dp[u][0];
      dp[u][0] = s;
    } else if (dp[u][1].max < s.max) {
      dp[u][1] = s;
    }
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
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
    int max, index;

    State(int max, int index) {
      this.max = max;
      this.index = index;
    }
  }

  static class Fraction implements Comparable<Fraction> {
    long a, b;

    Fraction(long a, long b) {
      long gcd = gcd(a, b);
      if (gcd != 0) {
        a /= gcd;
        b /= gcd;
      }
      this.a = a;
      this.b = b;
    }

    @Override
    public int compareTo(Fraction f) {
      return new Long(a * f.b).compareTo(new Long(f.a * b));
    }

    @Override
    public String toString() {
      return String.format("%d/%d", a, b);
    }

  }
}
