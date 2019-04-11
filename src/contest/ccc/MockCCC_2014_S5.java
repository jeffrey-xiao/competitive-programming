package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MockCCC_2014_S5 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int l;
  static int r;
  static boolean[][] adj;
  static boolean[] visited;
  static int[] prev;

  public static void main(String[] args) throws IOException {
    l = readInt();
    r = readInt();
    prev = new int[r];
    adj = new boolean[l][r];
    for (int x = 0; x < r; x++)
      prev[x] = -1;
    int[] left = new int[l];
    for (int x = 0; x < l; x++)
      left[x] = readInt();
    for (int x = 0; x < r; x++) {
      int curr = readInt();
      for (int y = 0; y < l; y++) {
        if (gcf(left[y], curr) > 1) {
          adj[y][x] = true;
        }
      }
    }
    int count = 0;
    for (int x = 0; x < l; x++) {
      visited = new boolean[r];
      count += hungary(x) ? 1 : 0;
    }
    System.out.println(count * 2);
  }

  private static boolean hungary(int x) {
    for (int y = 0; y < r; y++) {
      if (adj[x][y] && !visited[y]) {
        visited[y] = true;
        if (prev[y] == -1 || hungary(prev[y])) {
          prev[y] = x;
          return true;
        }
      }
    }
    return false;
  }

  private static int gcf(int a, int b) {
    return b == 0 ? a : gcf(b, a % b);
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
