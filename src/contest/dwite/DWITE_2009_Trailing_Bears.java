package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DWITE_2009_Trailing_Bears {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    for (int t = 0; t < 5; t++) {
      Point a = new Point(readDouble(), readDouble());
      Point b = new Point(readDouble(), readDouble());
      Point c = new Point(readDouble(), readDouble());
      Point center = circleCenter(a, b, c);
      System.out.printf("%.2f %.2f\n", center.x, center.y);
    }
  }

  static Point circleCenter (Point A, Point B, Point C) {

    double ym1 = B.y - A.y;
    double xm1 = B.x - A.x;
    double ym2 = C.y - B.y;
    double xm2 = C.x - B.x;
    Point center = new Point(0, 0);

    double slope1 = ym1 / xm1;
    double slope2 = ym2 / xm2;
    if (slope1 == Double.POSITIVE_INFINITY)
      return new Point((B.x + C.x) / 2, (B.y + C.y) / 2);
    else if (slope2 == Double.POSITIVE_INFINITY)
      return new Point((A.x + B.x) / 2, (A.y + B.y) / 2);
    center.x = (slope1 * slope2 * (A.y - C.y) + slope2 * (A.x + B.x) - slope1 * (B.x + C.x)) / (2 * (slope2 - slope1));
    center.y = -1 * (center.x - (A.x + B.x) / 2) / slope1 + (A.y + B.y) / 2;

    return center;
  }

  static class Point {
    double x, y;

    Point (double x, double y) {
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
