package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class COCI_2006_BOND {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[][] prob;
  static double ans = 0.0d;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    prob = new int[n][n];
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        prob[x][y] = readInt();
      }
    }
    HashSet<Integer> v = new HashSet<Integer>();
    compute(0, n, v, 1.0d);
    System.out.printf("%.6f%n", ans * 100);
  }

  private static void compute(int i, int n, HashSet<Integer> v, double a) {
    if (i == n) {
      System.out.println(a);
      ans = Math.max(a, ans);
      return;
    }

    for (int x = 0; x < n; x++) {
      if (v.contains(x))
        continue;
      v.add(x);
      double next = a * ((prob[i][x]) / 100.0d);
      if (next < ans) {
        v.remove(x);
        continue;
      }
      compute(i + 1, n, v, next);
      v.remove(x);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
