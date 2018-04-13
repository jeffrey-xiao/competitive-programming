package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Qualification_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static double EPS = 1e-12;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

    for (int t = 1; t <= T; t++) {
      Point[] points = new Point[] {
        new Point(0.5, 0.5, 0.5),
        new Point(-0.5, 0.5, 0.5),
        new Point(0.5, -0.5, 0.5),
        new Point(-0.5, -0.5, 0.5),
        new Point(0.5, 0.5, -0.5),
        new Point(-0.5, 0.5, -0.5),
        new Point(0.5, -0.5, -0.5),
        new Point(-0.5, -0.5, -0.5),
      };
      Point[] currPoints = new Point[8];

      double targetArea = readDouble();
      if (targetArea > Math.sqrt(2)) {
        for (int i = 0; i < 8; i++) {
          currPoints[i] = rotateX(points[i], Math.PI / 4);
          points[i] = rotateX(points[i], Math.PI / 4);
        }
      } else {
        for (int i = 0; i < 8; i++) {
          currPoints[i] = rotateX(points[i], 0);
        }
      }

      double lo = 0, hi = Math.PI / 4;
      while (Math.abs(targetArea - getArea(currPoints)) > EPS) {
        double mid = (lo + hi) / 2;
        for (int i = 0; i < 8; i++)
          currPoints[i] = rotateZ(points[i], mid);
        if (getArea(currPoints) < targetArea) {
          lo = mid;
        } else {
          hi = mid;
        }
      }

      assert(Math.abs(targetArea - getArea(currPoints)) <= EPS);

      out.printf("Case #%d:\n", t);
      printCenters(currPoints, targetArea);
    }

    out.close();
  }

  static void printCenters (Point[] points, double targetArea) {
    Point p1 = getMiddle(points[0], points[3]);
    Point p2 = getMiddle(points[0], points[5]);
    Point p3 = getMiddle(points[0], points[6]);

    double dist1 = Math.sqrt(p1.x * p1.x + p1.y * p1.y + p1.z * p1.z);
    double dist2 = Math.sqrt(p2.x * p2.x + p2.y * p2.y + p2.z * p2.z);
    double dist3 = Math.sqrt(p3.x * p3.x + p3.y * p3.y + p3.z * p3.z);

    assert(Math.abs(dist1 - 0.5) <= EPS);
    assert(Math.abs(dist2 - 0.5) <= EPS);
    assert(Math.abs(dist3 - 0.5) <= EPS);
    assert((Math.abs((p1.x * p2.x + p1.y * p2.y + p1.z * p2.z) / (dist1 * dist2)) - 1) <= EPS);
    assert((Math.abs((p1.x * p3.x + p1.y * p3.y + p1.z * p3.z) / (dist1 * dist3)) - 1) <= EPS);
    assert((Math.abs((p2.x * p3.x + p2.y * p3.y + p2.z * p3.z) / (dist2 * dist3)) - 1) <= EPS);

    Point[] compute = new Point[] {
      add(add(p1, p2), p3),
      add(add(neg(p1), p2), p3),
      add(add(p1, neg(p2)), p3),
      add(add(neg(p1), neg(p2)), p3),
      add(add(p1, p2), neg(p3)),
      add(add(neg(p1), p2), neg(p3)),
      add(add(p1, neg(p2)), neg(p3)),
      add(add(neg(p1), neg(p2)), neg(p3))
    };
    assert(Math.abs(targetArea - getArea(compute)) <= EPS);

    out.println(p1);
    out.println(p2);
    out.println(p3);
  }

  static Point getMiddle (Point p1, Point p2) {
    return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2, (p1.z + p2.z) / 2);
  }

  static Point neg (Point p) {
    return new Point(-p.x, -p.y, -p.z);
  }

  static Point add (Point p1, Point p2) {
    return new Point(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
  }

  static Point rotateX (Point p, double angle) {
    return new Point(
      p.x,
      p.y * Math.cos(angle) - p.z * Math.sin(angle),
      p.y * Math.sin(angle) + p.z * Math.cos(angle)
    );
  }

  static Point rotateZ (Point p, double angle) {
    return new Point(
      p.x * Math.cos(angle) - p.y * Math.sin(angle),
      p.x * Math.sin(angle) + p.y * Math.cos(angle),
      p.z
    );
  }

  static double getArea (Point[] points) {
    ArrayList<Point> u = new ArrayList<Point>();
    ArrayList<Point> l = new ArrayList<Point>();

    Arrays.sort(points);

    for (int x = 0; x < points.length; x++) {
      int i = l.size();
      while (i >= 2 && ccw(l.get(i - 2), l.get(i - 1), points[x]) <= 0) {
        l.remove(i - 1);
        i = l.size();
      }
      l.add(points[x]);
    }

    for (int x = points.length - 1; x >= 0; x--) {
      int i = u.size();
      while (i >= 2 && ccw(u.get(i - 2), u.get(i - 1), points[x]) <= 0) {
        u.remove(i - 1);
        i = u.size();
      }
      u.add(points[x]);
    }

    u.remove(u.size() - 1);
    l.remove(l.size() - 1);
    l.addAll(u);


    double area = 0;
    int j = l.size() - 1;
    for (int i = 0; i < l.size(); i++) {
      area += (l.get(j).x + l.get(i).x) * (l.get(j).z - l.get(i).z);
      j = i;
    }
    return Math.abs(area / 2);
  }

  static double ccw (Point p1, Point p2, Point p3) {
    return (p2.x - p1.x) * (p3.z - p1.z) - (p2.z - p1.z) * (p3.x - p1.x);
  }

  static class Point implements Comparable<Point> {
    double x, y, z;
    Point (double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public int compareTo (Point p) {
      if (Math.abs(x - p.x) <= EPS) {
        return new Double(z).compareTo(p.z);
      }
      return new Double(x).compareTo(p.x);
    }

    @Override
    public String toString () {
      return String.format("%.12f %.12f %.12f", x, y, z);
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
