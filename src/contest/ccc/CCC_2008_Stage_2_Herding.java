package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_Herding {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int r;
  static int c;
  static int count = 0;
  static Queue<Point> q;
  static char[][] grid;
  static int[][] trap;

  public static void main (String[] args) throws IOException {
    r = readInt();
    c = readInt();
    grid = new char[r][];
    for (int x = 0; x < r; x++) {
      grid[x] = next().toCharArray();
    }
    trap = new int[r][c];
    q = new LinkedList<Point>();
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        if (trap[x][y] > 0)
          continue;
        trap[x][y] = ++count;
        q.offer(new Point(x, y));
        while (!q.isEmpty()) {
          Point p = q.poll();
          checkPush(p.x - 1, p.y, 'S');
          checkPush(p.x + 1, p.y, 'N');
          checkPush(p.x, p.y - 1, 'E');
          checkPush(p.x, p.y + 1, 'W');

          if (grid[p.x][p.y] == 'N')
            p.x--;
          else if (grid[p.x][p.y] == 'S')
            p.x++;
          else if (grid[p.x][p.y] == 'E')
            p.y++;
          else if (grid[p.x][p.y] == 'W')
            p.y--;
          if (trap[p.x][p.y] == 0) {
            trap[p.x][p.y] = count;
            q.offer(new Point(p.x, p.y));
          }
        }
      }
    }
    System.out.println(count);
  }

  private static void checkPush (int x, int y, char d) {
    if (x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == d && trap[x][y] == 0) {
      trap[x][y] = count;
      q.offer(new Point(x, y));
    }
  }

  static class Point {
    int x;
    int y;

    Point (int x, int y) {
      this.x = x;
      this.y = y;
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
