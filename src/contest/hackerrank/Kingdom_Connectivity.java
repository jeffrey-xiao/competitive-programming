package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Kingdom_Connectivity {

  static final int MOD = 1000000000;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int[] edge;
  static int[] next;
  static int[] last;
  static int cnt = 0;
  static ArrayList<Integer> order = new ArrayList<Integer>();
  static int[] dp;
  static boolean[] v;
  static boolean[] computed;
  static ArrayList<Integer> inCycle = new ArrayList<Integer>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    edge = new int[m];
    next = new int[m];
    last = new int[n];

    v = new boolean[n];
    computed = new boolean[n];
    dp = new int[n];
    for (int i = 0; i < n; i++)
      last[i] = -1;
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      edge[cnt] = b;
      next[cnt] = last[a];
      last[a] = cnt++;
    }
    dfs(0);
    v = new boolean[n];
    for (int i : inCycle)
      mark(i);
    if (v[n - 1]) {
      out.println("INFINITE PATHS");
      out.close();
      return;
    }
    dp[0] = 1;
    for (int i = order.size() - 1; i >= 0; i--) {
      for (int j = last[order.get(i)]; j != -1; j = next[j]) {
        dp[edge[j]] = (dp[edge[j]] + dp[order.get(i)]) % MOD;
      }
    }
    out.println(dp[n - 1]);
    out.close();
  }

  static void mark(int i) {
    v[i] = true;
    for (int j = last[i]; j != -1; j = next[j])
      if (!v[edge[j]])
        mark(edge[j]);
  }

  static void dfs(int i) {
    v[i] = true;
    for (int j = last[i]; j != -1; j = next[j]) {
      if (v[edge[j]] && !computed[edge[j]])
        inCycle.add(edge[j]);
      if (v[edge[j]])
        continue;
      dfs(edge[j]);
    }
    computed[i] = true;
    order.add(i);
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