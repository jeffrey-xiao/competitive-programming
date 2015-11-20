/*
 * Algorithm that finds the number of inversions in a string.
 * It uses the concept behind merge sort (divide and conquer) to efficiently count the inversions.
 *
 * Time complexity: O(N log N) where N is the length of the array.
 */

package codebook.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountInversions {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int ans = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		sort(a, new int[n], 0, n - 1);
		out.println(ans / 2);
		out.close();
	}

	private static void sort (int[] a, int[] aux, int lo, int hi) {
		if (hi - lo <= 0)
			return;
		int mid = (hi + lo) >> 1;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static void merge (int[] a, int[] aux, int lo, int mid, int hi) {
		for (int i = lo; i <= mid; i++)
			aux[i] = a[i];
		for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
			if (j == hi + 1 || (i <= mid && aux[i] <= a[j])) {
				a[k] = aux[i++];
				ans += j - (mid + 1);
			} else {
				a[k] = a[j++];
				ans += (mid + 1 - i);
			}
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
