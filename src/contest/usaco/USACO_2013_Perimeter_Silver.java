package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2013_Perimeter_Silver {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int count;
  static TreeSet<Point> points = new TreeSet<Point>();
  static HashSet<Point> visited = new HashSet<Point>();

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int sx = 0;
    int sy = 0;
    for (int z = 0; z < n; z++) {
      int x = readInt();
      int y = readInt();
      points.add(new Point(x, y));
    }
    Point temp = points.first();
    sx = temp.x - 1;
    sy = temp.y;

    Stack<Point> moves = new Stack<Point>();
    moves.push(new Point(sx, sy));
    while (!moves.isEmpty()) {
      Point curr = moves.pop();
      if (points.contains(curr)) {
        count++;
        continue;
      }
      if (isIso(curr))
        continue;
      if (visited.contains(curr))
        continue;
      visited.add(curr);

      moves.push(new Point(curr.x + 1, curr.y));
      moves.push(new Point(curr.x - 1, curr.y));
      moves.push(new Point(curr.x, curr.y + 1));
      moves.push(new Point(curr.x, curr.y - 1));
    }
    System.out.println(count);
  }

  static boolean isIso(Point p) {
    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {
        if (points.contains(new Point(p.x + x, p.y + y)))
          return false;
      }
    }
    return true;
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

  static class Point implements Comparable<Point> {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Point) {
        Point p = (Point)o;
        return this.x == p.x && this.y == p.y;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return x + y;
    }

    @Override
    public int compareTo(Point p) {
      if (x == p.x)
        return y - p.y;
      return x - p.x;
    }
  }
}