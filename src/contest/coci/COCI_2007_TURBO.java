package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_TURBO {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] bit = new int[100001];

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[] p = new int[n + 1];
    for (int x = 1; x <= n; x++) {
      p[readInt()] = x;
      update(x, 1);
    }
    int lo = 1;
    int hi = n;
    for (int x = 1; x <= n; x++) {
      if (x % 2 == 1) {
        update(p[lo], -1);
        System.out.println(query(p[lo]) - query(0));
        lo++;
      } else {
        update(p[hi], -1);
        System.out.println(query(n) - query(p[hi] - 1));
        hi--;
      }
    }
  }

  static int query(int X) {
    int res = 0;
    for (int x = X; x > 0; x -= (x & -x))
      res += bit[x];
    return res;
  }

  static void update(int X, int v) {
    for (int x = X; x < 100001; x += (x & -x))
      bit[x] += v;
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
