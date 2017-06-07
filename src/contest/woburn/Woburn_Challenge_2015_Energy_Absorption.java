package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Energy_Absorption {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static int[] damage;
  static int[] seg;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    damage = new int[N + 1];
    seg = new int[4 * N];
    for (int i = 1; i <= N; i++) {
      damage[i] = readInt() + damage[i - 1];
    }
    build(1, 0, N);
    int[] min = new int[N + 1];
    for (int i = 0; i <= N; i++)
      min[i] = query(1, 0, N, 0, i);
    for (int i = 0; i < M; i++) {
      int l = 0;
      int r = N;
      int curr = readInt();
      while (l <= r) {
        int mid = (l + r) / 2;
        if (min[mid] < -curr)
          r = mid - 1;
        else
          l = mid + 1;
      }
      out.println(l - 1);
    }
    out.close();
  }

  static int query (int n, int l, int r, int ql, int qr) {
    if (ql == l && qr == r)
      return seg[n];
    int mid = (l + r) >> 1;
    if (qr <= mid)
      return query(n << 1, l, mid, ql, qr);
    else if (ql > mid)
      return query(n << 1 | 1, mid + 1, r, ql, qr);
    return Math.min(query(n << 1, l, mid, ql, mid), query(n << 1 | 1, mid + 1, r, mid + 1, qr));
  }

  static void build (int n, int l, int r) {
    if (l == r) {
      seg[n] = damage[l];
      return;
    }
    int mid = (l + r) >> 1;
    build(n << 1, l, mid);
    build(n << 1 | 1, mid + 1, r);
    seg[n] = Math.min(seg[n << 1], seg[n << 1 | 1]);
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
