package codebook.math;

import java.math.BigInteger;

public class Rational implements Comparable<Rational> {
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
