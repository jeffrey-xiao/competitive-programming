package contest.acm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACM_MVP {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int r, c;
  static char[][] grid;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main (String[] args) throws IOException {
    main : for (int t = readInt(); t > 0; t--) {
      r = readInt();
      c = readInt();
      grid = new char[r][];
      int sx = 0, sy = 0, ex = 0, ey = 0, count = 0;
      Point[] minerals = new Point[2];
      for (int x = 0; x < r; x++) {
        grid[x] = readLine().toCharArray();
        for (int y = 0; y < c; y++) {
          if (grid[x][y] == 'P') {
            sx = x;
            sy = y;
          } else if (grid[x][y] == 'C') {
            ex = x;
            ey = y;
          } else if (grid[x][y] == 'M')
            minerals[count++] = new Point(x, y);
        }
      }
      int[][][] dist = new int[2][r][c];
      for (int x = 0; x < 2; x++)
        dist[x] = bfs(minerals[x].x, minerals[x].y);
      Queue<Point> moves = new LinkedList<Point>();

      boolean[][] v = new boolean[r][c];
      v[sx][sy] = true;
      moves.offer(new Point(sx, sy, 0));
      while (!moves.isEmpty()) {
        Point curr = moves.poll();
        if (curr.x == ex && curr.y == ey) {
          System.out.printf("pwned you in %d seconds eZ, learn to play n00b\n", curr.time);
          continue main;
        }
        for (int z = 0; z < 4; z++) {
          Point p = new Point(curr.x + movex[z], curr.y + movey[z], curr.time + 1);
          if (p.x < 0 || p.y < 0 || p.x >= r || p.y >= c || v[p.x][p.y] || grid[p.x][p.y] == 'W' || grid[p.x][p.y] == 'M')
            continue;
          if (grid[curr.x][curr.y] == 'U' || grid[p.x][p.y] == 'U') {
            boolean valid = false;
            for (int i = 0; i < 2; i++) {
              int d1 = dist[i][curr.x][curr.y];
              int d2 = dist[i][p.x][p.y];
              if (d2 < d1)
                valid = true;
            }
            if (!valid)
              continue;
          }
          v[p.x][p.y] = true;
          moves.offer(p);
        }
      }
      System.out.println("terran so broken, apologize for playing this race");
    }
  }

  private static int[][] bfs (int x, int y) {
    int[][] min = new int[r][c];
    boolean[][] v = new boolean[r][c];
    Queue<Point> moves = new LinkedList<Point>();
    moves.offer(new Point(x, y, 0));
    v[x][y] = true;
    min[x][y] = 0;
    while (!moves.isEmpty()) {
      Point curr = moves.poll();
      min[curr.x][curr.y] = curr.time;
      for (int z = 0; z < 4; z++) {
        Point p = new Point(curr.x + movex[z], curr.y + movey[z], curr.time + 1);
        if (p.x < 0 || p.y < 0 || p.x >= r || p.y >= c || v[p.x][p.y] || grid[p.x][p.y] == 'W' || grid[p.x][p.y] == 'M')
          continue;
        v[p.x][p.y] = true;
        moves.offer(p);
      }
    }
    return min;
  }

  static class Point {
    int x, y, time;

    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }

    Point (int x, int y, int time) {
      this.x = x;
      this.y = y;
      this.time = time;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
