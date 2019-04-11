package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_J2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int s = readInt();
    int e = readInt();
    int count = 0;
    for (int x = s; x <= e; x++)
      count += (isRSA(x) ? 1 : 0);
    System.out.printf("The number of RSA numbers between %d and %d is %d\n", s, e, count);
  }

  private static boolean isRSA(int n) {
    int count = 0;
    for (int x = 1; x * x <= n; x++) {
      if (n % x == 0) {
        if (x * x == n)
          count++;
        else
          count += 2;
      }
    }
    return count == 4;
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
