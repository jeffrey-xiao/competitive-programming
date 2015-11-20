/*
 * Computes the b^p % m and b^p.
 *
 * Time complexity: O(log p) where p is the power.
 */

package codebook.algorithms;

public class BinaryExponentiation {
	public long modpow (long b, long p, long m) {
		if (p == 0)
			return 1;
		if (p == 1)
			return b;
		if (p % 2 == 0)
			return modpow(b * b % m, p, m);
		return b * modpow(b * b % m, p, m) % m;
	}
	public long pow (long b, long p) {
		if (p == 0)
			return 1;
		if (p == 1)
			return b;
		if (p % 2 == 0)
			return pow(b * b, p);
		return b * pow(b * b, p);
	}
}
