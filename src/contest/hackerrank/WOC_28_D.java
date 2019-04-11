package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class WOC_28_D {

  static final int MAX_N = 100000;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int Q, N, M;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static boolean[] vis;
  static long[] calc = new long[MAX_N + 1];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    Q = readInt();

    for (int i = 1; i <= MAX_N; i++)
      calc[i] = (long) (i) * (i - 1) + calc[i - 1];

    for (int q = 0; q < Q; q++) {
      N = readInt();
      M = readInt();

      adj.clear();
      vis = new boolean[N];
      long ans = 0;

      for (int i = 0; i < N; i++)
        adj.add(new ArrayList<Integer>());

      for (int i = 0; i < M; i++) {
        int u = readInt() - 1;
        int v = readInt() - 1;
        adj.get(u).add(v);
        adj.get(v).add(u);
      }

      ArrayList<Integer> sizes = new ArrayList<Integer>();
      for (int i = 0; i < N; i++) {
        if (!vis[i]) {
          int sz = fill(i);
          if (sz > 1)
            sizes.add(sz);
        }
      }

      Collections.sort(sizes);
      Collections.reverse(sizes);

      long edgesLeft = M;
      for (int i = 0; i < sizes.size(); i++) {
        edgesLeft -= (sizes.get(i) - 1);
        ans += (edgesLeft * sizes.get(i) * (sizes.get(i) - 1)) + calc[sizes.get(i)];
      }

      out.println(ans);
    }

    out.close();
  }

  static int fill(int u) {
    Queue<Integer> q = new ArrayDeque<Integer>();
    q.offer(u);
    vis[u] = true;
    int ret = 1;
    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int v : adj.get(curr))
        if (!vis[v]) {
          vis[v] = true;
          q.offer(v);
          ret++;
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
}
