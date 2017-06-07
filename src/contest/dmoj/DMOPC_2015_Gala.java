package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DMOPC_2015_Gala {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static final int MOD = 1000000007;
  static final int SIZE = 1501;
  static long[] dp = new long[SIZE];

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int k = readInt();
    compute(SIZE - 1, MOD);
    if (k <= 18) {
      long ans = 0;
      Interval[] a = new Interval[k];
      for (int i = 0; i < k; i++)
        a[i] = new Interval(readInt() - 1, readInt() - 1);
      Arrays.sort(a);
      for (int i = 0; i < 1 << k; i++) {
        int[] id = new int[n + 1];
        for (int j = 0; j < k; j++) {
          if ((i & 1 << j) > 0) {
            id[a[j].l] += 1 << (j);
            id[a[j].r + 1] -= 1 << (j);
          }
        }
        for (int j = 1; j <= n; j++)
          id[j] += id[j - 1];
        int[] cnt = new int[19];
        for (int j = 0; j < n; j++) {
          cnt[msb(id[j])]++;
        }
        long currAns = 1;
        for (int j = 0; j < 19; j++) {
          currAns = (currAns * catalan(cnt[j] - (j == 0 ? 0 : 2))) % MOD;
        }
        ans = (ans + currAns) % MOD;
      }
      out.println(ans);
    }

    out.close();
  }

  private static int msb (int x) {
    int bval[] = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4};

    int base = 0;
    if ((x & 0xFFFF0000) > 0) {
      base += 32 / 2;
      x >>= 32 / 2;
    }
    if ((x & 0x0000FF00) > 0) {
      base += 32 / 4;
      x >>= 32 / 4;
    }
    if ((x & 0x000000F0) > 0) {
      base += 32 / 8;
      x >>= 32 / 8;
    }
    return base + bval[x];
  }

  static class Interval implements Comparable<Interval> {
    int l, r;

    Interval (int l, int r) {
      this.l = l;
      this.r = r;
    }

    @Override
    public int compareTo (Interval o) {
      return -(r - l) + (o.r - o.l);
    }
  }

  static long catalan (int n) {
    if (n % 2 == 1)
      return 0;
    if (n < 0)
      return 1;
    return dp[n / 2];
  }

  static void compute (int n, int m) {
    dp[0] = dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j <= i - 1; j++)
        dp[i] = (dp[i] + (dp[j] * dp[i - j - 1]) % m) % m;
      dp[i] %= m;
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