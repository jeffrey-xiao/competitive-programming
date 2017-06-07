// TLE on a few cases.

package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WOC_29_E {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751");
  static final BigDecimal DECIMAL_PI = PI.subtract(new BigDecimal("3"));
  static final int PRECISION = 50;
  static final long LIMIT = 5000000;

  static BigDecimal best;
  static long bestNum, bestDen;
  static long min, max;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    min = readLong();
    max = readLong();

    if (max - min > LIMIT) {
      best = new BigDecimal("100");
      bestNum = 1;
      bestDen = 1;

      // starting points
      Rational candidate = new Rational((long)Math.round(min * PI.doubleValue()) % min, min);

      if (DECIMAL_PI.subtract(candidate.toBigDecimal()).abs().compareTo(best) < 0) {
        best = DECIMAL_PI.subtract(candidate.toBigDecimal()).abs();
        bestNum = candidate.num;
        bestDen = candidate.den;
      }

      candidate = new Rational((long)Math.round(max * PI.doubleValue()) % max, max);

      if (DECIMAL_PI.subtract(candidate.toBigDecimal()).abs().compareTo(best) < 0) {
        best = DECIMAL_PI.subtract(candidate.toBigDecimal()).abs();
        bestNum = candidate.num;
        bestDen = candidate.den;
      }

      // computing Farey sequence
      State[] curr = {new State(new Rational(1, 2), new Rational(0, 1), new Rational(1, 1))};
      setBest(curr);
      while (curr.length > 0) {
        State[] next = prune(generateNextSequence(curr));
        setBest(curr);
        curr = next;
      }

      out.printf("%d/%d\n", bestNum + bestDen * 3, bestDen);
    } else {
      BigDecimal best = PI;
      long num = 0;
      long den = 1;
      for (long i = min; i <= max; i += 1) {
        long numerator = new BigDecimal(i).multiply(PI).setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue();
        BigDecimal candidate = PI.subtract(new BigDecimal(numerator).divide(new BigDecimal(i), PRECISION, BigDecimal.ROUND_HALF_EVEN)).abs();
        if (candidate.compareTo(best) < 0) {
          best = candidate;
          num = numerator;
          den = i;
        }
      }
      out.printf("%d/%d\n", num, den);
    }

    out.close();
  }

  static class State {
    Rational curr, prev, next;

    State (Rational curr, Rational prev, Rational next) {
      this.curr = curr;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString () {
      return String.format("(%s, %s, %s)", curr, prev, next);
    }
  }

  static void setBest (State[] curr) {
    for (int i = 0; i < curr.length; i++) {
      long num = curr[i].curr.num;
      long den = curr[i].curr.den;
      long factor = (long)Math.ceil(1.0 * min / den);
      num *= factor;
      den *= factor;
      if (den <= max) {
        if (new BigDecimal(Long.toString(num)).divide(new BigDecimal(Long.toString(den)), PRECISION, BigDecimal.ROUND_HALF_EVEN).subtract(DECIMAL_PI).abs().compareTo(best) < 0) {
          best = new BigDecimal(Long.toString(num)).divide(new BigDecimal(Long.toString(den)), PRECISION, BigDecimal.ROUND_HALF_EVEN).subtract(DECIMAL_PI).abs();
          bestNum = num;
          bestDen = den;
        }
      }
    }
  }

  static boolean equal (State[] a, State[] b) {
    if (a.length != b.length)
      return false;
    for (int i = 0; i < a.length; i++)
      if (!a[i].equals(b[i]))
        return false;
    return true;
  }

  static State[] generateNextSequence (State[] curr) {
    State[] ret = new State[curr.length * 2];
    for (int i = 0; i < curr.length; i++) {
      ret[i * 2] = new State(new Rational(curr[i].prev.num + curr[i].curr.num, curr[i].prev.den + curr[i].curr.den), curr[i].prev, curr[i].curr);
      ret[i * 2 + 1] = new State(new Rational(curr[i].next.num + curr[i].curr.num, curr[i].next.den + curr[i].curr.den), curr[i].curr, curr[i].next);
    }
    return ret;
  }

  static State[] prune (State[] curr) {
    ArrayList<State> ret = new ArrayList<State>();
    for (int i = 0; i < curr.length; i++) {
      boolean add = true;
      if (curr[i].curr.toBigDecimal().compareTo(DECIMAL_PI) < 0 && curr[i].next.toBigDecimal().compareTo(DECIMAL_PI) < 0) {
        if (DECIMAL_PI.subtract(curr[i].next.toBigDecimal()).abs().compareTo(best) >= 0) {
          add = false;
        }
      } else if (curr[i].curr.toBigDecimal().compareTo(DECIMAL_PI) > 0 && curr[i].prev.toBigDecimal().compareTo(DECIMAL_PI) > 0) {
        if (DECIMAL_PI.subtract(curr[i].prev.toBigDecimal()).abs().compareTo(best) >= 0) {
          add = false;
        }
      }

      if (add && curr[i].curr.den <= max) {
        ret.add(curr[i]);
      }
    }
    return ret.toArray(new State[ret.size()]);
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

  static class Rational implements Comparable<Rational> {
    final long num, den;

    public static final Rational ZERO = new Rational(0);
    public static final Rational ONE = new Rational(1);
    public static final Rational POS_INF = new Rational(1, 0);
    public static final Rational NEG_INF = new Rational(-1, 0);

    public Rational (long num) {
      this.num = num;
      this.den = 1;
    }

    public Rational (long num, long den) {
      this.num = num;
      this.den = den;
    }

    public BigDecimal toBigDecimal () {
      return new BigDecimal(num).divide(new BigDecimal(den), PRECISION, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public int compareTo (Rational r) {
      return this.toBigDecimal().compareTo(r.toBigDecimal());
    }

    @Override
    public String toString () {
      return String.format("%d/%d", num, den);
    }
  }
}
