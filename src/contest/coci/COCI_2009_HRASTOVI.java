package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2009_HRASTOVI {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int n, m;
  static Point[] p;

  public static void main (String[] args) throws IOException {
    n = readInt();
    p = new Point[n];
    for (int i = 0; i < n; i++)
      p[i] = new Point(readInt(), readInt());
    m = readInt();
    Rect[] r = new Rect[m];
    for (int i = 0; i < m; i++)
      r[i] = new Rect(readInt(), readInt(), readInt(), readInt());
    int[] sum = new int[m];
    Arrays.sort(p, new Comparator<Point>() {
      @Override
      public int compare (Point o1, Point o2) {
        if (o1.x == o2.x)
          return o1.y - o2.y;
        return o1.x - o2.x;
      }
    });
    for (int i = 0; i < m; i++) {
      sum[i] += search(true, r[i].x2, r[i].y2, false) - search(true, r[i].x2, r[i].y1, true);
      sum[i] += search(true, r[i].x1, r[i].y2, false) - search(true, r[i].x1, r[i].y1, true);
    }
    Arrays.sort(p, new Comparator<Point>() {
      @Override
      public int compare (Point o1, Point o2) {
        if (o1.y == o2.y)
          return o1.x - o2.x;
        return o1.y - o2.y;
      }
    });
    for (int i = 0; i < m; i++) {
      sum[i] += search(false, r[i].y2, r[i].x2, false) - search(false, r[i].y2, r[i].x1, true);
      sum[i] += search(false, r[i].y1, r[i].x2, false) - search(false, r[i].y1, r[i].x1, true);
      System.out.println(sum[i]);
    }
  }

  static int search (boolean isX, Integer v, Integer u, boolean second) {
    int lo = 0;
    int hi = n - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp1 = isX ? v.compareTo(p[mid].x) : v.compareTo(p[mid].y);
      int cmp2 = !isX ? u.compareTo(p[mid].x) : u.compareTo(p[mid].y);
      if (cmp1 > 0) {
        lo = mid + 1;
      } else if (cmp1 == 0) {
        if ((!isX && !second) || (isX && second)) {
          if (cmp2 > 0) {
            lo = mid + 1;
          } else {
            hi = mid - 1;
          }
        } else {
          if (cmp2 >= 0) {
            lo = mid + 1;
          } else {
            hi = mid - 1;
          }
        }

      } else if (cmp1 < 0) {
        hi = mid - 1;
      }
    }
    return lo;
  }

  static class Rect {
    int x1, y1, x2, y2;

    Rect (int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }
  }

  static class Point {
    int x, y;

    Point (int x, int y) {
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
