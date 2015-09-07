/*
 * Using Mo's algorithm to determining the sum
 */

package codebook.algorithms;

import java.util.*;
import java.io.*;

public class Mo {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, sz;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		sz = (int)Math.sqrt(n);
		HashMap<Integer, LinkedList<Integer>> hm = new HashMap<Integer, LinkedList<Integer>>();
		int[] a = new int[n+1];
		for (int i = 1; i <= n; i++)
			a[i] = readInt() + a[i-1];
		Query[] q = new Query[m];
		for (int i = 0; i < m; i++)
			q[i] = new Query(readInt(), readInt(), i);
		Arrays.sort(q);
		for (Query query : q) {
			
		}
		
		out.close();
	}

	static class Query implements Comparable<Query> {
		int l, r, index;
		Query (int l, int r, int index) {
			this.l = l;
			this.r = r;
			this.index = index;
		}
		@Override
		public int compareTo (Query o) {
			if ((l - 1)/sz != (o.l - 1)/sz)
				return (l - 1)/sz - (o.l - 1)/sz;
				return r - o.r;
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

