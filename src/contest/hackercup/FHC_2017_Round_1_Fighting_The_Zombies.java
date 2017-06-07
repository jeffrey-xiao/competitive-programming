package contest.hackercup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2017_Round_1_Fighting_The_Zombies {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static int R;
  static final double EPS = 1e-9;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      R = readInt();
      Point[] p = new Point[N];

      for (int i = 0; i < N; i++)
        p[i] = new Point(readInt(), readInt());
      int ans = 0;
      for (int i = 0; i < N; i++) {
        for (int j = i; j < N; j++) {
          int bot = Math.min(p[i].y, p[j].y);
          int left = Math.min(p[i].x, p[j].x);

          for (int m = 0; m < N; m++) {
            for (int n = m; n < N; n++) {
              int bot2 = Math.min(p[m].y, p[n].y);
              int left2 = Math.min(p[m].x, p[n].x);
              double cx = left2 + R / 2.0;
              double cy = bot2 + R / 2.0;
              double r = R / Math.sqrt(2);

              boolean[] used = new boolean[N];
              int curr = 0;
              for (int k = 0; k < N; k++) {
                if (inCircle(cx, cy, r, p[k].x, p[k].y))
                  used[k] = true;
                if (inSquare(bot2, left2, R, p[k].x, p[k].y)) {
                  curr++;
                  assert inCircle(cx, cy, r, p[k].x, p[k].y);
                }
              }
              for (int k = 0; k < N; k++)
                if (!used[k] && inSquare(bot, left, R, p[k].x, p[k].y))
                  curr++;
              ans = Math.max(ans, curr);
            }
          }
        }
      }

      out.printf("Case #%d: %d\n", t, ans);
    }

    out.close();
  }

  static boolean inSquare (int bot, int left, int r, int x, int y) {
    if (bot <= y && y <= bot + r && left <= x && x <= left + r)
      return true;
    return false;
  }

  static boolean inCircle (double centerx, double centery, double r, double x, double y) {
    double dx = centerx - x;
    double dy = centery - y;
    if (dx * dx + dy * dy <= r * r * (EPS + 1))
      return true;
    return false;
  }

  static class Point {
    int x, y;

    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString () {
      return String.format("(%d, %d)", x, y);
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
