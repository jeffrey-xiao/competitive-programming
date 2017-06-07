package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2009_PROGRAM {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    int[] nums = new int[n + 1];
    int[] mod = new int[k];
    for (int x = 0; x < k; x++)
      mod[x] = readInt();
    Arrays.sort(mod);
    for (int x = 0; x < k; x++) {
      int curr = mod[x];
      int count = 1;
      while (x + 1 < k && mod[x + 1] == curr) {
        count++;
        x++;
      }
      for (int y = 1; y <= n; y += curr)
        nums[y] += count;
    }
    mod = null;
    long[] sum = new long[n + 1];
    for (int y = 1; y <= n; y++) {
      sum[y] = nums[y] + sum[y - 1];
    }
    int q = readInt();
    for (int x = 0; x < q; x++) {
      int a = readInt();
      int b = readInt() + 1;
      System.out.println(sum[b] - sum[a]);
    }
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
