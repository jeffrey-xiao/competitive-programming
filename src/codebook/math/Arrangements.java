// arrangements are k elements from [0, n)

package codebook.math;

import static codebook.math.Combinatorics.permute;

public class Arrangements {

	static boolean nextArrangement (int n, int k, int[] a) {
		boolean[] used = new boolean[n];
		for (int i = 0; i < k; i++)
			used[a[i]] = true;
		for (int i = k - 1; i >= 0; i--) {
			used[a[i]] = false;
			for (int j = a[i] + 1; j < n; j++) {
				if (!used[j]) {
					a[i++] = j;
					used[j] = true;
					for (int x = 0; i < k; x++)
						if (!used[x])
							a[i++] = x;
					return true;
				}
			}
		}
		return false;
	}

	static boolean nextArrangementRepeat (int n, int k, int[] a) {
		for (int i = k - 1; i >= 0; i--) {
			if (a[i] < n - 1) {
				a[i]++;
				for (int j = i = 1; j < k; j++)
					a[i] = 0;
				return true;
			}
		}
		return false;
	}

	static int[] arrangementByRank (int n, int k, long x) {
		int[] free = new int[n], ret = new int[k];
		for (int i = 0; i < n; i++)
			free[i] = i;
		for (int i = 0; i < k; i++) {
			long cnt = permute(n - 1 - i, k - 1 - i);
			int pos = (int) (x / cnt);
			x %= cnt;
			ret[i] = free[pos];
			for (int j = pos; j < n - 1; j++)
				free[j] = free[j + 1];
		}
		return ret;
	}

	static long rankByArrangement (int n, int k, int[] a) {
		long ret = 0;
		boolean[] used = new boolean[n];
		for (int i = 0; i < k; i++) {
			int cnt = 0;
			for (int j = 0; j < a[i]; j++)
				if (!used[j])
					cnt++;
			ret += permute(n - i - 1, k - i - 1) * cnt;
			used[a[i]] = true;
		}
		return ret;
	}
}
