package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_PASCAL {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    long n = readInt();
    long factor = 0;
    for (int x = 2; x * x <= n; x++) {
      if (n % x == 0) {
        factor = x;
        break;
      }
    }
    if (factor == 0)
      factor = n;
    System.out.println(n - n / factor);
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
