package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class VMSS_Jeffrey_And_Frank_And_A_Lack_Of_Roads {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int r = readInt();
		int s = readInt();
		int[][] dp = new int[r + 1][s + 1];

		Apple[] a = new Apple[n];
		for (int i = 0; i < n; i++)
			a[i] = new Apple(next(), readInt(), readInt(), readInt());
		Arrays.sort(a);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= r - a[i].cost; j++) {
				for (int k = 0; k <= s - a[i].volume; k++) {
					dp[j + a[i].cost][k + a[i].volume] = Math.max(dp[j][k] + a[i].value, dp[j + a[i].cost][k + a[i].volume]);
				}
			}
		}
		int currR = r;
		int currS = s;
		int[] cnt = new int[n];
		boolean found = true;
		while (found) {
			found = false;
			for (int i = 0; i < n; i++) {
				if (currR - a[i].cost >= 0 && currS - a[i].volume >= 0 && dp[currR][currS] == dp[currR - a[i].cost][currS - a[i].volume] + a[i].value) {
					currR -= a[i].cost;
					currS -= a[i].volume;
					cnt[i]++;
					found = true;
					break;
				}
			}
		}
		out.println(dp[r][s]);
		for (int i = 0; i < n; i++)
			out.println(a[i].name + " " + cnt[i]);
		out.close();
	}

	static class Apple implements Comparable<Apple> {
		String name;
		int value, cost, volume;

		Apple (String name, int value, int cost, int volume) {
			this.name = name;
			this.value = value;
			this.cost = cost;
			this.volume = volume;
		}

		@Override
		public int compareTo (Apple a) {
			return name.compareTo(a.name);
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
