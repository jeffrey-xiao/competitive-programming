package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Hopscotch_Gold_2 {
  static final int MOD = 1000000007;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int r, c, k;
  static int[][] g;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws IOException {
    r = readInt();
    c = readInt();
    k = readInt();
    int[][] g = new int[r + 1][c + 1];
    HashMap<Integer, Integer>[] toCy = new HashMap[k];
    int[][] prefix = new int[r + 1][c + 1];
    prefix[1][1] = 1;
    for (int i = 0; i < k; i++)
      toCy[i] = new HashMap<Integer, Integer>();

    for (int i = 1; i <= r; i++)
      for (int j = 1; j <= c; j++)
        g[i][j] = readInt() - 1;

    for (int j = 1; j <= c; j++) {
      for (int i = 1; i <= r; i++) {
        int a = g[i][j];
        if (!toCy[a].containsKey(j)) {
          toCy[a].put(j, toCy[a].size() + 1);
        }
      }
    }
    int[][] bit = new int[k][];
    for (int x = 0; x < k; x++)
      bit[x] = new int[toCy[x].size() + 1];
    int sum = 0;
    for (int i = 1; i <= r - 1; i++) {
      for (int j = c; j >= 1; j--) {
        if (i == 1 && j == 1) {
          update(1, bit[g[1][1]], 1);
          continue;
        }
        sum = 0;
        int id = g[i][j];
        int x = toCy[id].get(j);
        sum = (prefix[i - 1][j - 1] - query(x - 1, bit[id])) % MOD;
        prefix[i][j] += sum;
        update(x, bit[id], sum);
      }
      for (int j = 1; j <= c; j++)
        prefix[i][j] = (((prefix[i][j] + prefix[i - 1][j]) % MOD + prefix[i][j - 1]) % MOD - prefix[i - 1][j - 1]) % MOD;
    }
    sum = (prefix[r - 1][c - 1] - query(toCy[g[r][c]].get(c) - 1, bit[g[r][c]])) % MOD;
    if (sum < 0)
      System.out.println(sum + MOD);
    else
      System.out.println(sum % MOD);
  }

  private static void update(int x, int[] tree, int v) {
    for (int idx = x; idx < tree.length; idx += (idx & -idx)) {
      tree[idx] = (tree[idx] + v) % MOD;
    }
  }

  private static int query(int x, int[] tree) {
    int sum = 0;
    if (x == 0)
      return 0;
    for (int idx = x; idx > 0; idx -= (idx & -idx)) {
      sum = (sum + tree[idx]) % MOD;

    }
    return sum;
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