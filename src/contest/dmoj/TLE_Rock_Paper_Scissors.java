package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class TLE_Rock_Paper_Scissors {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, Q;

  static int[] disc, lo, id, sz;
  static int cnt, comCnt;

  static Stack<Integer> s = new Stack<Integer>();
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<HashSet<Integer>> g = new ArrayList<HashSet<Integer>>();

  static boolean[] inStack;
  static int[] cache;
  static int[][] val;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    Q = readInt();

    disc = new int[N];
    lo = new int[N];
    id = new int[N];
    sz = new int[N];
    inStack = new boolean[N];

    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < M; i++)
      adj.get(readInt() - 1).add(readInt() - 1);

    for (int i = 0; i < N; i++)
      if (disc[i] == 0)
        dfs(i);

    for (int i = 0; i < comCnt; i++)
      g.add(new HashSet<Integer>());

    for (int i = 0; i < N; i++)
      for (int j : adj.get(i))
        if (id[i] != id[j])
          g.get(id[i]).add(id[j]);

    val = new int[N][N];
    for (int i = 0; i < comCnt; i++) {

      cache = new int[comCnt];
      for (int j = 0; j < comCnt; j++)
        cache[j] = -2;

      for (int j = 0; j < comCnt; j++) {
        if (i == j)
          continue;
        val[j][i] = find(j, i);
      }
    }

    for (int i = 0; i < Q; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;

      if (id[u] == id[v]) {
        out.println("Indeterminate");
        continue;
      }
      if (val[id[u]][id[v]] != -1) {
        out.printf("%d %d\n", u + 1, val[id[u]][id[v]] - sz[id[u]]);
        continue;
      } else if (val[id[v]][id[u]] != -1) {
        out.printf("%d %d\n", v + 1, val[id[v]][id[u]] - sz[id[v]]);
        continue;
      } else {
        out.println("Indeterminate");
      }
    }

    out.close();
  }

  static int find(int u, int v) {
    if (cache[u] != -2)
      return cache[u];
    if (u == v)
      return cache[u] = 0;
    int ret = -1;
    for (int x : g.get(u)) {
      int res = find(x, v);
      if (res != -1) {
        ret = Math.max(ret, res + sz[u]);
      }
    }
    return cache[u] = ret;
  }

  static void dfs(int u) {
    disc[u] = lo[u] = ++cnt;
    inStack[u] = true;
    s.push(u);

    for (int v : adj.get(u)) {
      if (disc[v] == 0) {
        dfs(v);
        lo[u] = Math.min(lo[u], lo[v]);
      } else if (inStack[v]) {
        lo[u] = Math.min(lo[u], disc[v]);
      }
    }
    if (disc[u] == lo[u]) {
      while (s.peek() != u) {
        inStack[s.peek()] = false;
        sz[comCnt]++;
        id[s.pop()] = comCnt;
      }
      inStack[s.peek()] = false;
      sz[comCnt]++;
      id[s.pop()] = comCnt++;
    }
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