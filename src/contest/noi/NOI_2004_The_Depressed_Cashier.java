package contest.noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NOI_2004_The_Depressed_Cashier {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SIZE = 300010;
	static int[] tree;
	static int delta;
	static int employees;
	static int left;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int minWage = readInt();
		tree = new int[SIZE];
		for (int x = 0; x < n; x++) {
			char c = readCharacter();
			int k = readInt();
			if (c == 'I') {
				if (k < minWage)
					continue;
				update(k - delta, 1);
				employees++;
			} else if (c == 'A') {
				delta += k;
			} else if (c == 'S') {

				delta -= k;
				int lower = freq(minWage - 1 - delta);
				employees -= lower;
				left += lower;
				
				for (int y = 1; y <= minWage - 1 - delta; y++) {
					update(y, -tree[y]);
				}
			} else if (c == 'F') {
				int lo = 1;
				int hi = SIZE - 1;
				
				if (k > employees) {
					System.out.println(-1);
					continue;
				}
				
				k = employees - k + 1;
				
				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					if (freq(mid) < k)
						lo = mid + 1;
					else
						hi = mid - 1;
				}
				System.out.println(lo + delta);
			}
		}
		System.out.println(left);
	}

	static void update (int idxx, int k) {
		for (int x = idxx; x < SIZE; x += (x & -x))
			tree[x] += k;
	}

	static int freq (int idxx) {
		int sum = 0;
		for (int x = idxx; x > 0; x -= (x & -x))
			sum += tree[x];
		return sum;
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
