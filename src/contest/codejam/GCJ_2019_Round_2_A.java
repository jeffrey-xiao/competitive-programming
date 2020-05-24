package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_2_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      int N = readInt();
      HashSet<Fraction> fractions = new HashSet<Fraction>();
      int[] a = new int[N], b = new int[N];
      for (int i = 0; i < N; i++) {
        a[i] = readInt();
        b[i] = readInt();
      }
      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          if (a[i] >= a[j] && b[i] >= b[j] || a[i] <= a[j] && b[i] <= b[j]) {
            continue;
          }
          fractions.add(new Fraction(Math.abs(a[i] - a[j]), Math.abs(b[i] - b[j])));
        }
      }
      out.printf("Case #%d: %d%n", t, fractions.size() + 1);
    }

    out.close();
  }

  static class Fraction {
    int a, b;
    Fraction (int a, int b) {
      if (a == 0 && b == 0) {
        this.a = 0;
        this.b = 0;
      } else if (b == 0) {
        this.a = 1;
      } else if (a == 0) {
        this.b = 1;
      } else {
        int gcd = gcd(a, b);
        this.a = a / gcd;
        this.b = b / gcd;
      }
    }

    @Override
    public int hashCode() {
      return a * 31 + b;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Fraction) {
        Fraction f = (Fraction)o;
        return a == f.a && b == f.b;
      }
      return false;
    }
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
}
