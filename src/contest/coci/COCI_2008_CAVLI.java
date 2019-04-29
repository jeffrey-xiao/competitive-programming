package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class COCI_2008_CAVLI {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    ArrayList<Point> points = new ArrayList<Point>();
    for (int x = 0; x < n; x++)
      points.add(new Point(readInt(), readInt()));
    String s = next();
    for (int k = 0; k < n - 2; k++) {
      ArrayList<Point> u = new ArrayList<Point>();
      ArrayList<Point> l = new ArrayList<Point>();
      Collections.sort(points);
      for (int x = 0; x < points.size(); x++) {
        int i = l.size();
        while (i >= 2 && ccw(l.get(i - 2), l.get(i - 1), points.get(x)) <= 0) {
          l.remove(i - 1);
          i = l.size();
        }
        l.add(points.get(x));
      }
      for (int x = points.size() - 1; x >= 0; x--) {
        int i = u.size();
        while (i >= 2 && ccw(u.get(i - 2), u.get(i - 1), points.get(x)) <= 0) {
          u.remove(i - 1);
          i = u.size();
        }
        u.add(points.get(x));
      }
      u.remove(u.size() - 1);
      l.remove(l.size() - 1);
      l.addAll(u);
      long lowX = Integer.MAX_VALUE;
      int lowXI = 0;
      long highX = Integer.MIN_VALUE;
      int highXI = 0;
      long lowY = Integer.MAX_VALUE;
      int lowYI = 0;
      long highY = Integer.MIN_VALUE;
      int highYI = 0;
      System.out.printf("%.1f%n", getArea(l));
      for (int i = 0; i < l.size(); i++) {
        long x = l.get(i).x;
        long y = l.get(i).y;
        if (x > highX) {
          highX = x;
          highXI = i;
        }
        if (x < lowX) {
          lowX = x;
          lowXI = i;
        }
        if (y > highY) {
          highY = y;
          highYI = i;
        }
        if (y < lowY) {
          lowY = y;
          lowYI = i;
        }
      }
      if (s.charAt(k) == 'L') {
        points.remove(l.get(lowXI));
      } else if (s.charAt(k) == 'R') {
        points.remove(l.get(highXI));
      } else if (s.charAt(k) == 'U') {
        points.remove(l.get(highYI));
      } else {
        points.remove(l.get(lowYI));
      }
    }
  }

  private static double getArea(ArrayList<Point> l) {
    double area = 0;
    int n = l.size();
    for (int z = 0; z < n; z++) {
      area += ((l.get(z).x * l.get((z + 1) % n).y) - (l.get(z).y * l.get((z + 1) % n).x)) / 2.0d;
    }
    return area;
  }

  // CCW > 0 counter clockwise
  static long ccw(Point p1, Point p2, Point p3) {
    return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
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
    long x, y;

    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Point o) {
      if (x == o.x)
        return y < o.y ? -1 : 1;
      return x < o.x ? -1 : 1;
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + ")";
    }
  }
}
