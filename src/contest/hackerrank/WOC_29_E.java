package contest.hackerrank;

import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.math.BigDecimal;

public class WOC_29_E {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751");
	static final int PRECISION = 40;
	static final BigDecimal DECIMAL_PI = PI.subtract(new BigDecimal("3"));
	static final Random rng = new Random();
	static final Rational THREE = new Rational(3, 1);
	static final long LIMIT = 5000000;
	
	static BigDecimal best;
	static long bestNum, bestDen;
	static BigInteger min, max;


	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

//		min = new BigInteger(next());
//		max = new BigInteger(next());

		for (int q = 0; q < 1000; q++) {
			long a = Math.max(1, Math.abs(rng.nextInt()) - LIMIT);
			long b = a + LIMIT;
			min = new BigInteger(Long.toString(a));
			max = new BigInteger(Long.toString(b));
			
//		if (max.subtract(min).compareTo(new BigInteger(Long.toString(LIMIT))) > 0) {
		best = new BigDecimal("100");
		bestNum = 1;
		bestDen = 1;

		// starting points
		sample();

		// computing Farey sequence
		State[] curr = {new State(new Rational(1, 2), new Rational(0, 1), new Rational(1,1))};
		setBest(curr);
		while (curr.length != 0) {
			State[] next = prune(generateNextSequence(curr));
			setBest(curr);
			curr = next;
		}

//		out.printf("%d/%d\n", bestNum + bestDen * 3, bestDen);
//		} else {
			BigDecimal best = PI;
			long num = 0;
			long den = 1;
			for (BigInteger i = min; i.compareTo(max) <= 0; i = i.add(BigInteger.ONE)) {
				long numerator = new BigDecimal(i).multiply(PI).setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue();
				BigDecimal candidate2 = PI.subtract(new BigDecimal(numerator).divide(new BigDecimal(i), PRECISION, BigDecimal.ROUND_HALF_EVEN)).abs();
				if (candidate2.compareTo(best) < 0) {
					best = candidate2;
					num = numerator;
					den = i.longValue();
				}
			}
//			out.printf("%d/%d\n", num, den);
//		}
			assert num == bestNum + bestDen * 3 : min + " " + max;
			assert den == bestDen : min + " " + max;
			System.out.println("DONE " + q);
		}
		out.close();
	}

	static void sample () {
		long gap = Math.max(1, (max.longValue() - min.longValue()) / 100);
		for (long i = min.longValue(); i <= max.longValue(); i += gap) {
			Rational candidate = new Rational(new BigDecimal(Long.toString(i)).multiply(PI).setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue(), min.longValue()).subtract(THREE);

			if (DECIMAL_PI.subtract(candidate.toBigDecimal()).abs().compareTo(best) < 0) {
				best = DECIMAL_PI.subtract(candidate.toBigDecimal()).abs();
				bestNum = candidate.num.longValue();
				bestDen = candidate.den.longValue();
			}
		}
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
			long num = curr[i].curr.num.longValue();
			long den = curr[i].curr.den.longValue();
			long factor = (long)Math.ceil(min.doubleValue() / den);
			num *= factor;
			den *= factor;
			if (den <= max.longValue()) {
				if (new BigDecimal(Long.toString(num)).divide(new BigDecimal(Long.toString(den)), PRECISION, BigDecimal.ROUND_HALF_EVEN).subtract(DECIMAL_PI).abs().compareTo(best) < 0) {
					best = new BigDecimal(Long.toString(num)).divide(new BigDecimal(Long.toString(den)), PRECISION, BigDecimal.ROUND_HALF_EVEN).subtract(DECIMAL_PI).abs();
					bestNum = num;
					bestDen = den;
				}
			}
		}
	}
	
	static State[] generateNextSequence (State[] curr) {
		State[] ret = new State[curr.length * 2];
		for (int i = 0; i < curr.length; i++) {
			ret[i * 2] = new State(new Rational(curr[i].prev.num.add(curr[i].curr.num), curr[i].prev.den.add(curr[i].curr.den)), curr[i].prev, curr[i].curr);
			ret[i * 2 + 1] = new State(new Rational(curr[i].next.num.add(curr[i].curr.num), curr[i].next.den.add(curr[i].curr.den)), curr[i].curr, curr[i].next);
		}
		return ret;
	}

	static State[] prune (State[] curr) {
		ArrayList<State> ret = new ArrayList<State>();
		for (int i = 0; i < curr.length; i++) {
			boolean add = true;
			if (curr[i].curr.toBigDecimal().compareTo(DECIMAL_PI) < 0 && 
					curr[i].next.toBigDecimal().compareTo(DECIMAL_PI) < 0) {
				if (DECIMAL_PI.subtract(curr[i].next.toBigDecimal()).abs().compareTo(best) >= 0) {
					add = false;
				}
			}
			if (curr[i].curr.toBigDecimal().compareTo(DECIMAL_PI) > 0 && 
					curr[i].prev.toBigDecimal().compareTo(DECIMAL_PI) > 0) {
				if (DECIMAL_PI.subtract(curr[i].prev.toBigDecimal()).abs().compareTo(best) >= 0) {
					add = false;
				}
			}

			if (add && curr[i].curr.den.compareTo(max) <= 0) {
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
		final BigInteger num, den;

		public static final Rational ZERO = new Rational(0);
		public static final Rational ONE = new Rational(1);
		public static final Rational POS_INF = new Rational(1, 0);
		public static final Rational NEG_INF = new Rational(-1, 0);

		public Rational (long num) {
			this.num = BigInteger.valueOf(num);
			this.den = BigInteger.ONE;
		}

		public Rational (long num, long den) {
			this(BigInteger.valueOf(num), BigInteger.valueOf(den));
		}

		public Rational (String num, String den) {
			this(new BigInteger(num), new BigInteger(den));
		}

		public Rational (BigInteger num, BigInteger den) {
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

		public Rational add (Rational r) {
			return new Rational(num.multiply(r.den).add(r.num.multiply(den)), den.multiply(r.den));
		}

		public Rational subtract (Rational r) {
			return new Rational(num.multiply(r.den).subtract(r.num.multiply(den)), den.multiply(r.den));
		}

		public Rational multply (Rational r) {
			return new Rational(num.multiply(r.num), den.multiply(r.den));
		}

		public Rational divide (Rational r) {
			return new Rational(num.multiply(r.den), den.multiply(r.num));
		}

		public Rational negate () {
			return new Rational(num.negate(), den);
		}

		public Rational inverse () {
			return new Rational(den, num);
		}

		public Rational abs () {
			return new Rational(num.abs(), den);
		}

		public int signum () {
			return num.signum();
		}

		public double toDouble () {
			return num.doubleValue() / den.doubleValue();
		}

		public BigDecimal toBigDecimal () {
			return new BigDecimal(num).divide(new BigDecimal(den), PRECISION, BigDecimal.ROUND_HALF_EVEN);
		}

		@Override
		public int compareTo (Rational r) {
			return num.multiply(r.den).compareTo(r.num.multiply(den));
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Rational) {
				Rational r = (Rational)o;
				return num.equals(r.num) && den.equals(r.den);
			}
			return false;
		}

		@Override
		public int hashCode () {
			return num.hashCode() * 31 + den.hashCode();
		}

		@Override
		public String toString () {
			return String.format("%s/%s", num.toString(), den.toString());
		}

		public static void main (String[] args) {
			Rational a = new Rational(23, 94);
			Rational b = new Rational(24, 47);

			// Expected: 71/94
			System.out.println(a.add(b));
		}
	}
}

