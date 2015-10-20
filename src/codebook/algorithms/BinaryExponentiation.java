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
}
