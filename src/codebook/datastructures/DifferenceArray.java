/* 
 * A difference array allows for offline range updates and point queries.
 *
 * Time complexity: 
 * - Query: O(1) 
 * - Build: O(N)
 */

package codebook.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DifferenceArray {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, q;
	static int[] a;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		q = readInt();
		a = new int[n + 1];
		// updates use zero-based indexed
		for (int i = 0; i < q; i++) {
			int l = readInt();
			int r = readInt() + 1;
			int x = readInt();
			a[l] += x;
			a[r] -= x;
		}
		for (int i = 1; i < n; i++)
			a[i] += a[i - 1];
		for (int i = 0; i < n; i++)
			out.print(a[i] + " ");
		out.println();

		out.close();
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
