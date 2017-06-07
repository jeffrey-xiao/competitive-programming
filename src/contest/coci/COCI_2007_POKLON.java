package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2007_POKLON {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] bit = new int[1000001];
  static int[] dp;
  static int[] prev;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    Range[] r = new Range[n];
    dp = new int[n];
    prev = new int[n];
    for (int i = 0; i < n; i++) {
      r[i] = new Range(readInt(), readInt());
    }
    for (int i = 0; i < 1000000; i++)
      bit[i] = -1;
    Arrays.sort(r);
    int ans = 0;
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      prev[i] = -1;

      int query = query(r[i].r);
      if (query != -1) {
        dp[i] = dp[query] + 1;
        prev[i] = query;
      }
      update(r[i].r, i);
      if (dp[i] > dp[ans])
        ans = i;
    }
    System.out.println(dp[ans]);
    while (ans != -1) {
      System.out.println(r[ans].l + " " + r[ans].r);
      ans = prev[ans];
    }
  }

  static int query (int X) {
    int res = -1;
    for (int x = X; x > 0; x -= (x & -x)) {
      if (bit[x] != -1 && (res == -1 || dp[bit[x]] > dp[res])) {
        res = bit[x];
      }
    }
    return res;
  }

  static void update (int X, int v) {
    for (int x = X; x < 1000001; x += (x & -x)) {
      if (bit[x] == -1 || dp[v] > dp[bit[x]])
        bit[x] = v;
    }
  }

  static class Range implements Comparable<Range> {
    int l, r;

    Range (int l, int r) {
      this.l = l;
      this.r = r;
    }

    @Override
    public int compareTo (Range o) {
      if (l == o.l)
        return r - o.r;
      return o.l - l;
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
