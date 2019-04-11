package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2009_VUK {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    char[][] grid = new char[r][];
    Queue<Point> q = new LinkedList<Point>();
    int sx = 0, sy = 0, ex = 0, ey = 0;
    int[][] min = new int[r][c];
    for (int x = 0; x < r; x++) {
      grid[x] = next().toCharArray();
      for (int y = 0; y < c; y++) {
        min[x][y] = -1;
        if (grid[x][y] == '+') {
          q.offer(new Point(x, y, 0));
          min[x][y] = 0;
        } else if (grid[x][y] == 'V') {
          sx = x;
          sy = y;
        } else if (grid[x][y] == 'J') {
          ex = x;
          ey = y;
        }
      }
    }
    while (!q.isEmpty()) {
      Point curr = q.poll();
      for (int z = 0; z < 4; z++) {
        int x = curr.x + movex[z];
        int y = curr.y + movey[z];
        if (x < 0 || y < 0 || x >= r || y >= c || min[x][y] != -1)
          continue;
        min[x][y] = curr.moves + 1;
        q.offer(new Point(x, y, min[x][y]));
      }
    }
    PriorityQueue<Point> pq = new PriorityQueue<Point>();
    pq.offer(new Point(sx, sy, min[sx][sy]));
    boolean[][] v = new boolean[r][c];
    v[sx][sy] = true;
    while (!pq.isEmpty()) {
      Point curr = pq.poll();
      if (curr.x == ex && curr.y == ey) {
        System.out.println(curr.moves);
        return;
      }
      for (int z = 0; z < 4; z++) {
        int x = curr.x + movex[z];
        int y = curr.y + movey[z];
        if (x < 0 || y < 0 || x >= r || y >= c || v[x][y])
          continue;
        v[x][y] = true;
        pq.offer(new Point(x, y, Math.min(curr.moves, min[x][y])));
      }
    }
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Point implements Comparable<Point> {
    int x, y, moves;

    Point(int x, int y, int moves) {
      this.x = x;
      this.y = y;
      this.moves = moves;
    }

    @Override
    public int compareTo(Point o) {
      return o.moves - moves;
    }
  }
}
