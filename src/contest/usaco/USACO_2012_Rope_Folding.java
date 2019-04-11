package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2012_Rope_Folding {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int l = readInt();
    int[] seq = new int[n];
    for (int x = 0; x < n; x++)
      seq[x] = readInt();
    Arrays.sort(seq);
    int[] diff = new int[n - 1];
    for (int x = 0; x < n - 1; x++)
      diff[x] = seq[x + 1] - seq[x];
    int count = 0;
    n--;
    for (int x = 0; x < n - 1; x++) {
      if (checkPalin(0, x, diff))
        count++;
      if (checkPalin(n - 1 - x, n - 1, diff))
        count++;
    }
    if (checkPalin(0, n - 1, diff))
      count++;
    System.out.println(count);
  }

  private static boolean checkPalin(int start, int end, int[] diff) {
    int l = end - start + 1;
    for (int y = 0; y < l / 2; y++) {
      if (diff[start + y] != diff[end - y])
        return false;
    }
    return true;
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
