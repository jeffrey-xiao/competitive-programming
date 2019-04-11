package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2009_CUDOVISTE {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    char[][] g = new char[r][];
    for (int x = 0; x < r; x++)
      g[x] = next().toCharArray();
    int[] counts = new int[5];
    for (int x = 1; x < r; x++) {
      for (int y = 1; y < c; y++) {
        int count = 0;
        if (g[x][y] == 'X')
          count++;
        if (g[x - 1][y] == 'X')
          count++;
        if (g[x][y - 1] == 'X')
          count++;
        if (g[x - 1][y - 1] == 'X')
          count++;
        if (g[x][y] != '#' && g[x - 1][y] != '#' && g[x][y - 1] != '#' && g[x - 1][y - 1] != '#')
          counts[count]++;
      }
    }
    for (int i : counts)
      System.out.println(i);
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
