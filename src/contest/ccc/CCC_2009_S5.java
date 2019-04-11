package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2009_S5 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int[][] grid = new int[r][c + 1];
    int n = readInt();
    for (int z = 0; z < n; z++) {
      int y = readInt() - 1;
      int x = (r - readInt());
      int rd = readInt();
      int b = readInt();
      int s = Math.max(0, x - rd);
      int f = Math.min(r - 1, x + rd);
      for (int i = s; i <= f; i++) {
        int d = Math.abs((x - i));
        int hori = (int) Math.sqrt(rd * rd - d * d);
        int start = Math.max(0, y - hori);
        int end = Math.min(c, y + hori + 1);
        grid[i][start] += b;
        grid[i][end] -= b;
      }
    }
    int count = 0;
    int max = 0;
    for (int x = 0; x < r; x++) {
      int curr = 0;
      for (int y = 0; y < c + 1; y++) {
        curr += grid[x][y];
        grid[x][y] = curr;
        if (grid[x][y] > max) {
          max = grid[x][y];
          count = 1;
        } else if (grid[x][y] == max)
          count++;
      }
    }
    System.out.println(max + "\n" + count);
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
