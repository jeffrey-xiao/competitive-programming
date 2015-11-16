package contest.misc;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int ans = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	/*
		public static void main (String[] args) throws IOException {
			//		permute(new int[] {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, 0);
			//		System.out.println(ans);
			int n = readInt();
			int q = readInt();
			int[] c = new int[n + 2], prefix = new int[n + 2], suffix = new int[n * 2];
			for (int i = 1; i <= n; i++) {
				c[i] = readInt();
				prefix[i] = c[i] - c[1] + prefix[i - 1];
			}
			for (int i = n; i >= 1; i--)
				suffix[i] = c[n] - c[i] + suffix[i + 1];
			for (int i = 0; i < q; i++) {
				int a = readInt();
				int b = readInt();
				int median = (a + b) / 2;
				System.out.println(prefix[b] - prefix[median] - (c[median] - c[1]) * (b - median) + suffix[a] - suffix[median] - (c[n] - c[median]) * (median - a));
			}

		}
	*/
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 1; qq <= t; qq++) {
			long n = readLong();
			long m = readLong();
			long cnt = 1;
			if (m != 0)
				cnt = n * m / gcf(n, m) / m;
			if (cnt == n)
				out.println("Yes");
			else
				out.println("No " + cnt);
			boolean flag = true;
			long count = 1;
			long a[] = new long[(int) (n + 1)];
			Arrays.fill(a, 0);
			long pos = m + 1;
			long x = 0;
			a[(int) pos] = 1;

			while (flag) {
				x = m + pos;
				if ((x > n) && (x != n)) {
					x = Math.abs(x - n);
					if (a[(int) x] == 1) {
						break;
					}
					pos = x;
				} else {
					if (x < n) {
						if (a[(int) x] == 1) {
							break;
						}
						pos = x;
					} else {
						if (x == n) {
							if (a[(int) x] == 1) {
								break;
							}
							pos = x;
						}
					}
				}
				count++;
			}
			if (count == n) {
				out.println("Yes");

			} else {
				out.println("No " + count);
			}
			out.flush();
		}
		out.close();
	}

	static long gcf (long a, long b) {
		return b == 0 ? a : gcf(b, a % b);
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

	static void permute (int[] a, int i) {
		if (i == 11) {
			for (int k = 0; k < 3; k++) {
				int[] cnt = new int[3];
				for (int j = k * 4; j < (k + 1) * 4; j++)
					cnt[a[j]]++;
				int total = 0;
				for (int j = 0; j < 3; j++)
					if (cnt[j] == 0)
						total++;
				if (total == 1)
					ans++;
			}
			return;
		}
		for (int j = i; j < 12; j++) {
			swap(a, i, j);
			permute(a, i + 1);
			swap(a, i, j);
		}
	}

	static void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}