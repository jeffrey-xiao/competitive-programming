package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2016_Stage_2_Field_Trip {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj;
  static boolean[] vis;
  static int N, M, K;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    adj = new ArrayList<ArrayList<Integer>>(N);
    vis = new boolean[N];

    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Integer>(2));

    for (int i = 0; i < M; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    int ans = 0;
    int minConnections = 0;

    for (int i = 0; i < N; i++) {
      if (!vis[i]) {
        State res = dfs(i, -1);
        if (res.isCycle) {
          if (res.sz == K) {
            ans += res.sz;
          } else if (res.sz > K) {
            ans += res.sz / K * K;
            minConnections += (res.sz + K - 1) / K;
          }
        } else {
          ans += res.sz / K * K;
          minConnections += (res.sz + K - 1) / K - 1;
        }
      }
    }

    out.printf("%d %d%n", ans, minConnections);
    out.close();
  }

  static State dfs(int u, int prev) {
    State ret = new State(1, false);
    vis[u] = true;

    for (int v : adj.get(u)) {
      if (v == prev)
        continue;
      if (vis[v]) {
        ret.isCycle = true;
      } else {
        State res = dfs(v, u);
        ret.sz += res.sz;
        ret.isCycle |= res.isCycle;
      }
    }

    return ret;
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
    boolean isCycle;
    int sz;

    State(int sz, boolean isCycle) {
      this.sz = sz;
      this.isCycle = isCycle;
    }
  }
}
