package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Lazy_Sort {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int t = readInt();
		for (int q = 1; q <= t; q++) {
			int n = readInt();
			LinkedList<Integer> res = new LinkedList<Integer>();
			boolean valid = false;
			int[] a = new int[n];
			int l = 0, r = n - 1;
			for (int i = 0; i < n; i++) {
				a[i] = readInt();
			}
			res.add(a[l++]);
			while (l <= r) {
				if (res.peekFirst() == a[l] + 1)
					res.addFirst(a[l++]);
				else if (res.peekLast() == a[l] - 1)
					res.addLast(a[l++]);
				else if (res.peekFirst() == a[r] + 1)
					res.addFirst(a[r--]);
				else if (res.peekLast() == a[r] - 1)
					res.addLast(a[r--]);
				else {
					break;
				}
			}
			if (l > r)
				valid = true;
			l = 0;
			r = n - 1;
			res.clear();
			res.add(a[r--]);
			while (l <= r) {
				if (res.peekFirst() == a[l] + 1)
					res.addFirst(a[l++]);
				else if (res.peekLast() == a[l] - 1)
					res.addLast(a[l++]);
				else if (res.peekFirst() == a[r] + 1)
					res.addFirst(a[r--]);
				else if (res.peekLast() == a[r] - 1)
					res.addLast(a[r--]);
				else {
					break;
				}
			}
			if (l > r)
				valid = true;
			System.out.printf("Case #%d: %s\n", q, valid ? "yes" : "no");
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
