package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class COCI_2007_PRAVOKUTNI {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static Point[] p;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    p = new Point[N];

    for (int i = 0; i < N; i++)
      p[i] = new Point(readInt(), readInt());

    int ans = 0;

    for (int i = 0; i < N; i++) {
      HashMap<Fraction, Integer> cnt = new HashMap<Fraction, Integer>();

      for (int j = 0; j < N; j++) {
        if (i == j)
          continue;
        int dx = p[i].x - p[j].x;
        int dy = p[i].y - p[j].y;

        Fraction f = new Fraction(dy, dx);
        if (!cnt.containsKey(f))
          cnt.put(f, 0);
        cnt.put(f, cnt.get(f) + 1);

        if (cnt.get(new Fraction(-dx, dy)) != null)
          ans += cnt.get(new Fraction(-dx, dy));
      }
    }

    out.println(ans);
    out.close();
  }

  static int gcd(int a, int b) {
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

  static class Fraction {
    int num, den;

    Fraction(int num, int den) {
      if (num == 0) {
        den = 1;
      } else if (den == 0) {
        num = 0;
      } else {
        int gcd = gcd(num, den);
        num /= gcd;
        den /= gcd;
        if (den < 0) {
          den *= -1;
          num *= -1;
        }
      }

      this.num = num;
      this.den = den;
    }

    @Override
    public int hashCode() {
      return num ^ den;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Fraction) {
        Fraction f = (Fraction)o;
        return num == f.num && den == f.den;
      }
      return false;
    }
  }

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
