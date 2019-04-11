package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ACM_Mount_Allison_2017_H {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, Q;
  static int[] temp0, temp100;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    Q = readInt();

    temp0 = new int[N];
    temp100 = new int[N];

    for (int i = 0; i < N; i++) {
      temp0[i] = readInt();
      temp100[i] = readInt();
    }

    for (int i = 0; i < Q; i++) {
      int x = readInt() - 1;
      int y = readInt() - 1;
      int temp = readInt();
      out.println(celsiusToX(y, xToCelsius(x, new Rational(temp))));
    }

    out.close();
  }

  static Rational celsiusToX(int i, Rational temp) {
    Rational slope = new Rational(temp100[i] - temp0[i], 100);
    return new Rational(temp0[i]).add(slope.multply(temp));
  }

  static Rational xToCelsius(int i, Rational temp) {
    Rational slope = new Rational(temp100[i] - temp0[i], 100);
    return temp.subtract(new Rational(temp0[i])).divide(slope);
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

  static class Rational implements Comparable<Rational> {
    public static final Rational ZERO = new Rational(0);
    public static final Rational ONE = new Rational(1);
    public static final Rational POS_INF = new Rational(1, 0);
    public static final Rational NEG_INF = new Rational(-1, 0);
    final BigInteger num, den;

    public Rational(long num) {
      this.num = BigInteger.valueOf(num);
      this.den = BigInteger.ONE;
    }

    public Rational(long num, long den) {
      this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public Rational(BigInteger num, BigInteger den) {
      if (!den.abs().equals(BigInteger.ONE)) {
        BigInteger gcd = num.gcd(den);
        if (!gcd.equals(BigInteger.ZERO) && !gcd.equals(BigInteger.ONE)) {
          num = num.divide(gcd);
          den = den.divide(gcd);
        }
      }
      if (den.signum() < 0) {
        num = num.negate();
        den = den.negate();
      }
      this.num = num;
      this.den = den;
    }

    public Rational add(Rational r) {
      return new Rational(num.multiply(r.den).add(r.num.multiply(den)), den.multiply(r.den));
    }

    public Rational subtract(Rational r) {
      return new Rational(num.multiply(r.den).subtract(r.num.multiply(den)), den.multiply(r.den));
    }

    public Rational multply(Rational r) {
      return new Rational(num.multiply(r.num), den.multiply(r.den));
    }

    public Rational divide(Rational r) {
      return new Rational(num.multiply(r.den), den.multiply(r.num));
    }

    public Rational negate() {
      return new Rational(num.negate(), den);
    }

    public Rational inverse() {
      return new Rational(den, num);
    }

    public Rational abs() {
      return new Rational(num.abs(), den);
    }

    public int signum() {
      return num.signum();
    }

    @Override
    public int compareTo(Rational r) {
      return num.multiply(r.den).compareTo(r.num.multiply(den));
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Rational) {
        Rational r = (Rational)o;
        return num.equals(r.num) && den.equals(r.den);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return num.hashCode() * 31 + den.hashCode();
    }

    @Override
    public String toString() {
      return String.format("%s/%s", num.toString(), den.toString());
    }
  }
}
