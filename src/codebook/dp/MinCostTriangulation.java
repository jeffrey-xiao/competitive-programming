/*
 * Dynamic programming algorithm that finds the minimum cost of the triangulation of n points. The cost of a triangulation is the length of the edges that form the triangulations.
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MinCostTriangulation {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    Point[] p = new Point[n];
    for (int i = 0; i < n; i++)
      p[i] = new Point(readDouble(), readDouble());

    out.println(minCost(p, n));
    out.close();
  }

  private static double minCost(Point[] points, int n) {
    // if there are fewer than three points then the cost is 0
    if (n < 3)
      return 0;

    // dp table
    double[][] table = new double[n][n];

    // Fill table using optimal substructure
    // start when the gap is 0 and increment it
    for (int gap = 0; gap < n; gap++) {
      for (int i = 0, j = gap; j < n; i++, j++) {
        if (j < i + 2)
          table[i][j] = 0.0;
        else {
          table[i][j] = Integer.MAX_VALUE;
          for (int k = i + 1; k < j; k++) {
            double val = table[i][k] + table[k][j] + cost(points, i, j, k);
            if (table[i][j] > val)
              table[i][j] = val;
          }
        }
      }
    }
    return table[0][n - 1];
  }

  private static double cost(Point[] points, int i, int j, int k) {
    return dist(points[i], points[j]) + dist(points[j], points[k]) + dist(points[k], points[i]);
  }

  private static double dist(Point p1, Point p2) {
    double dx = p1.x - p2.x;
    double dy = p1.y - p2.y;
    return Math.sqrt(dx * dx + dy * dy);
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

  private static class Point {
    double x, y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
}
