package codebook.math;

import java.util.ArrayList;

public class Euclid {

	static int mod (int a, int b) {
		return ((a % b) + b) % b;
	}

	static int gcd (int a, int b) {
		return b == 0 ? a : (gcd(b, a % b));
	}

	static int lcm (int a, int b) {
		return a / gcd(a, b) * b;
	}

	// returns d = gcd(a, b); finds x, y such that d = ax * by
	static int x, y;
	static int extendedEuclid (int a, int b) {
		int xx = y = 0;
		int yy = x = 1;
		while (b > 0) {
			int q = a / b;
			int t = b; b = a % b; a = t;
			t = xx; xx = x - q * xx; a = t;
			t = yy; yy = y - q * yy; y = t;
		}
		return a;
	}

	// finds all solutions to ax = b mod n
	static ArrayList<Integer> linearEquationSolver (int a, int b, int n) {
		ArrayList<Integer> ret = new ArrayList<Integer>();

		int d = extendedEuclid(a, b);
		if (b % d == 0) {
			x = mod(x * (b / d), n);
			for (int i = 0; i < d; i++)
				ret.add(mod(x + i * (n / d), n));
		}
		return ret;
	}

	// computes b such that ab = 1 (mod n), returns -1 on failure
	static int modInverse (int a, int n) {
		int d = extendedEuclid(a, n);
		if (d > 1)
			return -1;
		return mod(x, n);
	}

	// computes x and y such that ax + by = c; on failure, x = y = -1
	static void linearDiophantine(int a, int b, int c) {
		int d = gcd(a, b);
		if (c % d != 0) {
			x = y = -1;
		} else {
			x = c / d * modInverse(a / d, b / d);
			y = (c - a * x) / b;
		}
	}
}

