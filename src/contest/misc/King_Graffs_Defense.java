package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class King_Graffs_Defense {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static int[] low, disc;
  static boolean[] v;
  static int count = 0;
  static int n, m;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    v = new boolean[n];
    disc = new int[n];
    low = new int[n];

    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Edge>());
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(new Edge(b, adj.get(b).size()));
      adj.get(b).add(new Edge(a, adj.get(a).size() - 1));
    }
    for (int i = 0; i < n; i++)
      if (!v[i])
        dfs(i, -1);
    v = new boolean[n];
    long total = 0;
    for (int i = 0; i < n; i++) {
      if (!v[i]) {
        int size = getSize(i);
        total += (size) * (size - 1) / 2;
      }
    }
    out.printf("%.5f\n", 1 - total / (double)((long)n * (n - 1) / 2));
    out.close();
  }

  static int getSize (int i) {
    int ret = 1;
    v[i] = true;
    for (Edge j : adj.get(i))
      if (!j.bridge && !v[j.a])
        ret += getSize(j.a);
    return ret;
  }

  static void dfs (int i, int prev) {
    disc[i] = low[i] = count++;
    v[i] = true;
    for (int j = 0; j < adj.get(i).size(); j++) {
      Edge next = adj.get(i).get(j);
      if (!v[next.a]) {
        dfs(next.a, i);
        low[i] = Math.min(low[i], low[next.a]);
        if (low[next.a] > disc[i]) {
          adj.get(i).get(j).bridge = true;
          adj.get(next.a).get(next.b).bridge = true;
        }
      } else if (next.a != prev && disc[next.a] < low[i]) {
        low[i] = disc[next.a];
      }
    }
  }

  static class Edge {
    int a, b;
    boolean bridge;

    Edge (int a, int b) {
      this.a = a;
      this.b = b;
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
