package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Pollos {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[] dist;
  static boolean[] obliged;
  static int n, k;

  public static void main(String[] args) throws IOException {
    n = readInt();
    k = readInt();
    dist = new int[n];
    obliged = new boolean[n];
    for (int x = 0; x < n; x++) {
      dist[x] = readInt();
      obliged[x] = readInt() == 1;
    }
    int lo = 0, hi = 1 << 30;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (getMoves(mid) <= k) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    System.out.println(lo > (1 << 30) ? "DEA Bust!" : lo);
  }

  private static int getMoves(int t) {
    int curr = 0;
    int total = 0;
    if (dist[0] - curr > t)
      return Integer.MAX_VALUE;
    for (int x = 0; x < n - 1;) {
      if (obliged[x]) {
        if (dist[x] - curr > t)
          return Integer.MAX_VALUE;
        total++;
        curr = dist[x];
      } else if (dist[x + 1] - curr > t) {
        total++;
        curr = dist[x];
      }
      x++;
    }
    if (dist[n - 1] - curr > t)
      return Integer.MAX_VALUE;
    return total + 1;
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
