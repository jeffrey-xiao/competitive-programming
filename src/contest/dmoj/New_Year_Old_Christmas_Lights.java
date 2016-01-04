package contest.dmoj;

import java.io.*;
import java.util.*;

public class New_Year_Old_Christmas_Lights {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, k, q;
	static int[] a;
	static int[][] min, max;

	static int[][] maxAns;
	static int[][] maxIndex;

	static int[] len;

	static TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		k = readInt();

		a = new int[n+1];
		for (int i = 1; i <= n; i++)
			a[i] = readInt();

		int ln = 1 + (int) (Math.ceil(Math.log(n) / Math.log(2)));

		min = new int[n+1][ln];
		max = new int[n+1][ln];
		maxAns = new int[n+1][ln];
		maxIndex = new int[n+1][ln];

		len = new int[n+1];

		for (int i = 1; i <= n; i++)
			min[i][0] = max[i][0] = a[i];
		
		for (int i = 1; i < ln; i++)
			for (int j = 1; j + (1 << i) - 1 <= n; j++) {
				min[j][i] = Math.min(min[j][i - 1], min[j + (1 << (i - 1))][i - 1]);
				max[j][i] = Math.max(max[j][i - 1], max[j + (1 << (i - 1))][i - 1]);
			}

		a = new int[n+1];
		
		for (int i = 1; i <= n; i++)
			len[i] = getMin(i, i, n);

		for (int i = 1; i <= n; i++) {
			maxAns[i][0] = len[i];
			maxIndex[i][0] = i;
		}
		
		for (int i = 1; i < ln; i++)
			for (int j = 1; j + (1 << i) - 1 <= n; j++) {
				if (maxAns[j][i - 1] >= maxAns[j + (1 << (i - 1))][i - 1]) {
					maxAns[j][i] = maxAns[j][i - 1];
					maxIndex[j][i] = maxIndex[j][i-1];
				} else {
					maxAns[j][i] = maxAns[j + (1 << (i - 1))][i - 1];
					maxIndex[j][i] = maxIndex[j + (1 << (i - 1))][i - 1];
				}
			}


		q = readInt();

		for (int i = 0; i < q; i++) {
			int x = readInt();
			int y = readInt();
			
			int ansIndex = 0, ansSize = 0;

			int endpoint = indexOverflow(x, y);
			
			int l = x;
			int r = endpoint - 1;
			
			int sz = (int) (Math.log(endpoint - 1 - l + 1) / Math.log(2));
			
			if (sz >= 0) {
				if (maxAns[l][sz] >= maxAns[r - (1 << sz) + 1][sz]) {
					ansIndex = maxIndex[l][sz];
					ansSize = maxAns[l][sz];
				} else {
					ansIndex = maxIndex[r - (1 << sz) + 1][sz];
					ansSize = maxAns[r - (1 << sz) + 1][sz];
				}
			}
			
			int val = Math.min(len[endpoint], y - endpoint + 1); 
			
			if (val > ansSize) {
				ansSize = val;
				ansIndex = endpoint;
			}
			
			out.println(ansIndex + " " + (ansIndex + ansSize - 1));
		}
		out.close();
	}
	static int indexOverflow (int l, int r) {
		int lo = l;
		int hi = r;
		while (lo <= hi) {
			int mid = (lo + hi) >> 1;
			if (mid + len[mid] - 1 < r)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

	static int getMin (int i, int l, int r) {
		while (l <= r) {
			int mid = (l + r) >> 1;
			if (queryMax(i, mid) - queryMin(i, mid) <= k)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return r - i + 1;
	}

	static int queryMin (int l, int r) {
		int sz = (int) (Math.log(r - l + 1) / Math.log(2));
		return Math.min(min[l][sz], min[r - (1 << sz) + 1][sz]);
	}

	static int queryMax (int l, int r) {
		int sz = (int) (Math.log(r - l + 1) / Math.log(2));
		return Math.max(max[l][sz], max[r - (1 << sz) + 1][sz]);
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
