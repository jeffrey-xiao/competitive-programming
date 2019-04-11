package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2008_Linear_Garden_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[][][] dp;
  static long k;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    k = readLong();
    char[] s = next().toCharArray();
    int curr = 0, min = 0, max = 0;
    long sum = 0;
    for (int x = 0; x < n; x++) {
      if (s[x] == 'P') {
        sum += compute(min, Math.max(max, curr + 1), n - x - 1, curr + 1);
        curr--;
        min = Math.min(min, curr);
      } else {
        curr++;
        max = Math.max(max, curr);
      }
    }
    System.out.println((sum + 1) % k);
  }

  static long compute(int min, int max, int n, int curr) {
    if (max - min > 2)
      return 0;
    if (max - min == 2)
      return pow(2, n / 2);
    return (pow(2, n / 2) * (n % 2 == 1 ? 3 : 2) - 1);
  }

  static long pow(long n, int m) {
    if (m == 0)
      return 1;
    if (m == 1)
      return n;
    if (m % 2 == 0)
      return pow(n * n % k, m / 2) % k;
    return n * pow(n * n % k, m / 2) % k;
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