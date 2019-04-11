package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2012_Distant_Pastures {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int n;
  static int a;
  static int b;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    n = readInt();
    a = readInt();
    b = readInt();
    char[][] grid = new char[n][];
    for (int x = 0; x < n; x++)
      grid[x] = next().toCharArray();
    int max = 0;
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        max = Math.max(bfs(x, y, grid), max);
      }
    }

    System.out.println(max);
  }

  private static int bfs(int x, int y, char[][] grid) {
    PriorityQueue<Point> moves = new PriorityQueue<Point>();
    int[][] min = new int[n][n];

    for (int i = 0; i < n; i++)
      Arrays.fill(min[i], 1 << 30);

    int max = 0;

    min[x][y] = 0;
    moves.add(new Point(x, y, 0));

    while (!moves.isEmpty()) {
      Point curr = moves.poll();
      x = curr.x;
      y = curr.y;

      if (min[x][y] < curr.time)
        continue;

      int time = curr.time;

      if (time > max) {
        max = time;
      }

      for (int z = 0; z < 4; z++) {
        int nextx = x + movex[z];
        int nexty = y + movey[z];

        if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= n)
          continue;

        int add = a;
        if (grid[x][y] != grid[nextx][nexty])
          add = b;

        if (min[nextx][nexty] <= add + time)
          continue;

        min[nextx][nexty] = add + time;
        moves.add(new Point(nextx, nexty, add + time));
      }

    }
    return max;
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

  static class Point implements Comparable<Point> {
    int x;
    int y;
    int time;

    Point(int x, int y, int time) {
      this.x = x;
      this.y = y;
      this.time = time;
    }

    @Override
    public int compareTo(Point p) {
      return time - p.time;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Point) {
        Point p = (Point) o;
        return x == p.x && y == p.y;
      }
      return false;
    }
  }
}
