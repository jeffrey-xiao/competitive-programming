package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2009_Stage_2_A_Weighty_Problem {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int c = readInt();
		int n = readInt();
		int k = readInt();
		int[] count = new int[n];
		Coin[] coins = new Coin[n];
		for (int x = 0; x < n; x++) {
			coins[x] = new Coin(readInt(), readDouble());
		}
		int total = 0;
		double totalWeight = 0;
		for (int x = 0; x < k; x++) {
			int coin = readInt() - 1;
			count[coin]++;
			total += coins[coin].value;
			totalWeight += coins[coin].weight;
		}
		if (total < c) {
			System.out.println("too poor");
			return;
		}
		boolean[] dp = new boolean[total + 1];
		double[] maxWeight = new double[total + 1];
		maxWeight[0] = 0;
		dp[0] = true;
		for (int x = 0; x < n; x++) {
			if (count[x] == 0)
				continue;
			for (int y = total; y >= 0; y--) {
				if (dp[y]) {
					for (int z = 1; z <= count[x]; z++)
						if (dp[y] && y + z * coins[x].value <= total) {
							int i = y + z * coins[x].value;
							dp[i] = true;
							maxWeight[i] = Math.max(maxWeight[i], maxWeight[y] + z * coins[x].weight);
						}
				}
			}
		}
		Arrays.sort(coins);
		double min = Integer.MAX_VALUE;
		for (int x = 1; x <= total; x++)
			if (dp[x] && x >= c)
				min = Math.min(min, totalWeight - diff(x - c, coins, maxWeight[x]));
		System.out.printf("%.2f", min);
	}

	static double diff (int c, Coin[] coins, double weight) {
		double newWeight = 0;
		for (int x = 0; x < coins.length; x++) {
			newWeight += c / coins[x].value * coins[x].weight;
			c %= coins[x].value;
		}
		return weight - newWeight;
	}

	static class Coin implements Comparable<Coin> {
		int value;
		double weight;

		Coin (int value, double weight) {
			this.value = value;
			this.weight = weight;
		}

		@Override
		public int compareTo (Coin o) {
			return o.value - value;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
