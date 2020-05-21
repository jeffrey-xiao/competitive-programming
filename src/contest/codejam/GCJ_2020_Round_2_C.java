package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_2_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static Point[] p;
  static HashMap<Point, HashSet<Integer>> occ;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();

      p = new Point[N];
      occ = new HashMap<>();

      for (int i = 0; i < N; i++) {
        p[i] = new Point(readInt(), readInt());
      }

      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          long dy = p[i].y - p[j].y;
          long dx = p[i].x - p[j].x;
          long gcd = gcd(Math.abs(dy), Math.abs(dx));
          dy /= gcd;
          dx /= gcd;

          if (dy < 0) {
            dy = -dy;
            dx = -dx;
          } else if (dy == 0 && dx < 0) {
            dx = -dx;
          }

          Point slope = new Point(dy, dx);
          if (!occ.containsKey(slope)) {
            occ.put(slope, new HashSet<>());
          }
          occ.get(slope).add(i);
          occ.get(slope).add(j);
        }
      }

      int ans = Math.min(N, 4);
      for (Map.Entry<Point, HashSet<Integer>> e : occ.entrySet()) {
        int grouped = e.getValue().size();
        int alone = N - grouped;
        ans = Math.max(ans, grouped + Math.min(alone, grouped % 2 == 0 ? 2 : 1));
      }

      out.printf("Case #%d: %d%n", t, ans);
    }

    out.close();
  }

  static class Point {
    long x, y;
    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Point) {
        Point p = (Point)o;
        return p.x == x && p.y == y;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return new Long(x).hashCode() * 31 + new Long(y).hashCode();
    }
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
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
}
