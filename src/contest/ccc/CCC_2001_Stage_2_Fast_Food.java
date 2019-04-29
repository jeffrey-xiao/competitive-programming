package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2001_Stage_2_Fast_Food {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = 0;
    while ((n = readInt()) != 0) {
      Point[] p = new Point[n];
      int[] count = new int[n];
      for (int x = 0; x < n; x++)
        p[x] = new Point(readInt(), readInt());
      for (double x = 0.01d; x < 10.0d; x += 0.1d) {
        for (double y = 0.01d; y < 10.0d; y += 0.1d) {
          double min = Integer.MAX_VALUE;
          int index = -1;
          for (int z = 0; z < n; z++) {
            double dist = p[z].distTo(x, y);
            if (dist < min) {
              min = dist;
              index = z;
            }
          }
          count[index]++;
        }
      }
      for (int x = 0; x < n; x++) {
        System.out.printf("Restaurant at %s serves %.1f%s of the population.%n", p[x], count[x] / 100.0d, "%");
      }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Point {
    double x, y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }

    double distTo(double x, double y) {
      x = this.x - x;
      y = this.y - y;
      return x * x + y * y;
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + ")";
    }
  }
}
