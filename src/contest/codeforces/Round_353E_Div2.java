package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_353E_Div2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] val, max, index;
  static long[] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    max = new int[4 * N];

    val = new int[N + 1];
    dp = new long[N + 1];

    for (int i = 1; i < N; i++)
      val[i] = readInt();
    val[N] = N;

    build(1, 1, N);

    long ans = 0;
    for (int i = N - 1; i >= 1; i--) {
      int s = query(1, 1, N, i + 1, val[i]);
      long best = dp[s] + N - i - (val[i] - s);
      dp[i] = best;
      ans += best;
    }

    out.println(ans);
    out.close();
  }

  static void build(int n, int l, int r) {
    if (l == r) {
      max[n] = l;
      return;
    }

    int mid = (l + r) >> 1;
    build(n << 1, l, mid);
    build(n << 1 | 1, mid + 1, r);

    if (val[max[n << 1]] >= val[max[n << 1 | 1]])
      max[n] = max[n << 1];
    else
      max[n] = max[n << 1 | 1];
  }

  static int query(int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
      return max[n];

    int mid = (l + r) >> 1;
    if (qr <= mid)
      return query(n << 1, l, mid, ql, qr);
    else if (ql > mid)
      return query(n << 1 | 1, mid + 1, r, ql, qr);
    else {
      int s1 = query(n << 1, l, mid, ql, mid);
      int s2 = query(n << 1 | 1, mid + 1, r, mid + 1, qr);
      if (val[s1] >= val[s2])
        return s1;
      else
        return s2;
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

  static class State {
    int index, min;

    State(int min, int index) {
      this.min = min;
      this.index = index;
    }
  }
}
