package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cyclopian_Cash {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int[] nums = new int[n];
    for (int x = 0; x < n; x++)
      nums[x] = readInt();
    Arrays.sort(nums);
    if (n % 2 == 1)
      System.out.printf("%.1f\n", (float)nums[n / 2]);
    else
      System.out.printf("%.1f\n", ((double)nums[n / 2] + nums[n / 2 - 1]) / 2);
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
