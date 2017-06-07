package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DMOPC_2015_MMORPG_II {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static ArrayList<ArrayList<Integer>> adj;

  static boolean[] isBasic;
  static long[] dp;
  static int[] sz;

  static long[] f;
  static final int MOD = (int)(1e9 + 7);

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    f = new long[100001];
    f[0] = 1;
    for (int i = 1; i <= 100000; i++)
      f[i] = (f[i - 1] * i) % MOD;

    N = readInt();

    adj = new ArrayList<ArrayList<Integer>>(N);
    isBasic = new boolean[N];
    dp = new long[N];
    sz = new int[N];

    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < N; i++) {
      int val = readInt() - 1;
      if (val == -1)
        isBasic[i] = true;
      else
        adj.get(val).add(i);
    }
    int curr = N;
    long ans = 1;

    for (int i = 0; i < N; i++) {
      if (isBasic[i]) {
        dfs(i);
        ans = (ans * dp[i]) % MOD;
        ans = (ans * choose(curr, sz[i])) % MOD;
        curr -= sz[i];
      }
    }
    out.println(ans);
    out.close();
  }

  static void dfs (int u) {
    sz[u] = 1;
    for (int v : adj.get(u)) {
      dfs(v);
      sz[u] += sz[v];
    }

    int curr = sz[u] - 1;
    long ret = 1;

    for (int v : adj.get(u)) {
      ret = (ret * dp[v]) % MOD;
      ret = (ret * choose(curr, sz[v])) % MOD;
      curr -= sz[v];
    }
    dp[u] = ret;
  }

  static long choose (int n, int k) {
    return divMod(divMod(f[n], f[k]), f[n - k]);
  }

  // O(log P)
  static long divMod (long i, long j) {
    return i * pow(j, MOD - 2, MOD) % MOD;
  }

  static long pow (long base, long pow, long mod) {
    if (pow == 0)
      return 1;
    if (pow == 1)
      return base;
    if (pow % 2 == 0)
      return pow(base * base % mod, pow / 2, mod);
    return base * pow(base * base % mod, pow / 2, mod) % mod;
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
