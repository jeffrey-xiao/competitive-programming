package contest.usaco_other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: hopscotch
 */
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class hopscotch {
  static final int MOD = 1000000007;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int r, c, k;
  static int[][] g;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("hopscotch.in"));
    out = new PrintWriter(new FileWriter("hopscotch.out"));
    //br = new BufferedReader(new InputStreamReader(System.in));
    //out = new PrintWriter(new OutputStreamWriter(System.out));
    out.println(solve());
    out.close();
  }

  private static void update(int x, int y, int[][] tree, int v) {
    for (int idx = x; idx < tree.length; idx += (idx & -idx)) {
      for (int idy = y; idy < tree[x].length; idy += (idy & -idy)) {
        tree[idx][idy] = (tree[idx][idy] + v) % MOD;
      }
    }
  }

  private static int query(int x, int y, int[][] tree) {
    int sum = 0;
    if (x == 0 || y == 0)
      return 0;
    for (int idx = x; idx > 0; idx -= (idx & -idx)) {
      for (int idy = y; idy > 0; idy -= (idy & -idy)) {
        sum = (sum + tree[idx][idy]) % MOD;
      }
    }
    return sum;
  }

  @SuppressWarnings("unchecked")
  private static int solve() throws IOException {
    r = readInt();
    c = readInt();
    k = readInt();
    int[][] g = new int[r + 1][c + 1];
    TreeSet<Integer>[] xs = new TreeSet[k];
    TreeSet<Integer>[] ys = new TreeSet[k];
    HashMap<Integer, Integer>[] toCx = new HashMap[k];
    HashMap<Integer, Integer>[] toCy = new HashMap[k];
    HashMap<Integer, Integer>[] toIx = new HashMap[k];
    HashMap<Integer, Integer>[] toIy = new HashMap[k];
    int[][] prefix = new int[r + 1][c + 1];
    prefix[1][1] = 1;
    for (int i = 0; i < k; i++) {
      xs[i] = new TreeSet<Integer>();
      ys[i] = new TreeSet<Integer>();
      toCx[i] = new HashMap<Integer, Integer>();
      toCy[i] = new HashMap<Integer, Integer>();
      toIx[i] = new HashMap<Integer, Integer>();
      toIy[i] = new HashMap<Integer, Integer>();
    }
    for (int i = 1; i <= r; i++)
      for (int j = 1; j <= c; j++) {
        int a = g[i][j] = readInt() - 1;
        xs[a].add(i);
        ys[a].add(j);
      }
    int[][][] bit = new int[k][][];
    for (int x = 0; x < k; x++) {
      bit[x] = new int[xs[x].size() + 1][ys[x].size() + 1];
      int count = 1;
      for (Integer i : xs[x]) {
        toCx[x].put(i, count);
        toIx[x].put(count, i);
        count++;
      }
      count = 1;
      for (Integer i : ys[x]) {
        toCy[x].put(i, count);
        toIy[x].put(count, i);
        count++;
      }
    }

    update(1, 1, bit[g[1][1]], 1);
    for (int i = 1; i <= r; i++) {
      for (int j = 1; j <= c; j++) {
        if (i == 1 && j == 1)
          continue;
        int sum = 0;
        int id = g[i][j];
        sum = (-query(toCx[id].get(i) - 1, toCy[id].get(j) - 1, bit[id]) + prefix[i - 1][j - 1]) % MOD;
        update(toCx[id].get(i), toCy[id].get(j), bit[id], sum);
        if (i == r && j == c) {
          if (sum < 0)
            return sum + MOD;
          return (sum) % MOD;
        }
        prefix[i][j] = (((sum + prefix[i - 1][j]) % MOD + prefix[i][j - 1]) % MOD - prefix[i - 1][j - 1]) % MOD;
      }
    }
    return -1;
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
