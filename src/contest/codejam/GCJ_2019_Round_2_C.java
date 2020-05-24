package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_2_C {

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

      int[] C = new int[N];
      int[] J = new int[N];
      for (int i = 0; i < N; i++) {
        C[i] = readInt();
        J[i] = readInt();
      }

      Fraction lo = new Fraction(1, Integer.MAX_VALUE);
      Fraction hi = new Fraction(Integer.MAX_VALUE, 1);
      for (int i = 0; i < N - 1; i++) {
        int dc = C[i] - C[i + 1];
        int dj = J[i + 1] - J[i];
        if (dc <= 0 && dj >= 0) {
          continue;
        }
        Fraction curr = new Fraction(dc, dj);
        if (dj < 0 && curr.compareTo(hi) < 0) {
          hi = curr;
        } else if (dc > 0 && curr.compareTo(lo) > 0) {
          lo = curr;
        }
      }
      if (lo.compareTo(hi) >= 0) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue;
      }

      Fraction ans = solve(lo, hi);
      out.printf("Case #%d: %d %d%n", t, ans.d, ans.n);
    }

    out.close();
  }

  static Fraction solve(Fraction lo, Fraction hi) {
    Fraction best = new Fraction(lo.n + hi.n, lo.d + hi.d);
    ArrayList<ArrayList<Long>> l = Fraction.toContinued(lo);
    ArrayList<ArrayList<Long>> r = Fraction.toContinued(hi);
    for (ArrayList<Long> c1 : l) {
      for (ArrayList<Long> c2 : r) {
        ArrayList<Long> c = new ArrayList<Long>();
        for (int i = 0;; i++) {
          if (c1.get(i) == c2.get(i)) {
            c.add(c1.get(i));
          } else {
            c.add(Math.min(c1.get(i), c2.get(i)) + 1);
            break;
          }
        }
        Fraction curr = Fraction.fromContinued(c);
        if (lo.compareTo(curr) < 0 && curr.compareTo(hi) < 0) {
          if (curr.n <= best.n && curr.d <= best.d) {
            best = curr;
          }
        }
      }
    }
    return best;
  }

  static class Fraction implements Comparable<Fraction> {
    long n, d;
    Fraction(long n, long d) {
      long gcd = gcd(Math.abs(n), Math.abs(d));
      this.n = n / gcd;
      this.d = d / gcd;
      if (this.d < 0) {
        this.n = -this.n;
        this.d = -this.d;
      }
      assert this.d >= 0;
    }

    static ArrayList<ArrayList<Long>> toContinued(Fraction f) {
      long n = f.n;
      long d = f.d;
      ArrayList<Long> c1 = new ArrayList<>();
      while (n != 0 && d != 0) {
        c1.add(n / d);
        n %= d;
        long temp = n;
        n = d;
        d = temp;
      }
      ArrayList<Long> c2 = new ArrayList<>();
      c2.addAll(c1);
      if (!c2.isEmpty()) {
        int lastIndex = c2.size() - 1;
        c2.set(lastIndex, c2.get(lastIndex) - 1);
      }
      c2.add(1L);
      c1.add((long)Integer.MAX_VALUE);
      c2.add((long)Integer.MAX_VALUE);
      ArrayList<ArrayList<Long>> ret = new ArrayList<ArrayList<Long>>();
      ret.add(c1);
      ret.add(c2);
      return ret;
    }

    static Fraction fromContinued(ArrayList<Long> c) {
      long n = 1;
      long d = 0;
      for (int i = c.size() - 1; i >= 0; i--) {
        long temp = n;
        n = d;
        d = temp;
        if (c.get(i) == Integer.MAX_VALUE) {
          continue;
        }
        n += c.get(i) * d;
      }
      return new Fraction(n, d);
    }

    @Override
    public int compareTo(Fraction f) {
      return new Long(n * f.d).compareTo(f.n * d);
    }
  }

  static long gcd(long a, long b) {
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
