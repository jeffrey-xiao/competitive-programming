/*
 * The Boyer-Moore majority vote algorithm determines if there is an majority element and returns the element in O(N) time.
 */

package codebook.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Majority {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[] a;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		a = new int[n];

		for (int i = 0; i < n; i++)
			a[i] = readInt();

		int candidate = 0, cnt = 0;
		for (int i = 0; i < n; i++) {
			if (cnt == 0) {
				candidate = i;
				cnt = 1;
			} else if (a[i] == candidate) {
				cnt++;
			} else {
				cnt--;
			}
		}

		cnt = 0;
		for (int i = 0; i < n; i++)
			if (a[i] == a[candidate])
				cnt++;

		if (cnt < (n + 1) / 2)
			out.println(-1);
		else
			out.println(a[candidate]);

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
