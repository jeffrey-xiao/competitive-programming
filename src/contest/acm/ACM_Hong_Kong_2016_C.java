package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ACM_Hong_Kong_2016_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, LN;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] id, sz, depth;
  static int[] indegree;
  static int[][] pa;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    LN = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);

    id = new int[N];
    sz = new int[N];
    indegree = new int[N];
    depth = new int[N];

    pa = new int[N][LN];

    for (int i = 0; i < N; i++) {
      id[i] = i;
      adj.add(new ArrayList<Integer>());

      for (int j = 0; j < LN; j++)
        pa[i][j] = -1;
    }

    for (int i = 0; i < N; i++) {
      int x = readInt() - 1;
      if (x == -1)
        continue;
      adj.get(x).add(i);
      pa[i][0] = x;
      indegree[i]++;
      merge(x, i);
    }

    for (int i = 1; i < LN; i++)
      for (int j = 0; j < N; j++)
        if (pa[j][i - 1] != -1)
          pa[j][i] = pa[pa[j][i - 1]][i - 1];

    for (int i = 0; i < N; i++)
      if (indegree[i] == 0)
        dfs(i, 0);

    M = readInt();
    for (int i = 0; i < M; i++) {
      int cnt = readInt();
      HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
      main:
      for (int j = 0; j < cnt; j++) {
        int x = readInt() - 1;
        int root = find(x);
        if (!hm.containsKey(root)) {
          hm.put(root, new ArrayList<Integer>());
          hm.get(root).add(x);
        } else {
          ArrayList<Integer> v = hm.get(root);
          for (int k = v.size() - 1; k >= 0; k--) {
            int lca = getLca(v.get(k), x);
            if (lca == v.get(k))
              continue main;
            else if (lca == x)
              v.remove(k);
          }
          v.add(x);
        }

      }
      int ans = 0;
      for (Map.Entry<Integer, ArrayList<Integer>> e : hm.entrySet())
        for (int node : e.getValue())
          ans += sz[node];
      out.println(ans);
    }

    out.close();
  }

  static int getLca(int i, int j) {
    if (depth[i] < depth[j]) {
      int temp = i;
      i = j;
      j = temp;
    }
    for (int k = LN - 1; k >= 0; k--)
      if (pa[i][k] != -1 && depth[pa[i][k]] >= depth[j])
        i = pa[i][k];

    if (i == j)
      return i;
    for (int k = LN - 1; k >= 0; k--)
      if (pa[i][k] != -1 && pa[j][k] != -1 && pa[i][k] != pa[j][k]) {
        i = pa[i][k];
        j = pa[j][k];
      }
    return pa[i][0];
  }

  static void dfs(int i, int d) {
    depth[i] = d;
    sz[i] = 1;
    for (int j : adj.get(i)) {
      dfs(j, d + 1);
      sz[i] += sz[j];
    }
  }

  static int find(int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
  }

  static void merge(int x, int y) {
    x = find(x);
    y = find(y);

    id[x] = y;
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
    int node, cnt;

    State(int node, int cnt) {
      this.node = node;
      this.cnt = cnt;
    }
  }
}
