package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class STNBD_Fianna_Ray_Ordesia {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    Point a = new Point(readDouble(), readDouble());
    Point b = new Point(readDouble(), readDouble());
    double d = readDouble();
    double slope = -1 / ((a.y - b.y) / (a.x - b.x));
    Point mid = new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    double A, B, C;
    double x1, x2, y1, y2;
    if (slope == Double.NEGATIVE_INFINITY || slope == Double.POSITIVE_INFINITY) {
      C = -(d * d - a.y * a.y + 2 * a.y * mid.y - mid.y * mid.y - a.x * a.x);
      B = -2 * a.x;
      A = 1;
      x1 = (-B + Math.sqrt(B * B - 4 * A * C)) / (2 * A);
      x2 = (-B - Math.sqrt(B * B - 4 * A * C)) / (2 * A);
      x1 = mid.x;
      x2 = mid.x;
      y1 = y2 = mid.y;
    } else {
      double c = mid.y - slope * mid.x;
      C = a.x * a.x + a.y * a.y - 2 * a.y * c + c * c - d * d;
      B = -2 * a.x - 2 * a.y * slope + 2 * slope * c;
      A = 1 + slope * slope;
      x1 = (-B + Math.sqrt(B * B - 4 * A * C)) / (2 * A);
      x2 = (-B - Math.sqrt(B * B - 4 * A * C)) / (2 * A);
      y1 = slope * x1 + c;
      y2 = slope * x2 + c;
    }
    Point[] p = new Point[2];
    p[0] = new Point(x1, y1);
    p[1] = new Point(x2, y2);
    Arrays.sort(p);
    if (B * B - 4 * A * C == 0 || x1 == x2) {
      System.out.printf("%f %f\n", p[0].x, p[0].y);
    } else {
      System.out.printf("%.6f %.6f\n", p[0].x, p[0].y);
      System.out.printf("%.6f %.6f\n", p[1].x, p[1].y);
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
    double x, y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Point o) {
      if (o.x == x)
        return y < o.y ? -1 : 1;
      return x < o.x ? -1 : 1;
    }
  }
}
