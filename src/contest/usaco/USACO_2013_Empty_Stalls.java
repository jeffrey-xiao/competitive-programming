package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2013_Empty_Stalls {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    int[] cows = new int[n];
    for (; k > 0; k--) {
      int x = readInt();
      int y = readInt();
      long a = readLong();
      long b = readLong();
      for (; y > 0; y--) {
        cows[(int)((a * y + b) % n)] += x;
      }
    }
    int curr = 0;
    for (int x = 0; x < n; x++) {
      curr += cows[x];
      if (curr > 0)
        curr--;
    }
    for (int x = 0; x < n; x++) {
      curr += cows[x];
      if (curr > 0)
        curr--;
      else {
        System.out.println(x);
        break;
      }
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
