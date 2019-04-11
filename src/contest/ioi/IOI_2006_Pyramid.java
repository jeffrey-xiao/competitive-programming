// Suboptimal solution that scores 97/100

package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2006_Pyramid {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static Point[][][] p; // min c*d rectangle in (a-2)*(b-2) grid
  static int[][] g;
  static int n, m, a, b, c, d;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    m = readInt();
    n = readInt();
    b = readInt();
    a = readInt();
    d = readInt();
    c = readInt();
    g = new int[n + 1][m + 1];
    p = new Point[n + 1][m + 1][4];
    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= m; j++)
        g[i][j] = readInt() + g[i - 1][j] + g[i][j - 1] - g[i - 1][j - 1];
    for (int i = 0; i <= n; i++)
      for (int j = 0; j <= m; j++) {
        if (i < c || j < d)
          p[i][j][0] = p[i][j][1] = p[i][j][2] = p[i][j][3] = new Point(1 << 30, -1, -1);
        else {
          p[i][j][0] = p[i][j][1] = p[i][j][2] = p[i][j][3] = new Point(g[i][j] - g[i - c][j] - g[i][j - d] + g[i - c][j - d], i, j);
          update(i - 1, j, i, j, 0);
          update(i - 1, j, i, j, 1);
          update(i - 1, j, i, j, 2);
          update(i - 1, j, i, j, 3);
          update(i, j - 1, i, j, 0);
          update(i, j - 1, i, j, 1);
          update(i, j - 1, i, j, 2);
          update(i, j - 1, i, j, 3);
        }
      }
    long ans = -1;
    int minA = -1, minB = -1, minC = -1, minD = -1;
    for (int i = a; i <= n; i++) {
      for (int j = b; j <= m; j++) {
        int val = g[i][j] - g[i - a][j] - g[i][j - b] + g[i - a][j - b] - p[i - 1][j - 1][0].min;
        if (val > ans) {
          ans = val;
          minA = i - a + 1;
          minB = j - b + 1;
          minC = p[i - 1][j - 1][0].x - c + 1;
          minD = p[i - 1][j - 1][0].y - d + 1;
        }
      }
    }
    out.printf("%d %d\n%d %d\n", minB, minA, minD, minC);
    out.close();
  }

  static void update(int i, int j, int k, int l, int z) {
    if (p[i][j][z].x >= k - (a - 2) + c && p[i][j][z].y >= l - (b - 2) + d) {
      if (p[i][j][z].min < p[k][l][0].min) {
        p[k][l][0] = p[i][j][z];
      }
      if (p[i][j][z].min < p[k][l][1].min && p[i][j][z].x >= k - (a - 2) + c + 1) {
        p[k][l][1] = p[i][j][z];
      }
      if (p[i][j][z].min < p[k][l][2].min && p[i][j][z].y >= l - (b - 2) + d + 1) {
        p[k][l][2] = p[i][j][z];
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Point {
    int min, x, y;

    Point(int min, int x, int y) {
      this.min = min;
      this.x = x;
      this.y = y;
    }
  }
}
