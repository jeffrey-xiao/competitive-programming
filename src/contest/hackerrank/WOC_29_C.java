package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WOC_29_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int W, H;
  static Point circle;
  static int R;
  static Point[] square = new Point[4];

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    H = readInt();
    W = readInt();

    circle = new Point(readInt(), readInt());
    R = readInt();

    square[0] = new Point(readInt(), readInt());
    square[2] = new Point(readInt(), readInt());

    computeCorners();

    for (int j = 0; j < W; j++) {
      for (int i = 0; i < H; i++) {
        if (insideCircle(new Point(i, j)) || insideSquare(new Point(i, j))) {
          out.print("#");
        } else {
          out.print(".");
        }
      }
      out.println();
    }
    out.close();
  }

  static boolean insideCircle (Point p) {
    double dx = p.x - circle.x;
    double dy = p.y - circle.y;
    return dx * dx + dy * dy <= R * R;
  }

  static boolean insideSquare (Point p) {
    boolean[] type = new boolean[4];
    for (int i = 1; i <= 4; i++)
      type[i - 1] = ccw(square[i - 1], p, square[i % 4]);
    for (int i = 1; i < 4; i++)
      if (type[i] != type[i - 1])
        return false;
    return true;
  }

  static double cross (Point p1, Point p2) {
    return p1.x * p2.y - p1.y * p2.x;
  }

  static boolean ccw (Point p1, Point p2, Point p3) {
    return cross(new Point(p2.x - p1.x, p2.y - p1.y), new Point(p3.x - p1.x, p3.y - p1.y)) >= 0;
  }

  static void computeCorners () {
    double centerX = (square[0].x + square[2].x) / 2.0;
    double centerY = (square[0].y + square[2].y) / 2.0;

    double vx = square[0].x - centerX;
    double vy = square[0].y - centerY;
    double ux = vy;
    double uy = -vx;
    square[1] = new Point(centerX + ux, centerY + uy);
    square[3] = new Point(centerX - ux, centerY - uy);
  }

  static class Point {
    double x, y;

    Point (double x, double y) {
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
