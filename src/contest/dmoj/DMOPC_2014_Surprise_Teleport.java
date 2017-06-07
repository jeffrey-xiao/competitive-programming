package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class DMOPC_2014_Surprise_Teleport {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int r = readInt();
    int c = readInt();
    int sx = readInt();
    int sy = readInt();
    int ex = readInt();
    int ey = readInt();
    char[][] g = new char[r][c];
    for (int i = 0; i < r; i++)
      g[i] = next().toCharArray();
    int t = readInt();
    Point[] tele = new Point[t];
    for (int i = 0; i < t; i++)
      tele[i] = new Point(readInt(), readInt());
    Queue<Point> q = new ArrayDeque<Point>();
    int[][] dist = new int[r][c];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++)
        dist[i][j] = 1 << 30;
    dist[sx][sy] = 0;
    q.offer(new Point(sx, sy));
    while (!q.isEmpty()) {
      Point curr = q.poll();
      for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
          if (Math.abs(dx) + Math.abs(dy) != 1)
            continue;
          int nx = curr.x + dx;
          int ny = curr.y + dy;
          if (nx < 0 || nx >= r || ny < 0 || ny >= c || g[nx][ny] == 'X' || dist[nx][ny] <= dist[curr.x][curr.y] + 1)
            continue;
          dist[nx][ny] = dist[curr.x][curr.y] + 1;
          q.offer(new Point(nx, ny));

        }
      }
    }
    int max = 0;
    for (int i = 0; i < t; i++)
      max = Math.max(max, dist[ex][ey] - dist[tele[i].x][tele[i].y]);
    out.println(max);
    out.close();
  }

  static class Point {
    int x, y;

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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
