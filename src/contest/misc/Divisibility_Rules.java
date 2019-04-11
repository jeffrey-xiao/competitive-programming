package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Divisibility_Rules {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int testCases = readInt(); testCases > 0; testCases--) {
      int b = readInt(); // base
      int t = readInt(); // divisibility rule for
      // (BA+1)=0 mod t
      int smallestNValue = -t - 1;
      int smallestPValue = t + 1;
      for (int x = -t; x <= t; x++) {
        if ((b * x + 1) % t == 0) {
          if (x < 0 && x > smallestNValue)
            smallestNValue = x;
          else if (x >= 0 && x < smallestPValue)
            smallestPValue = x;
        }
      }
      if (-smallestNValue == smallestPValue)
        System.out.println("+" + smallestPValue);
      else
        System.out.println(-smallestNValue < smallestPValue ? ("+" + (-smallestNValue)) : "-" + smallestPValue);
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
