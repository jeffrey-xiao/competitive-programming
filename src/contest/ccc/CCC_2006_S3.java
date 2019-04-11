package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2006_S3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    Point a = new Point(readInt(), readInt());
    Point b = new Point(readInt(), readInt());
    Line l = a.getLine(b);
    int n = readInt();
    int count = 0;
    main:
    for (int x = 0; x < n; x++) {
      int points = readInt();
      Point[] ps = new Point[points + 1];
      for (int y = 0; y < points; y++)
        ps[y] = new Point(readInt(), readInt());
      ps[points] = ps[0];
      for (int y = 0; y < points; y++) {
        Line next = ps[y].getLine(ps[y + 1]);
        if (checkIntersection(l, next, a, b, ps[y], ps[y + 1])) {
          count++;
          continue main;
        }
      }
    }
    System.out.println(count);
  }

  private static boolean checkIntersection(Line a, Line b, Point w, Point x, Point y, Point z) {
    if (a.m == b.m)
      return false;
    int y1 = Math.min(w.y, x.y);
    int y2 = Math.max(w.y, x.y);
    int y3 = Math.min(y.y, z.y);
    int y4 = Math.max(y.y, z.y);
    int x1 = Math.min(w.x, x.x);
    int x2 = Math.max(w.x, x.x);
    int x3 = Math.min(y.x, z.x);
    int x4 = Math.max(y.x, z.x);
    if (a.m == Double.POSITIVE_INFINITY || b.m == Double.POSITIVE_INFINITY) {
      double yy = 0;
      if (a.m == Double.POSITIVE_INFINITY) {
        yy = b.m * w.x + b.b;
      } else {
        yy = a.m * y.x + a.b;
      }
      return y1 <= yy && yy <= y2 && y3 <= yy && yy <= y4;
    }
    double xx = (b.b - a.b) / (a.m - b.m);

    double yy1 = (a.m) * xx + a.b;
    double yy2 = (b.m) * xx + b.b;
    if (yy1 != yy2)
      return false;
    return y1 <= yy1 && yy2 <= y2 && y3 <= yy2 && yy2 <= y4 && x1 <= xx && xx <= x2 && x3 <= xx && xx <= x4;

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

  static class Line {
    double m;
    double b;

    Line(double m, double b) {
      this.m = m;
      this.b = b;
    }
  }

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    Line getLine(Point a) {
      double m = ((double) this.y - a.y) / (this.x - a.x);
      if (m == Double.NEGATIVE_INFINITY)
        m = Double.POSITIVE_INFINITY;
      double b = y - m * this.x;
      return new Line(m, b);
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ") ";
    }
  }
}
