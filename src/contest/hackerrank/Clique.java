package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Clique {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int m = readInt();
      int lo = 1;
      int hi = n + 1;
      while (lo + 1 < hi) {
        int mid = (hi + lo) / 2;
        if (getUpperBound(n, mid) < m)
          lo = mid;
        else
          hi = mid;
      }
      out.println(hi);
    }

    out.close();
  }

  static double getUpperBound(int n, int r) {
    double g1 = n % r;
    double g2 = r - g1;
    double sz1 = n / r + 1;
    double sz2 = n / r;
    return g1 * sz1 * g2 * sz2 + g1 * (g1 - 1) * sz1 * sz1 / 2 + g2 * (g2 - 1) * sz2 * sz2 / 2;
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
