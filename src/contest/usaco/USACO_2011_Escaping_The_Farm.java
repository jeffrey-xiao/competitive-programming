package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2011_Escaping_The_Farm {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int max = 1;

  public static void main(String[] args) throws IOException {
    int numOfCows = readInt();
    int[] cows = new int[numOfCows];
    for (int x = 0; x < numOfCows; x++)
      cows[x] = readInt();
    getSolution(0, 0, 0, numOfCows, cows); // 0 index, 1 sum, 2 curr, 3 max

    System.out.println(max);
  }

  private static void getSolution(int i, int j, int k, int end, int[] cows) {
    if (k > max)
      max = k;
    if (i == end)
      return;
    if (isValid(cows[i], j)) {
      getSolution(i + 1, cows[i] + j, k + 1, end, cows);
      getSolution(i + 1, j, k, end, cows);
    } else
      getSolution(i + 1, j, k, end, cows);
  }

  private static boolean isValid(int i, int j) {
    while (i != 0 && j != 0) {
      if (i % 10 + j % 10 >= 10)
        return false;
      i /= 10;
      j /= 10;
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
