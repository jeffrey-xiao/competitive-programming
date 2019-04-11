package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2013_Tractor {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int n;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    n = readInt();
    int[][] grid = new int[n][n];
    int upper = 0;
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        grid[x][y] = readInt();
        upper = Math.max(grid[x][y], upper);
      }
    }
    int lower = 0;
    while (upper - lower > 1) {
      int mid = (lower + upper) / 2;
      boolean[][] visited = new boolean[n][n];
      int max = 0;
      for (int x = 0; x < n; x++) {
        for (int y = 0; y < n; y++) {
          if (!visited[x][y])
            max = Math.max(max, floodFill(x, y, mid, grid, visited));
        }
      }

      if (max >= n * n / 2)
        upper = mid;
      else
        lower = mid;
    }
    boolean[][] visited = new boolean[n][n];
    int max = 0;
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        if (!visited[x][y])
          max = Math.max(max, floodFill(x, y, lower, grid, visited));
      }
    }
    if (max >= n * n / 2)
      System.out.println(lower);
    else
      System.out.println(upper);
  }

  private static int floodFill(int x, int y, int mid, int[][] grid, boolean[][] visited) {
    int ans = 0;
    Queue<int[]> moves = new LinkedList<int[]>();
    moves.add(new int[]{x, y, grid[x][y]});
    visited[x][y] = true;
    while (!moves.isEmpty()) {
      int[] curr = moves.poll();
      ans++;
      for (int z = 0; z < 4; z++) {

        int nextx = curr[0] + movex[z];
        int nexty = curr[1] + movey[z];

        if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= n || visited[nextx][nexty] || Math.abs(grid[nextx][nexty] - curr[2]) > mid)
          continue;
        moves.add(new int[]{nextx, nexty, grid[nextx][nexty]});
        visited[nextx][nexty] = true;
      }
    }
    return ans;
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
