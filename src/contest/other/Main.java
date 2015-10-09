package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int ans = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] x = new int[n+1];
		for(int i = 1; i <= n; i++)
			x[i] = x[i-1] + sc.nextInt();
		int a, b, m;
		for(int i = 0; i < q; i++)
		{
			a = sc.nextInt();
			b = sc.nextInt();
			m = (a+b)/2;
			System.out.println(x[b]-x[m]-((a+b)%2==0?x[m-1]:x[m])+x[a-1]);
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