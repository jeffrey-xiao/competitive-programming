package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class VMSS_Agriphilisophical_Data_Slaves {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] a;
	static int max = -1 << 30;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		a = new int[n];
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		for (int i = 0; i < n - 1; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
		}
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		solve(0);
		System.out.println(max);
	}

	private static int solve (int i) {
		int res = a[i];
		for (Integer next : adj.get(i)) {
			res += solve(next);
		}
		max = Math.max(res, max);
		return res;
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
