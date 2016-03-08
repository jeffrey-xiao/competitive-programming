package codebook.math;

public class Toolkit {
	static double roundToSignificantFigures (double num, int n) {
		if (num == 0) {
			return 0;
		}

		final double d = Math.ceil(Math.log10(num < 0 ? -num : num));
		final int power = n - (int) d;

		final double magnitude = Math.pow(10, power);
		final long shifted = Math.round(num * magnitude);
		return shifted / magnitude;
	}

	static int lcm (int a, int b) {
		return a / gcd(a, b) * b;
	}

	static int gcd (int a, int b) {
		if (b == 0)
			return a;
		if (b == 1)
			return 1;
		return gcd(b, a % b);
	}

	public static long[] euclid (long a, long b) {
		long x = 1, y = 0, x1 = 0, y1 = 1;
		// invariant: a=a*x+b*y, b=a*x1+b*y1
		while (b != 0) {
			long q = a / b;
			long _x1 = x1;
			long _y1 = y1;
			long _b = b;
			x1 = x - q * x1;
			y1 = y - q * y1;
			b = a - q * b;
			x = _x1;
			y = _y1;
			a = _b;
		}
		return a > 0 ? new long[] {a, x, y} : new long[] {-a, -x, -y};
	}

	public static long[] euclid2 (long a, long b) {
		if (b == 0)
			return a > 0 ? new long[] {a, 1, 0} : new long[] {-a, -1, 0};
		long[] r = euclid2(b, a % b);
		return new long[] {r[0], r[2], r[1] - a / b * r[2]};
	}

	// precondition: m > 0
	public static long mod (long a, long m) {
		a %= m;
		return a >= 0 ? a : a + m;
	}

	// precondition: m > 0 && gcd(a, m) = 1
	public static long modInverse (long a, long m) {
		a = mod(a, m);
		return a == 0 ? 0 : mod((1 - modInverse(m % a, a) * m) / a, m);
	}

	// precondition: m > 0 && gcd(a, m) = 1
	static long modInverse2 (long a, long m) {
		return (euclid(a, m)[1] % m + m) % m;
	}

	// precondition: p is prime
	public static int[] generateInverse (int p) {
		int[] res = new int[p];
		res[1] = 1;
		for (int i = 2; i < p; ++i)
			res[i] = (p - (p / i) * res[p % i] % p) % p;
		return res;
	}

	// solve x = a[i] (mod p[i]), where gcd(p[i], p[j]) == 1
	static int simpleRestore (int[] a, int[] p) {
		int res = a[0];
		int m = 1;
		for (int i = 1; i < a.length; i++) {
			m *= p[i - 1];
			while (res % p[i] != a[i])
				res += m;
		}
		return res;
	}

	static int garnerRestore (int[] a, int[] p) {
		int[] x = new int[a.length];
		for (int i = 0; i < x.length; ++i) {
			x[i] = a[i];
			for (int j = 0; j < i; ++j) {
				x[i] = (int) modInverse(p[j], p[i]) * (x[i] - x[j]);
				x[i] = (x[i] % p[i] + p[i]) % p[i];
			}
		}
		int res = x[0];
		int m = 1;
		for (int i = 1; i < a.length; i++) {
			m *= p[i - 1];
			res += x[i] * m;
		}
		return res;
	}

}
