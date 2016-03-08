// permutations are ordered lists of size n

package codebook.math;

import java.util.Arrays;

public class Permutations {

	static void swap (int i, int j, int[] a) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	static boolean nextPermutation (int n, int[] a) {
		for (int i = n - 1; i >= 0; i--) {
			if (a[i] < a[i + 1]) {
				for (int j = n - 1;; j--) {
					if (a[i] < a[j]) {
						swap(i, j, a);
						i++;
						for (j = n - 1; i < j; i++, j--)
							swap(i, j, a);
						return true;
					}
				}
			}
		}
		return false;
	}

	// binary permutation
	static long nextPermutation (long x) {
		long s = x & -x, r = x + s;
		return r | (((x ^ r) >> 2) / s);
	}

	static int[] permutationByRank (int n, long x) {
		long[] fact = new long[n];
		fact[0] = 1;
		for (int i = 1; i < n; i++)
			fact[i] = i * fact[i - 1];

		int[] free = new int[n], ret = new int[n];

		for (int i = 0; i < n; i++)
			free[i] = i;

		for (int i = 0; i < n; i++) {
			int pos = (int) (x / fact[n - 1 - i]);
			ret[i] = free[pos];
			for (int j = pos; j < n; j++)
				free[j] = free[j + 1];
			x %= fact[n - 1 - i];
		}
		return ret;
	}

	static long rankByPermutation (int n, int[] a) {
		long[] fact = new long[n];
		fact[0] = 1;
		for (int i = 1; i < n; i++)
			fact[i] = i * fact[i - 1];

		long ret = 0;

		for (int i = 0; i < n; i++) {
			int v = a[i];
			for (int j = 0; j < i; j++)
				if (a[j] < a[i])
					v--;

			ret += v * fact[n - 1 - i];
		}
		return ret;
	}

	// permutations of size n
	static void printPermutations (int n, int[] p, int d) {
		if (d == n) {
			System.out.println(Arrays.toString(p));
			return;
		}
		for (int i = 0; i < n; i++) {
			if (p[i] == 0) {
				p[i] = d;
				printPermutations(n, p, d + 1);
				p[i] = 0;
			}
		}
	}
}
