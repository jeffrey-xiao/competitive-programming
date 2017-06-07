package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Benergy_Transfer {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] a, b, va, vb;
  static final double EPS = 1e-8;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    a = new int[N];
    b = new int[N];
    va = new int[N];
    vb = new int[N];

    for (int i = 0; i < N; i++)
      a[i] = readInt();

    for (int i = 0; i < N; i++)
      b[i] = readInt();

    for (int i = 0; i < N; i++)
      va[i] = readInt();

    for (int i = 0; i < N; i++)
      vb[i] = readInt();

    if (getDist(0) <= getDist(EPS))
      out.println(0);
    else
      out.println(ternarySearch(0, 1e10));
    out.close();
  }

  static double getDist (double t) {
    double ret = 0;
    for (int i = 0; i < N; i++) {
      double diff = (b[i] - a[i]) + t * (vb[i] - va[i]);
      ;
      ret += diff * diff;
    }
    return ret;
  }

  static double ternarySearch (double lo, double hi) {
    while (Math.abs(hi - lo) >= EPS) {
      double mid1 = (lo * 2 + hi) / 3.0;
      double mid2 = (lo + hi * 2) / 3.0;

      if (getDist(mid1) >= getDist(mid2))
        lo = mid1;
      else
        hi = mid2;
    }
    return (lo + hi) / 2;

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
