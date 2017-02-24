package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_365E_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static long K;
	static ArrayList<Long> factors;
	static long[] val;
	static State[][] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		K = readLong();

		val = new long[N + 1];
		factors = new ArrayList<Long>();

		int min = 1;
		for (int i = 1; i <= N; i++) {
			val[i] = readLong();
			if (val[i] < val[min])
				min = i;
		}

		if (K == 1) {
			out.println(1);
			out.println(min);
			out.close();
			return;
		}

		ArrayList<Factor> f = factor(K);
		fillFactors(f, 0, 1);

		Collections.sort(factors);

		dp = new State[N + 1][factors.size()];

		for (int i = 0; i <= N; i++)
			for (int j = 0; j < factors.size(); j++)
				dp[i][j] = new State(1 << 30, -1, 1 << 30);
		dp[0][0] = new State(0, -1, 0);

		int[][] valPowers = new int[N + 1][f.size()];
		int[][] factorPowers = new int[factors.size()][f.size()];
		long[][] powers = new long[f.size()][];

		for (int i = 0; i < f.size(); i++) {
			powers[i] = new long[f.get(i).count + 1];
			powers[i][0] = 1;
			for (int j = 1; j <= f.get(i).count; j++)
				powers[i][j] = powers[i][j - 1] * f.get(i).factor;
		}

		for (int i = 1; i <= N; i++) {
			long curr = val[i];
			for (int j = 0; j < f.size(); j++)
				while (curr % f.get(j).factor == 0) {
					curr /= f.get(j).factor;
					valPowers[i][j]++;
				}
		}

		for (int i = 0; i < factors.size(); i++) {
			long curr = factors.get(i);
			for (int j = 0; j < f.size(); j++)
				while (curr % f.get(j).factor == 0) {
					curr /= f.get(j).factor;
					factorPowers[i][j]++;
				}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < factors.size(); j++) {
				dp[i][j] = new State(dp[i - 1][j].val, j, dp[i - 1][j].sum);

				long gcf = 1;
				for (int k = 0; k < f.size(); k++)
					gcf *= powers[k][Math.min(valPowers[i][k], factorPowers[j][k])];

				long nextFactor = factors.get(j) / gcf;
				int index = getFactorIndex(nextFactor);
				if ((dp[i - 1][index].val + 1 < dp[i][j].val) || (dp[i - 1][index].val + 1 == dp[i][j].val && dp[i - 1][index].sum + val[i] <= dp[i][j].sum)) {
					dp[i][j] = new State(dp[i - 1][index].val + 1, index, dp[i - 1][index].sum + val[i]);
				}
			}
		}

		int i = N;
		int j = factors.size() - 1;
		if (dp[i][j].val != 1 << 30) {
			out.println(dp[i][j].val);
			while (i != 0) {
				State curr = dp[i][j];
				State prev = dp[i - 1][curr.prev];
				if (curr.sum - prev.sum != 0)
					out.print(i + " ");
				i--;
				j = curr.prev;
			}
		} else {
			out.println(-1);
		}

		out.close();
	}

	static long pow (long a, long b) {
		if (b == 0)
			return 1;
		if (b == 1)
			return a;
		if (b % 2 == 0)
			return pow(a * a, b / 2);
		return a * pow(a * a, b / 2);
	}

	static int getFactorIndex (long x) {
		int lo = 0;
		int hi = factors.size() - 1;
		while (lo <= hi) {
			int mid = (lo + hi) >> 1;

			if (factors.get(mid) < x)
				lo = mid + 1;
			else if (factors.get(mid) > x)
				hi = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	static long gcf (long a, long b) {
		return b == 0 ? a : gcf(b, a % b);
	}

	static void fillFactors (ArrayList<Factor> primes, int i, long curr) {
		if (i == primes.size()) {
			factors.add(curr);
			return;
		}
		long currPower = 1;
		for (int j = 0; j <= primes.get(i).count; j++) {
			fillFactors(primes, i + 1, curr * currPower);
			currPower *= primes.get(i).factor;
		}
	}

	static ArrayList<Factor> factor (long X) {
		ArrayList<Factor> ret = new ArrayList<Factor>();
		for (long i = 2; i * i <= X; i++) {
			if (X % i == 0) {
				ret.add(new Factor(i, 0));
				while (X % i == 0) {
					ret.get(ret.size() - 1).count++;
					X /= i;
				}
			}
		}

		if (X != 1)
			ret.add(new Factor(X, 1));

		return ret;
	}

	static class State {
		int val, prev;
		long sum;

		State (int val, int prev, long sum) {
			this.val = val;
			this.prev = prev;
			this.sum = sum;
		}
	}

	static class Factor {
		long factor;
		int count;

		Factor (long factor, int count) {
			this.factor = factor;
			this.count = count;
		}
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
}