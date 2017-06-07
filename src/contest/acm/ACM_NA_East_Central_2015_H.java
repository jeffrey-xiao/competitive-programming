package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ACM_NA_East_Central_2015_H {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static double w, l;
  static double r, h;
  static Vector p1, p2, p3, left, right;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    w = readDouble();
    l = readDouble();
    r = readDouble();
    left = new Vector(0, l);
    right = new Vector(w, l);
    p1 = new Vector(readDouble(), readDouble());
    p2 = new Vector(readDouble(), readDouble());
    p3 = new Vector(readDouble(), readDouble());
    h = readDouble();

    Vector dir = new Vector(p3, right);
    dir = dir.normalize();
    dir = dir.multiply(r * 2);

    Vector inter3 = new Vector(p3);
    inter3 = inter3.subtract(dir);

    dir = new Vector(p1, inter3);
    dir = dir.normalize();
    dir = dir.multiply(r * 2);

    Vector normal = new Vector(dir);
    normal = normal.normalize();
    normal = normal.multiply(-1);

    Vector inter1 = new Vector(p1);
    inter1 = inter1.subtract(dir);

    dir = new Vector(p2, left);
    dir = dir.normalize();
    dir = dir.multiply(r * 2);

    Vector inter2 = new Vector(p2);
    inter2 = inter2.subtract(dir);

    Vector cueDir = new Vector(inter2, inter1);
    cueDir = cueDir.subtract(normal.multiply(2 * cueDir.dot(normal)));

    double t = (h - inter1.y) / cueDir.y;
    double x = inter1.x + t * cueDir.x;

    Vector start = new Vector(x, h);

    if (new Vector(start, inter1).intersect(p2.subtract(start), r))
      out.println("impossible");
    else if (new Vector(start, inter1).intersect(p3.subtract(start), r))
      out.println("impossible");
    else if (inter3.x < inter1.x || inter2.x > inter1.x)
      out.println("impossible");
    else if (x + r > w || x - r < 0 || Math.atan2(cueDir.y, cueDir.x) * 180 / Math.PI + 180 > 180)
      out.println("impossible");
    else
      out.printf("%.2f %.2f\n", x, Math.atan2(cueDir.y, cueDir.x) * 180 / Math.PI + 180);

    out.close();
  }

  static class Vector {
    double x, y;

    Vector (double x, double y) {
      this.x = x;
      this.y = y;
    }

    Vector (Vector v) {
      this.x = v.x;
      this.y = v.y;
    }

    Vector (Vector v1, Vector v2) {
      x = v2.x - v1.x;
      y = v2.y - v1.y;
    }

    boolean intersect (Vector v1, double r) {
      return Math.abs(v1.y - y - y / x * (v1.x - x)) / Math.sqrt(1 + y * y / x / x) / 2 < r && this.dist() > v1.dist();
    }

    double dot (Vector v) {
      return x * v.x + y * v.y;
    }

    Vector multiply (double s) {
      Vector ret = new Vector(x * s, y * s);
      return ret;
    }

    Vector subtract (Vector v) {
      Vector ret = new Vector(x - v.x, y - v.y);
      return ret;
    }

    Vector normalize () {
      double d = dist();
      return new Vector(x / d, y / d);
    }

    Vector rotate (double angle) {
      angle = angle / 180 * Math.PI;
      return new Vector(Math.cos(angle) * x - Math.sin(angle) * y, Math.sin(angle) * x + Math.cos(angle) * y);
    }

    double dist () {
      return Math.sqrt(x * x + y * y);
    }

    public String toString () {
      return String.format("(%f, %f)", x, y);
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