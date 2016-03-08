package codebook.math;

public class Combinatorics {

	// O(n)
	static long factorial (long n, long m) {
		long ret = 1;
		for (int i = 2; i <= n; i++)
			ret = (ret * i) % m;
		return ret;
	}

	// O(p log n)
	static long factorialPrime (long n, long p) {
		long ret = 1, h = 0;
		while (n > 1) {
			ret = (ret * ((n / p) % 2 == 1 ? p - 1 : 1)) % p;
			h = n % p;
			for (int i = 2; i <= h; i++)
				ret = (ret * i) % p;
			n /= p;
		}
		return ret % p;
	}

	// O(log power)
	static long pow (long base, long pow, long mod) {
		if (pow == 0)
			return 1;
		if (pow == 1)
			return base;
		if (pow % 2 == 0)
			return pow(base * base % mod, pow / 2, mod);
		return base * pow(base * base % mod, pow / 2, mod) % mod;
	}

	// O(min(k, n-k))
	static long choose (int n, int k, long p) {
		if (n < k)
			return 0;
		if (k > n - k)
			k = n - k;
		long num = 1;
		long den = 1;
		for (int i = 0; i < k; i++)
			num = (num * (n - i)) % p;
		for (int i = 1; i <= k; i++)
			den = (den * i) % p;
		return num * pow(den, p - 2, p) % p;
	}

	static long choose (long n, long k) {
		if (k > n - k)
			k = n - k;
		long ret = 1;
		for (int i = 0; i < k; i++)
			ret = ret * (n - i) / (i + 1);
		return ret;
	}

	// O(k)
	static long multichoose (int n, int k, long p) {
		return choose(n + k - 1, k, p);
	}

	// O(k)
	static long permute (int n, int k) {
		if (n < k)
			return 0;
		long ret = 1;
		for (int i = 0; i < k; i++)
			ret = (ret * (n - i));
		return ret;
	}

	static long permute (int n, int k, long m) {
		if (n < k)
			return 0;
		long ret = 1;
		for (int i = 0; i < k; i++)
			ret = (ret * (n - i)) % m;
		return ret % m;
	}

	// O(n^2)
	static long partitions (int n, long m) {
		long[] dp = new long[n + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = i; j <= n; j++)
				dp[j] = (dp[j] + dp[j - 1]) % m;
		return dp[n] % m;
	}

	// O(n * k)
	static long partitions (int n, int k, long m) {
		long[][] dp = new long[n + 1][k + 1];
		dp[0][1] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= Math.min(i, k); j++)
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - j][j]) % m;
		return dp[n][k] % m;
	}

	// O(n * k)
	static long stirling1 (int n, int k, long m) {
		long[][] dp = new long[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= k; j++) {
				dp[i][j] = ((i - 1) * dp[i - 1][j]) % m;
				dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % m;
			}
		return dp[n][k];
	}

	// O(n * k)
	static long stirling2 (int n, int k, long m) {
		long[][] dp = new long[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (j * dp[i - 1][j]) % m;
				dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % m;
			}
		return dp[n][k];
	}

	// O(n * k)
	static long eulerian1 (int n, int k, long m) {
		if (k > n - 1 - k)
			k = n - 1 - k;
		long[][] dp = new long[n + 1][k + 1];
		for (int j = 1; j <= k; j++)
			dp[0][j] = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= k; j++) {
				dp[i][j] = ((i - j) * dp[i - 1][j - 1]) % m;
				dp[i][j] = (dp[i][j] + ((j + 1) * dp[i - 1][j]) % m) % m;
			}
		return dp[n][k] % m;
	}

	// O(n * k)
	static long eulerian2 (int n, int k, long m) {
		long[][] dp = new long[n + 1][k + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= k; j++) {
				if (i == j) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = ((j + 1) % dp[i - 1][j]) % m;
					dp[i][j] = (((2 * i - 1 - j) * dp[i - 1][j - 1]) % m + dp[i][j]) % m;
				}
			}
		return dp[n][k] % m;
	}

	// O(n)
	static long catalan (int n, long p) {
		return choose(2 * n, n, p) * pow(n + 1, p - 2, p) % p;
	}

	// O(log P) if you precompute factorials
	static long fastChoose (int n, int k, int p) {
		return divMod(divMod(factorial(n, p), factorial(k, p), p), factorial(n - k, p), p);
	}

	// O(log P)
	static long divMod (long i, long j, long p) {
		return i * pow(j, p - 2, p) % p;
	}
}
