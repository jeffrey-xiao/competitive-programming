package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_1998_Y2K_Why_Too_Bad {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    Point[] points = new Point[n];
    for (int x = 0; x < n; x++)
      points[x] = new Point(readInt(), readInt());
    ArrayList<Point> u = new ArrayList<Point>();
    ArrayList<Point> l = new ArrayList<Point>();
    Arrays.sort(points);
    for (int x = 0; x < n; x++) {
      int i = l.size();
      while (i >= 2 && ccw(l.get(i - 2), l.get(i - 1), points[x]) <= 0) {

        l.remove(i - 1);
        i = l.size();
      }
      l.add(points[x]);
    }
    for (int x = n - 1; x >= 0; x--) {
      int i = u.size();
      while (i >= 2 && ccw(u.get(i - 2), u.get(i - 1), points[x]) <= 0) {

        u.remove(i - 1);
        i = u.size();
      }
      u.add(points[x]);
    }
    u.remove(u.size() - 1);
    l.remove(l.size() - 1);
    u.addAll(l);
    for (int x = u.size() - 1; x >= 0; x--)
      System.out.println(u.get(x).x + " " + u.get(x).y);
  }

  // CCW > 0 counter clockwise
  static int ccw (Point p1, Point p2, Point p3) {
    return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
  }

  static class Point implements Comparable<Point> {
    int x, y;

    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo (Point o) {
      if (x == o.x)
        return y - o.y;
      return x - o.x;
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
