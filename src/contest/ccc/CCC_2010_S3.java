package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2010_S3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static final int LEN = 1000000;
  static int[] h;
  static int n;

  public static void main (String[] args) throws IOException {
    n = readInt();
    h = new int[2 * n];
    for (int x = 0; x < n; x++)
      h[x] = readInt();

    Arrays.sort(h, 0, n);
    for (int x = 0; x < n; x++)
      h[x + n] = h[x] + LEN;

    int k = readInt();

    int lo = 0;
    int hi = LEN;

    while (lo < hi) {
      int mid = (hi + lo) / 2;
      if (k >= poss(mid))
        hi = mid;
      else
        lo = mid + 1;
    }
    System.out.println(lo);
  }

  static int poss (int mid) {
    int hydrants = 1 << 30;
    for (int i = 0; i < Math.min(n, 10); i++)
      hydrants = Math.min(hydrants, compute(i, mid));
    return hydrants;
  }

  static int compute (int j, int mid) {
    int curr = j, hydrants = 1;
    for (int i = j; i < n + j; i++)
      if (h[i] - h[curr] > 2 * mid) {
        curr = i;
        hydrants++;
      }
    return hydrants;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
