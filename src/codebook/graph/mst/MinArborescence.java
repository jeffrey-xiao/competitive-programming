package codebook.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinArborescence {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int t, n, m;
  static int[] id;
  static boolean[] v, curr, cycle;
  static ArrayList<ArrayList<Edge>> adj;
  static ArrayList<Edge> e;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    // precondition: You want the arborescence to start from vertex 0
    n = readInt();
    m = readInt();
    id = new int[n];
    e = new ArrayList<Edge>();
    for (int i = 0; i < n; i++)
      id[i] = i;
    for (int i = 0; i < m; i++) {
      int a = readInt();
      int b = readInt();
      int c = readInt();
      e.add(new Edge(a, b, c));
    }
    int sz = n;
    int res = 0;
    while (true) {
      Edge[] min = new Edge[n];
      int edgesAdded = 0;
      for (Edge edge : e) {
        int rx = find(edge.a);
        int ry = find(edge.b);
        if (rx == ry || ry == find(0))
          continue;
        if (min[ry] == null || min[ry].c > edge.c) {
          min[ry] = edge;
        }
      }
      adj = new ArrayList<ArrayList<Edge>>();
      for (int i = 0; i < n; i++)
        adj.add(new ArrayList<Edge>());
      for (int i = 0; i < n; i++)
        if (min[i] != null) {
          adj.get(find(min[i].a)).add(new Edge(find(min[i].a), find(min[i].b), min[i].c));
          edgesAdded++;
        }
      if (edgesAdded < sz - 1) {
        out.printf("NO MINIMUM ARBORESCENCE FOUND\n");
        return;
      }
      v = new boolean[n];
      curr = new boolean[n];
      cycle = new boolean[n];
      boolean cycleFound = false;
      for (int i = 0; i < n; i++)
        if (!v[find(i)])
          if (dfs(find(i))) {
            cycleFound = true;
            break;
          }
      if (!cycleFound) {
        for (int i = 0; i < n; i++)
          if (min[i] != null)
            res += min[i].c;
        break;
      }
      for (int i = 0; i < e.size(); i++) {
        if (!cycle[find(e.get(i).a)] && cycle[find(e.get(i).b)]) {
          e.get(i).c -= min[find(e.get(i).b)].c;
        }
      }
      for (int i = 0; i < n; i++) {
        if (min[i] != null && cycle[find(min[i].a)] && cycle[find(min[i].b)]) {
          res += min[i].c;
          if (find(min[i].a) != find(min[i].b)) {
            merge(min[i].a, min[i].b);
            sz--;
          }
        }
      }
    }
    out.printf("The minimum arborescence is %d\n", res);
    out.close();
  }

  static boolean dfs(int i) {
    v[i] = true;
    curr[i] = true;
    boolean ret = false;
    for (Edge j : adj.get(i)) {
      if (curr[j.b]) {
        cycle[i] = true;
        ret = true;
      }
      if (!v[j.b])
        ret |= dfs(j.b);
      cycle[i] |= cycle[j.b];
    }
    curr[i] = false;
    return ret;
  }

  static int find(int x) {
    return id[x] == x ? x : (id[x] = find(id[x]));
  }

  static void merge(int x, int y) {
    int rx = find(x);
    int ry = find(y);
    id[rx] = ry;
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

  static class Edge {
    int a, b, c;

    Edge(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }
  }
}
