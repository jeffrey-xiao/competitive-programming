package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_Sabotage {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] cows;
  static int n;

  public static void main(String[] args) throws IOException {
    n = readInt();
    cows = new int[n];
    for (int x = 0; x < n; x++)
      cows[x] = readInt();
    double value = bsearch();
    System.out.printf("%.3f\n", Math.round(value * 1000) / 1000d);
  }

  private static double bsearch() {
    double lo = 1;
    double hi = Integer.MAX_VALUE;
    while (Math.round(lo * 1000) / 1000.0d != Math.round(hi * 1000) / 1000.0d) {
      double mid = (lo + hi) / 2;
      if (isValid(mid))
        hi = mid;
      else
        lo = mid;
    }
    return lo;
  }

  private static boolean isValid(double mid) {

    double total = 0;
    for (int x = 0; x < n; x++)
      total += cows[x];
    // start removing at middle. Cannot extend past last.
    double curr = cows[1] - mid;
    double max = cows[1] - mid;
    for (int x = 2; x < n - 1; x++) {
      curr = Math.max(0, curr);
      curr = curr + cows[x] - mid;
      max = Math.max(max, curr);
    }
    return max >= total - mid * n;
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
