package contest.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UTS_Open_2014_Second_MST_V {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int VSIZE = 50010;
  static int ESIZE = 100010;

  static int[] nextE = new int[ESIZE * 2];
  static int[] to = new int[ESIZE * 2];
  static int[] cost = new int[ESIZE * 2];

  static int[] id = new int[VSIZE];
  static int[] size = new int[VSIZE];
  static int[] now = new int[VSIZE];
  static int[] last = new int[VSIZE];

  static int n, m, E;

  public static void main (String[] args) throws IOException {
    for (int x = 0; x < VSIZE; x++) {
      last[x] = -1;
      id[x] = x;
    }
    n = readInt();
    m = readInt();
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Edge> unused = new ArrayList<Edge>();
    for (int x = 0; x < m; x++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      a--;
      b--;
      edges.add(new Edge(a, b, c));
    }
    Collections.sort(edges);
    int totalCost = 0;
    for (Edge e : edges) {
      if (merge(e.a, e.b)) {
        totalCost += e.c;
        addEdge(e.a, e.b, e.c, e.c);
      } else
        unused.add(e);
    }
    int minV = 1 << 30;
    for (Edge e : unused) {
      for (int x = 0; x < VSIZE; x++)
        now[x] = last[x];
      int nextV = e.c - dfs(e.a, e.b, 0, -1);
      if (nextV != 0)
        minV = Math.min(minV, nextV);
    }
    if (m < n - 1 || minV == 1 << 30)
      ps.println(-1);
    else
      ps.println(minV + totalCost);
    ps.close();
  }

  private static int dfs (int x, int y, int m, int prev) {
    if (x == y)
      return m;
    for (int e = now[x]; e != -1; e = nextE[e]) {
      if (to[e] != prev) {
        int v = dfs(to[e], y, Math.max(m, cost[e]), x);
        if (v != -1)
          return v;
      }
    }
    return -1;
  }

  private static void addEdge (int x, int y, int xy, int yx) {
    to[E] = y;
    cost[E] = xy;
    nextE[E] = last[x];
    last[x] = E;
    E++;

    to[E] = x;
    cost[E] = yx;
    nextE[E] = last[y];
    last[y] = E;
    E++;

  }

  static class Edge implements Comparable<Edge> {
    int a, b, c;

    Edge (int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    @Override
    public int compareTo (Edge o) {
      return c - o.c;
    }
  }

  private static int find (int x) {
    return (x == id[x]) ? x : (id[x] = find(id[x]));
  }

  private static boolean merge (int x, int y) {
    int rootx = find(x);
    int rooty = find(y);
    if (rootx == rooty)
      return false;
    if (size[rootx] > size[rooty]) {
      size[rootx] += size[rooty];
      id[rooty] = rootx;
    } else {
      size[rooty] += size[rootx];
      id[rootx] = rooty;
    }
    return true;
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
