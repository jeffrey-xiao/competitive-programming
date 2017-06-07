package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class IOI_2006_Pyramid_2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static Point[][] p; // min c*d rectangle in (a-2)*(b-2) grid
  static int[][] g;
  static int n, m, a, b, c, d;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    m = readInt();
    n = readInt();
    b = readInt();
    a = readInt();
    d = readInt();
    c = readInt();
    g = new int[n + 1][m + 1];
    p = new Point[n + 1][m + 1];
    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= m; j++)
        g[i][j] = readInt() + g[i - 1][j] + g[i][j - 1] - g[i - 1][j - 1];
    for (int i = c; i <= n; i++) {
      ArrayDeque<Point> dq = new ArrayDeque<Point>();
      for (int j = d; j <= m; j++) {
        int val = g[i][j] - g[i - c][j] - g[i][j - d] + g[i - c][j - d];
        if (!dq.isEmpty() && dq.getFirst().y < (j - (b - 2) + d))
          dq.pollFirst();
        while (!dq.isEmpty() && dq.getLast().min >= val)
          dq.pollLast();
        dq.addLast(new Point(val, i, j));
        p[i][j] = dq.getFirst();
        assert p[i][j].x >= i - (a - 2) + c && p[i][j].y >= j - (b - 2) + d;
      }
    }
    for (int j = d; j <= m; j++) {
      ArrayDeque<Point> dq = new ArrayDeque<Point>();
      for (int i = c; i <= n; i++) {
        Point val = new Point(p[i][j].min, p[i][j].x, p[i][j].y);
        if (!dq.isEmpty() && dq.getFirst().x < (i - (a - 2) + c))
          dq.pollFirst();
        while (!dq.isEmpty() && dq.getLast().min >= val.min)
          dq.pollLast();
        dq.addLast(val);
        p[i][j] = dq.getFirst();
        assert p[i][j].x >= i - (a - 2) + c && p[i][j].y >= j - (b - 2) + d;
      }
    }

    long ans = -1;
    int minA = -1, minB = -1, minC = -1, minD = -1;
    for (int i = a; i <= n; i++) {
      for (int j = b; j <= m; j++) {
        int val = g[i][j] - g[i - a][j] - g[i][j - b] + g[i - a][j - b] - p[i - 1][j - 1].min;
        if (val > ans) {
          ans = val;
          minA = i - a + 1;
          minB = j - b + 1;
          minC = p[i - 1][j - 1].x - c + 1;
          minD = p[i - 1][j - 1].y - d + 1;
        }
      }
    }
    out.printf("%d %d\n%d %d\n", minB, minA, minD, minC);
    out.close();
  }

  static class Point {
    int min, x, y;

    Point (int min, int x, int y) {
      this.min = min;
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
