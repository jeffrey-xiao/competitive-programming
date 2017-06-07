package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WOC_29_F {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int GROUPS = 25;

  static Point[][] p = new Point[GROUPS][12];

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    //		Point target = new Point(readInt(), readInt());
    for (int a = -12; a <= 12; a++) {
      for (int b = -12; b <= 12; b++) {
        double dist = Math.sqrt(a * a + b * b);
        if (dist == (int)dist)
          continue;
        Point target = new Point(a, b);
        initPoints();

        boolean found = false;
        for (int i = 0; i < GROUPS; i++) {
          for (int j = 0; j < 12; j++) {
            if (isSimilar(target, p[i][j])) {
              p[i][j] = p[i][0];
              p[i][0] = new Point(target.x, target.y);
              found = true;
            }
          }
          if (found) {
            for (int j = 0; j < 12; j++) {
              for (int k = j + 1; k < 12; k++) {
                if (p[i][j].equals(p[i][k])) {
                  change(i, k);
                }
              }
            }
            for (int j = 1; j < 12; j++)
              out.printf("%d %d\n", p[i][j].x, p[i][j].y);
            assert isValid(i) : Arrays.toString(p[i]);
            break;
          }
        }
        assert found;
      }
    }
    out.close();
  }

  static boolean isValid (int i) {
    double dist = 0;
    for (int j = 0; j < 12; j++) {
      dist += Math.sqrt(p[i][j].x * p[i][j].x + p[i][j].y * p[i][j].y);
    }
    dist -= (int)dist;
    dist = Math.min(Math.abs(dist), Math.abs(1 - dist));
    if (dist > 1e-12)
      return false;
    for (int j = 0; j < 12; j++) {
      for (int k = j + 1; k < 12; k++)
        if (p[i][j].equals(p[i][k]))
          return false;
    }
    for (int j = 0; j < 12; j++) {
      dist = Math.sqrt(p[i][j].x * p[i][j].x + p[i][j].y * p[i][j].y);
      if (dist == (int)dist)
        return false;
    }
    return true;
  }

  static void change (int i, int j) {
    if (!exists(i, -p[i][j].x, p[i][j].y)) {
      p[i][j].x = -p[i][j].x;
      p[i][j].y = p[i][j].y;
      return;
    }
    if (!exists(i, p[i][j].x, -p[i][j].y)) {
      p[i][j].x = p[i][j].x;
      p[i][j].y = -p[i][j].y;
      return;
    }
    if (!exists(i, -p[i][j].x, -p[i][j].y)) {
      p[i][j].x = -p[i][j].x;
      p[i][j].y = -p[i][j].y;
      return;
    }
    if (!exists(i, p[i][j].y, p[i][j].x)) {
      p[i][j].x = p[i][j].y;
      p[i][j].y = p[i][j].x;
      return;
    }
    if (!exists(i, -p[i][j].y, p[i][j].x)) {
      p[i][j].x = -p[i][j].y;
      p[i][j].y = p[i][j].x;
      return;
    }
    if (!exists(i, p[i][j].y, -p[i][j].x)) {
      p[i][j].x = p[i][j].y;
      p[i][j].y = -p[i][j].x;
      return;
    }
    if (!exists(i, -p[i][j].y, -p[i][j].x)) {
      p[i][j].x = -p[i][j].y;
      p[i][j].y = -p[i][j].x;
      return;
    }
    assert false;
  }

  static boolean exists (int i, int x, int y) {
    for (int j = 0; j < 12; j++)
      if (p[i][j].x == x && p[i][j].y == y)
        return true;
    return false;
  }

  static void initPoints () {
    p[0][0] = new Point(1, 1);
    p[0][1] = new Point(1, 2);
    p[0][2] = new Point(1, 3);
    p[0][3] = new Point(1, 5);
    p[0][4] = new Point(2, 10);
    p[0][5] = new Point(2, 11);
    p[0][6] = new Point(6, 9);
    p[0][7] = new Point(3, 11);
    p[0][8] = new Point(4, 4);
    p[0][9] = new Point(5, 6);
    p[0][10] = new Point(8, 12);
    p[0][11] = new Point(5, 7);

    p[1][0] = new Point(7, 11);
    p[1][1] = new Point(1, 11);
    p[1][2] = new Point(2, 12);
    p[1][3] = new Point(4, 5);
    p[1][4] = new Point(3, 12);
    p[1][5] = new Point(3, 10);
    p[1][6] = new Point(6, 9);
    p[1][7] = new Point(7, 12);
    p[1][8] = new Point(1, 11);
    p[1][9] = new Point(6, 6);
    p[1][10] = new Point(4, 12);
    p[1][11] = new Point(4, 12);

    p[2][0] = new Point(2, 2);
    p[2][1] = new Point(1, 2);
    p[2][2] = new Point(1, 3);
    p[2][3] = new Point(1, 5);
    p[2][4] = new Point(2, 10);
    p[2][5] = new Point(2, 11);
    p[2][6] = new Point(6, 9);
    p[2][7] = new Point(3, 3);
    p[2][8] = new Point(3, 11);
    p[2][9] = new Point(5, 6);
    p[2][10] = new Point(8, 12);
    p[2][11] = new Point(5, 7);

    p[3][0] = new Point(1, 3);
    p[3][1] = new Point(1, 5);
    p[3][2] = new Point(2, 3);
    p[3][3] = new Point(2, 4);
    p[3][4] = new Point(2, 10);
    p[3][5] = new Point(3, 11);
    p[3][6] = new Point(4, 6);
    p[3][7] = new Point(4, 8);
    p[3][8] = new Point(5, 6);
    p[3][9] = new Point(8, 12);
    p[3][10] = new Point(5, 7);
    p[3][11] = new Point(5, 5);

    p[4][0] = new Point(2, 3);
    p[4][1] = new Point(1, 1);
    p[4][2] = new Point(1, 3);
    p[4][3] = new Point(1, 5);
    p[4][4] = new Point(2, 10);
    p[4][5] = new Point(3, 11);
    p[4][6] = new Point(6, 12);
    p[4][7] = new Point(4, 4);
    p[4][8] = new Point(4, 6);
    p[4][9] = new Point(5, 6);
    p[4][10] = new Point(8, 12);
    p[4][11] = new Point(5, 7);

    p[5][0] = new Point(2, 4);
    p[5][1] = new Point(1, 1);
    p[5][2] = new Point(1, 3);
    p[5][3] = new Point(1, 5);
    p[5][4] = new Point(2, 10);
    p[5][5] = new Point(3, 11);
    p[5][6] = new Point(6, 9);
    p[5][7] = new Point(4, 4);
    p[5][8] = new Point(4, 8);
    p[5][9] = new Point(5, 6);
    p[5][10] = new Point(8, 12);
    p[5][11] = new Point(5, 7);

    p[6][0] = new Point(2, 5);
    p[6][1] = new Point(1, 8);
    p[6][2] = new Point(2, 11);
    p[6][3] = new Point(4, 4);
    p[6][4] = new Point(4, 10);
    p[6][5] = new Point(5, 7);
    p[6][6] = new Point(6, 10);
    p[6][7] = new Point(-1, 8);
    p[6][8] = new Point(2, 12);
    p[6][9] = new Point(3, 8);
    p[6][10] = new Point(8, 12);
    p[6][11] = new Point(3, 9);

    p[7][0] = new Point(3, 5);
    p[7][1] = new Point(1, 1);
    p[7][2] = new Point(1, 3);
    p[7][3] = new Point(4, 8);
    p[7][4] = new Point(4, 10);
    p[7][5] = new Point(7, 12);
    p[7][6] = new Point(8, 12);
    p[7][7] = new Point(1, 9);
    p[7][8] = new Point(1, 11);
    p[7][9] = new Point(3, 10);
    p[7][10] = new Point(10, 12);
    p[7][11] = new Point(3, 11);

    p[8][0] = new Point(1, 6);
    p[8][1] = new Point(1, 5);
    p[8][2] = new Point(1, 9);
    p[8][3] = new Point(2, 4);
    p[8][4] = new Point(2, 7);
    p[8][5] = new Point(4, 9);
    p[8][6] = new Point(9, 10);
    p[8][7] = new Point(3, 12);
    p[8][8] = new Point(-4, 9);
    p[8][9] = new Point(5, 7);
    p[8][10] = new Point(-9, 10);
    p[8][11] = new Point(5, 8);

    p[9][0] = new Point(2, 6);
    p[9][1] = new Point(1, 5);
    p[9][2] = new Point(1, 9);
    p[9][3] = new Point(2, 3);
    p[9][4] = new Point(2, 9);
    p[9][5] = new Point(4, 11);
    p[9][6] = new Point(5, 7);
    p[9][7] = new Point(6, 11);
    p[9][8] = new Point(7, 8);
    p[9][9] = new Point(7, 9);
    p[9][10] = new Point(10, 12);
    p[9][11] = new Point(7, 10);

    p[10][0] = new Point(3, 6);
    p[10][1] = new Point(1, 1);
    p[10][2] = new Point(1, 3);
    p[10][3] = new Point(1, 5);
    p[10][4] = new Point(2, 10);
    p[10][5] = new Point(-3, 6);
    p[10][6] = new Point(6, 9);
    p[10][7] = new Point(3, 11);
    p[10][8] = new Point(4, 4);
    p[10][9] = new Point(5, 6);
    p[10][10] = new Point(8, 12);
    p[10][11] = new Point(5, 7);

    p[11][0] = new Point(1, 7);
    p[11][1] = new Point(1, 2);
    p[11][2] = new Point(1, 3);
    p[11][3] = new Point(1, 5);
    p[11][4] = new Point(2, 3);
    p[11][5] = new Point(2, 10);
    p[11][6] = new Point(2, 11);
    p[11][7] = new Point(3, 11);
    p[11][8] = new Point(4, 6);
    p[11][9] = new Point(5, 6);
    p[11][10] = new Point(8, 12);
    p[11][11] = new Point(5, 7);

    p[12][0] = new Point(3, 7);
    p[12][1] = new Point(5, 8);
    p[12][2] = new Point(5, 9);
    p[12][3] = new Point(5, 11);
    p[12][4] = new Point(6, 9);
    p[12][5] = new Point(7, 10);
    p[12][6] = new Point(10, 11);
    p[12][7] = new Point(3, 11);
    p[12][8] = new Point(5, 6);
    p[12][9] = new Point(5, 7);
    p[12][10] = new Point(-5, 8);
    p[12][11] = new Point(5, -8);

    p[13][0] = new Point(4, 7);
    p[13][1] = new Point(1, 8);
    p[13][2] = new Point(2, 5);
    p[13][3] = new Point(4, 4);
    p[13][4] = new Point(4, 10);
    p[13][5] = new Point(5, 7);
    p[13][6] = new Point(6, 10);
    p[13][7] = new Point(2, 11);
    p[13][8] = new Point(2, 12);
    p[13][9] = new Point(3, 8);
    p[13][10] = new Point(8, 12);
    p[13][11] = new Point(3, 9);

    p[14][0] = new Point(6, 7);
    p[14][1] = new Point(1, 2);
    p[14][2] = new Point(1, 9);
    p[14][3] = new Point(1, 10);
    p[14][4] = new Point(3, 9);
    p[14][5] = new Point(9, 11);
    p[14][6] = new Point(11, 12);
    p[14][7] = new Point(4, 9);
    p[14][8] = new Point(6, 6);
    p[14][9] = new Point(8, 9);
    p[14][10] = new Point(-11, 12);
    p[14][11] = new Point(8, 10);

    p[15][0] = new Point(9, 9);
    p[15][1] = new Point(1, 6);
    p[15][2] = new Point(3, 5);
    p[15][3] = new Point(4, 9);
    p[15][4] = new Point(5, 8);
    p[15][5] = new Point(6, 10);
    p[15][6] = new Point(9, 10);
    p[15][7] = new Point(5, 9);
    p[15][8] = new Point(-6, 10);
    p[15][9] = new Point(7, 8);
    p[15][10] = new Point(12, 12);
    p[15][11] = new Point(7, 9);

    p[16][0] = new Point(10, 10);
    p[16][1] = new Point(1, 2);
    p[16][2] = new Point(2, 10);
    p[16][3] = new Point(4, 9);
    p[16][4] = new Point(6, 12);
    p[16][5] = new Point(7, 8);
    p[16][6] = new Point(10, 11);
    p[16][7] = new Point(1, 10);
    p[16][8] = new Point(2, 5);
    p[16][9] = new Point(-2, 10);
    p[16][10] = new Point(-4, 9);
    p[16][11] = new Point(2, 11);

    p[17][0] = new Point(11, 11);
    p[17][1] = new Point(1, 6);
    p[17][2] = new Point(3, 5);
    p[17][3] = new Point(4, 9);
    p[17][4] = new Point(5, 8);
    p[17][5] = new Point(6, 10);
    p[17][6] = new Point(9, 10);
    p[17][7] = new Point(5, 9);
    p[17][8] = new Point(-6, 10);
    p[17][9] = new Point(7, 8);
    p[17][10] = new Point(10, 10);
    p[17][11] = new Point(7, 9);

    p[18][0] = new Point(1, 12);
    p[18][1] = new Point(1, 2);
    p[18][2] = new Point(1, 9);
    p[18][3] = new Point(1, 10);
    p[18][4] = new Point(8, 10);
    p[18][5] = new Point(9, 11);
    p[18][6] = new Point(11, 12);
    p[18][7] = new Point(3, 9);
    p[18][8] = new Point(4, 9);
    p[18][9] = new Point(6, 6);
    p[18][10] = new Point(-11, 12);
    p[18][11] = new Point(6, 7);

    p[19][0] = new Point(5, 10);
    p[19][1] = new Point(1, 1);
    p[19][2] = new Point(1, 2);
    p[19][3] = new Point(1, 3);
    p[19][4] = new Point(1, 5);
    p[19][5] = new Point(2, 10);
    p[19][6] = new Point(6, 9);
    p[19][7] = new Point(3, 11);
    p[19][8] = new Point(4, 4);
    p[19][9] = new Point(5, 6);
    p[19][10] = new Point(8, 12);
    p[19][11] = new Point(5, 7);

    p[20][0] = new Point(8, 11);
    p[20][1] = new Point(1, 8);
    p[20][2] = new Point(2, 11);
    p[20][3] = new Point(3, 8);
    p[20][4] = new Point(3, 10);
    p[20][5] = new Point(5, 10);
    p[20][6] = new Point(5, 11);
    p[20][7] = new Point(6, 10);
    p[20][8] = new Point(6, 12);
    p[20][9] = new Point(8, 10);
    p[20][10] = new Point(8, 12);
    p[20][11] = new Point(-8, 11);

    p[21][0] = new Point(1, 4);
    p[21][1] = new Point(1, 3);
    p[21][2] = new Point(1, 7);
    p[21][3] = new Point(1, 12);
    p[21][4] = new Point(2, 8);
    p[21][5] = new Point(4, 9);
    p[21][6] = new Point(4, 12);
    p[21][7] = new Point(8, 10);
    p[21][8] = new Point(-4, 12);
    p[21][9] = new Point(4, 11);
    p[21][10] = new Point(-4, 9);
    p[21][11] = new Point(4, -9);

    p[22][0] = new Point(7, 7);
    p[22][1] = new Point(1, 10);
    p[22][2] = new Point(2, 5);
    p[22][3] = new Point(2, 10);
    p[22][4] = new Point(3, 3);
    p[22][5] = new Point(4, 9);
    p[22][6] = new Point(6, 12);
    p[22][7] = new Point(10, 11);
    p[22][8] = new Point(7, 8);
    p[22][9] = new Point(-6, 12);
    p[22][10] = new Point(-4, 9);
    p[22][11] = new Point(-2, 10);

    p[23][0] = new Point(8, 8);
    p[23][1] = new Point(1, 3);
    p[23][2] = new Point(1, 8);
    p[23][3] = new Point(2, 3);
    p[23][4] = new Point(2, 8);
    p[23][5] = new Point(2, 10);
    p[23][6] = new Point(3, 5);
    p[23][7] = new Point(8, 10);
    p[23][8] = new Point(5, 11);
    p[23][9] = new Point(5, 9);
    p[23][10] = new Point(-2, 10);
    p[23][11] = new Point(2, -10);

    p[24][0] = new Point(2, 8);
    p[24][1] = new Point(1, 3);
    p[24][2] = new Point(1, 4);
    p[24][3] = new Point(1, 7);
    p[24][4] = new Point(1, 12);
    p[24][5] = new Point(4, 9);
    p[24][6] = new Point(4, 12);
    p[24][7] = new Point(8, 10);
    p[24][8] = new Point(-4, 12);
    p[24][9] = new Point(4, 11);
    p[24][10] = new Point(-4, 9);
    p[24][11] = new Point(4, -9);
  }

  static boolean isSimilar (Point p1, Point p2) {
    if (p1.x == p2.x && p1.y == p2.y)
      return true;
    if (p1.x == -p2.x && p1.y == p2.y)
      return true;
    if (p1.x == p2.x && p1.y == -p2.y)
      return true;
    if (p1.x == -p2.x && p1.y == -p2.y)
      return true;
    if (p1.x == p2.y && p1.y == p2.x)
      return true;
    if (p1.x == -p2.y && p1.y == p2.x)
      return true;
    if (p1.x == p2.y && p1.y == -p2.x)
      return true;
    if (p1.x == -p2.y && p1.y == -p2.x)
      return true;
    return false;
  }

  static class Point {
    int x, y;

    Point (int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof Point) {
        Point p = (Point)o;
        return p.x == x && p.y == y;
      }
      return false;
    }

    @Override
    public String toString () {
      return String.format("(%d, %d)", x, y);
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
