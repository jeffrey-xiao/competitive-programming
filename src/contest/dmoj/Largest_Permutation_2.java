package contest.dmoj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Largest_Permutation_2 {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();
		int[] a = new int[n + 1];
		int[] ind = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = readInt();
			ind[a[i]] = i;
		}
		int swaps = 0;
		for (int i = 1; i <= n && swaps < k; i++) {
			if (a[i] == n - i + 1)
				continue;
			int j = ind[n - i + 1];
			int temp = ind[n - i + 1];
			ind[n - i + 1] = ind[a[i]];
			ind[a[i]] = temp;
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			swaps++;
		}
		for (int i = 1; i <= n; i++)
			System.out.print(a[i] + " ");
		pr.close();
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
