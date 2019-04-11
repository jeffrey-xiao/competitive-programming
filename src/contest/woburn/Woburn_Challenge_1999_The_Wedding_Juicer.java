package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Woburn_Challenge_1999_The_Wedding_Juicer {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int[][] g;
  static boolean[][] v;

  static int[] movex = {-1, 1, 0, 0};
  static int[] movey = {0, 0, -1, 1};
  static int r, c;
  static int min = 1 << 30;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      r = readInt();
      c = readInt();
      g = new int[r][c];
      v = new boolean[r][c];
      int ans = 0;
      Point[] ls = new Point[r * c];
      int cnt = 0;
      for (int i = 0; i < r; i++)
        for (int j = 0; j < c; j++) {
          g[i][j] = readInt();
          ls[cnt++] = new Point(i, j, g[i][j]);
        }
      Arrays.sort(ls);
      int prev = -1;
      for (int i = 0; i < r * c; i++) {
        if (ls[i].h != prev) {
          prev = ls[i].h;
          System.out.println(prev);
          v = new boolean[r][c];
        }
        if (!v[ls[i].x][ls[i].y]) {
          min = 1 << 30;
          int res = 0;
          Queue<Point> q = new ArrayDeque<Point>();
          q.offer(new Point(ls[i].x, ls[i].y));
          v[ls[i].x][ls[i].y] = true;
          while (!q.isEmpty()) {
            Point curr = q.poll();
            res++;
            if (curr.x == 0 || curr.y == 0 || curr.x == r - 1 || curr.y == c - 1) {
              res = -10000;
            }
            for (int k = 0; k < 4; k++) {
              int nx = curr.x + movex[k];
              int ny = curr.y + movey[k];
              if (nx < 0 || nx >= r || ny < 0 || ny >= c || v[nx][ny])
                continue;

              if (g[nx][ny] <= g[curr.x][curr.y]) {
                v[nx][ny] = true;
                q.offer(new Point(nx, ny));
              } else {
                if (g[nx][ny] > ls[i].h)
                  min = Math.min(min, g[nx][ny]);
              }
            }
          }
          if (res > 0 && min != 1 << 30) {
            System.out.println("MIN " + min + " " + res);
            ans += res * (min - ls[i].h);
          }
        }
      }
      System.out.println(ans);
    }

    pr.close();
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
    int x, y, h;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    Point(int x, int y, int h) {
      this.x = x;
      this.y = y;
      this.h = h;
    }

    @Override
    public int compareTo(Point o) {
      return h - o.h;
    }
  }
}
