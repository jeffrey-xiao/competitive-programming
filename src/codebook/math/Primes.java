package codebook.math;

import java.util.*;

public class Primes {

	// Sieve of Eratosthenes in O(N log log N)
	static ArrayList<Integer> getPrimesEratosthenes (int N) {
		boolean[] prime = new boolean[N + 1];
		ArrayList<Integer> ret = new ArrayList<Integer>();

		Arrays.fill(prime, true);

		for (int i = 2; i * i <= N; i++)
			if (prime[i])
				for (int j = i * i; j <= N; j += i)
					prime[j] = false;

		for (int i = 2; i <= N; i++)
			if (prime[i])
				ret.add(i);

		return ret;
	}

	static ArrayList<Integer> getPrimesLinear (int N) {
		int[] lp = new int[N + 1];
		ArrayList<Integer> ret = new ArrayList<Integer>();

		for (int i = 2; i <= N; i++) {
			if (lp[i] == 0) {
				lp[i] = i;
				ret.add(i);
			}
			for (int j = 0; j < ret.size(); j++) {
				if (ret.get(j) > lp[i] || i * ret.get(j) > N)
					break;
				lp[i * ret.get(j)] = ret.get(j);
			}
		}
		return ret;
	}

	static ArrayList<Integer> getPrimesAtkins (int N) {
		boolean[] prime = new boolean[N + 1];
		ArrayList<Integer> ret = new ArrayList<Integer>();

		prime[2] = true;
		prime[3] = true;

		int num;

		for (int i = 1; i * i <= N; i++) {
			for (int j = 1; j * j <= N; j++) {
				num = 4 * i * i + j * j;
				if (num <= N && (num % 12 == 1 || num % 12 == 5))
					prime[num] = true;

				num = 3 * i * i + j * j;
				if (num <= N && (num % 12 == 7))
					prime[num] = true;

				if (i > j) {
					num = (3 * i * i - j * j);
					if (num <= N && num % 12 == 11)
						prime[num] = true;
				}
			}
		}

		for (int i = 5; i * i <= N; i++)
			if (prime[i])
				for (int j = i * i; j <= N; j += i)
					prime[j] = false;

		for (int i = 2; i <= N; i++)
			if (prime[i])
				ret.add(i);

		return ret;
	}

	static ArrayList<Integer> genPrimes (int l, int h) {
		int sqrth = (int) Math.ceil(Math.sqrt(h));
		int sqrtsqrth = (int) Math.ceil(Math.sqrt(sqrth));

		boolean[] prime1 = new boolean[sqrth + 1];
		boolean[] prime2 = new boolean[h - l + 1];

		ArrayList<Integer> ret = new ArrayList<Integer>();

		Arrays.fill(prime1, true);
		Arrays.fill(prime2, true);

		for (int i = 2; i <= sqrtsqrth; i++) {
			if (prime1[i])
				for (int j = i * i; j <= sqrth; j += i)
					prime1[j] = false;
		}
		for (int i = 2, n = h - l; i <= sqrth; i++) {
			if (prime1[i])
				for (int j = l / i * i - l; j <= n; j += i)
					if (j >= 0 && j + l != i)
						prime2[j] = false;
		}
		for (int i = l > 1 ? l : 2; i <= h; i++)
			if (prime2[i - l])
				ret.add(i);
		return ret;
	}

	public static void main (String[] args) {
		ArrayList<Integer> ret1 = getPrimesEratosthenes(123456789);
		ArrayList<Integer> ret2 = getPrimesLinear(123456789);
		ArrayList<Integer> ret3 = getPrimesAtkins(123456789);

		assert (ret1.equals(ret2) && ret2.equals(ret3));
	}
}
