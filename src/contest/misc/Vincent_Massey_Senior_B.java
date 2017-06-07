package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Vincent_Massey_Senior_B {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int[] nums = new int[n + 1];
    for (int m = readInt(); m > 0; m--)
      nums[readInt() + 1]++;
    for (int x = 1; x <= n; x++)
      nums[x] += nums[x - 1];
    for (int q = readInt(); q > 0; q--)
      System.out.println(-nums[readInt()] + nums[readInt() + 1]);
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
