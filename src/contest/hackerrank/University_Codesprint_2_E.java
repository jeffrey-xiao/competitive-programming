package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class University_Codesprint_2_E {

  static final int MOD = (int)(1e9 + 7);
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N;
  static int[] val, seg;
  static long[] fact;
  static long[][] dp, choose;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    val = new int[N];
    seg = new int[N << 2];
    fact = new long[N + 1];

    fact[0] = 1;
    for (int i = 1; i <= N; i++)
      fact[i] = (fact[i - 1] * i) % MOD;

    for (int i = 0; i < N; i++)
      val[i] = readInt();

    update(1, 0, N - 1, 0, 0, 1);
    int last = 0;
    for (int i = 1; i < N; i++) {
      if (val[i] < val[i - 1])
        last = i;
      update(1, 0, N - 1, last, i, 1);
    }

    dp = new long[N + 1][N + 1];
    for (int i = 0; i <= N; i++)
      for (int j = 0; j <= N; j++)
        dp[i][j] = -1;

    choose = new long[N + 1][N + 1];
    for (int i = 0; i <= N; i++)
      for (int j = 0; j <= i; j++)
        choose[i][j] = (j == 0 || i <= 1) ? 1 : (choose[i - 1][j - 1] + choose[i - 1][j]) % MOD;

    out.println(compute(0, N));
    out.close();
  }

  static long compute(int n, int k) {
    if (n == N)
      return 1;
    if (dp[n][k] != -1)
      return dp[n][k];
    long ret = 0;
    int len = Math.min(k, query(1, 0, N - 1, n));
    for (int i = 1; i <= len; i++)
      ret = (ret + (n == 0 ? 1 : fact[i] * choose[k][i] % MOD) * compute(n + i, i)) % MOD;
    return dp[n][k] = ret;
  }

  static void update(int n, int l, int r, int ql, int qr, int val) {
    if (ql == l && qr == r) {
      seg[n] += val;
      return;
    }
    pushDown(n);
    int mid = (l + r) >> 1;
    if (qr <= mid)
      update(n << 1, l, mid, ql, qr, val);
    else if (ql > mid)
      update(n << 1 | 1, mid + 1, r, ql, qr, val);
    else {
      update(n << 1, l, mid, ql, mid, val);
      update(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
    }
  }

  static int query(int n, int l, int r, int x) {
    if (l == x && x == r)
      return seg[n];
    pushDown(n);
    int mid = (l + r) >> 1;
    if (x <= mid)
      return query(n << 1, l, mid, x);
    else
      return query(n << 1 | 1, mid + 1, r, x);
  }

  static void pushDown(int n) {
    if (seg[n] != 0) {
      seg[n << 1] += seg[n];
      seg[n << 1 | 1] += seg[n];
      seg[n] = 0;
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
