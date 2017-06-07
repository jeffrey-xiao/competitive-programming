package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IOI_2002_Bus_Terminals {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, ans = 1 << 30, stackIndex;
  static Point curr;
  static Point[] points, sorted;
  static int[] hubA;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    points = new Point[N];
    sorted = new Point[N];
    hubA = new int[N];

    for (int i = 0; i < N; i++) {
      int x = readInt();
      int y = readInt();
      points[i] = new Point(x, y, i);
      sorted[i] = new Point(x, y, i);
    }
    // case 1: all connected to one hub
    for (int i = 0; i < N; i++) {
      int max1 = 0;
      int max2 = 0;

      for (int j = 0; j < N; j++) {
        if (i == j)
          continue;
        int currDist = dist(points[i], points[j]);
        if (currDist > max1) {
          max2 = max1;
          max1 = currDist;
        } else if (currDist > max2) {
          max2 = currDist;
        }
      }

      ans = Math.min(ans, max1 + max2);
    }

    // case 2: connected to two hubs
    for (int i = 0; i < N; i++) {
      curr = points[i];
      Arrays.sort(sorted);
      for (int j = i + 1; j < N; j++) {

        stackIndex = 0;
        int aMax1 = 0, aMax2 = 0, bMax1 = 0, bMax2 = 0;
        int hubDist = dist(points[i], points[j]);

        for (int k = 0; k < N; k++) {
          if (sorted[k].index == i || sorted[k].index == j)
            continue;

          int dist1 = dist(points[i], sorted[k]);
          int dist2 = dist(points[j], sorted[k]) + hubDist;

          // could be connected to hub A or hub B
          if (dist1 < dist2) {
            hubA[stackIndex++] = sorted[k].index;
            if (dist1 > aMax1) {
              aMax2 = aMax1;
              aMax1 = dist1;
            } else if (dist1 > aMax2) {
              aMax2 = dist1;
            }
          }

          // has to be connected to hub B
          else {
            if (dist2 - hubDist > bMax1) {
              bMax2 = bMax1;
              bMax1 = dist2 - hubDist;
            } else if (dist2 - hubDist > bMax2) {
              bMax2 = dist2 - hubDist;
            }
          }

        }

        int currMaxDist = Math.max(aMax1 + bMax1 + hubDist, Math.max(aMax1 + aMax2, bMax1 + bMax2));
        ans = Math.min(ans, currMaxDist);

        // moving connections from hub A to hub B
        // stack will be non-increasing
        while (stackIndex > 0) {
          int curr = hubA[--stackIndex];

          int distB = dist(points[curr], points[j]);
          if (distB > bMax1) {
            bMax2 = bMax1;
            bMax1 = distB;
          } else if (distB > bMax2) {
            bMax2 = distB;
          }

          aMax1 = aMax2;
          aMax2 = stackIndex >= 2 ? dist(points[hubA[stackIndex - 2]], points[i]) : 0;

          currMaxDist = Math.max(aMax1 + bMax1 + hubDist, Math.max(aMax1 + aMax2, bMax1 + bMax2));
          ans = Math.min(ans, currMaxDist);
        }
      }
    }

    out.println(ans);
    out.close();
  }

  static class Point implements Comparable<Point> {
    int x, y, index;

    Point (int x, int y, int index) {
      this.x = x;
      this.y = y;
      this.index = index;
    }

    @Override
    public int compareTo (Point p) {
      int d1 = dist(this, curr);
      int d2 = dist(p, curr);
      return d1 - d2;
    }
  }

  static int dist (Point p1, Point p2) {
    int dx = Math.abs(p1.x - p2.x);
    int dy = Math.abs(p1.y - p2.y);
    return dx + dy;
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
