package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCO_Prep_Corporative_Network {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static int LN = 15;

  static int[][] par, sum;
  static int[] id, depth;
  static boolean[] notRoot;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {

      N = readInt();

      par = new int[N][LN];
      sum = new int[N][LN];
      id = new int[N];
      depth = new int[N];
      notRoot = new boolean[N];

      for (int i = 0; i < LN; i++)
        for (int j = 0; j < N; j++)
          par[j][i] = -1;

      adj.clear();

      for (int i = 0; i < N; i++) {
        id[i] = i;
        depth[i] = -1;
        adj.add(new ArrayList<Integer>());
      }

      ArrayList<Query> q = new ArrayList<Query>();

      char c;
      while ((c = readCharacter()) != 'O') {
        if (c == 'E')
          q.add(new Query(readInt() - 1, -1));
        else {
          // v is par of u
          int u = readInt() - 1;
          int v = readInt() - 1;
          adj.get(v).add(u);
          notRoot[u] = true;
          par[u][0] = v;
          sum[u][0] = Math.abs(v - u) % 1000;
          q.add(new Query(u, v));
        }
      }

      for (int i = 0; i < N; i++)
        if (!notRoot[i])
          dfs(i, 0);

      for (int i = 1; i < LN; i++) {
        for (int j = 0; j < N; j++) {
          if (par[j][i - 1] != -1) {
            par[j][i] = par[par[j][i - 1]][i - 1];
            sum[j][i] = sum[j][i - 1] + sum[par[j][i - 1]][i - 1];
          }
        }
      }

      for (Query query : q) {
        if (query.b == -1) {
          out.println(lca(find(query.a), query.a));
        } else {
          merge(query.a, query.b);
        }
      }
    }

    out.close();
  }

  static int lca(int a, int b) {
    if (a == b)
      return 0;

    int ret = 0;
    for (int i = LN - 1; i >= 0; i--) {
      if (par[b][i] != -1 && depth[par[b][i]] > depth[a]) {
        ret += sum[b][i];
        b = par[b][i];
      }
    }
    ret += sum[b][0];
    return ret;
  }

  static void dfs(int u, int d) {
    depth[u] = d;
    for (int v : adj.get(u))
      dfs(v, d + 1);
  }

  static int find(int i) {
    return i == id[i] ? i : (id[i] = find(id[i]));
  }

  // j is par of i
  static void merge(int i, int j) {
    i = find(i);
    j = find(j);
    id[i] = j;
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

  static class Query {
    int a, b;

    Query(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }
}
