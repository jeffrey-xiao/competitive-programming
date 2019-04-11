package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DWITE_2006_Points_On_A_line {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    ArrayList<Point> p = new ArrayList<Point>();
    for (int z = 0; z < n; z++) {
      int x = readInt();
      int y = readInt();
      p.add(new Point(x, y));
    }
    for (int z = 0; z < 5; z++) {
      Point p1 = new Point(readInt(), readInt());
      Point p2 = new Point(readInt(), readInt());
      int count = 0;
      for (int x = 0; x < p.size(); x++) {
        Point curr = p.get(x);
        if (getSlope(p1, curr) == getSlope(curr, p2)) {
          count++;
        }
      }
      System.out.println(count);
    }
  }

  static double getSlope(Point p1, Point p2) {
    double m = ((double)(p1.y - p2.y)) / ((double)(p1.x - p2.x));
    if (m == Double.NEGATIVE_INFINITY)
      return Double.POSITIVE_INFINITY;
    return m;
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

  static class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }
}
