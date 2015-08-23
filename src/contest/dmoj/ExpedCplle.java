package contest.dmoj;

import java.util.*;
import java.io.*;

public class ExpedCplle {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int r = readInt();
		ArrayList<PriorityQueue<Long>> pq = new ArrayList<PriorityQueue<Long>>();
		for (int i = 0; i <= r; i++)
			pq.add(new PriorityQueue<Long>());
		for (int i = 0; i < n; i++) {
			int e = readInt();
			long c = readInt();
			long v = readInt();
			long ca = readInt();
			long cb = readInt();
			long cm = readInt();
			long va = readInt();
			long vb = readInt();
			long vm = readInt();
			for (int j = 0; j < e; j++) {
				if (c <= r) {
					pq.get((int)(c)).offer(v);
					while (pq.get((int)(c)).size()*c > r)
						pq.get((int)c).poll();
				}
				c = (c*ca+cb)%cm;
				v = (v*va+vb)%vm;
			} 
		}
		ArrayList<Item> items = new ArrayList<Item>();
		for (int i = 0; i <= r; i++)
			for (Long j : pq.get(i))
				items.add(new Item(i, j));
		long[] dp = new long[r+1];
		for (int i = 1; i <= r; i++)
			dp[i] = -1;
		for (Item i : items)
			for (int j = r; j >= 0; j--)
				if (j - i.c >= 0 && dp[j-i.c] != -1)
					dp[j] = Math.max(dp[j], dp[j-i.c] + i.v);
		long max = 0;
		for (int i = 0; i <= r; i++)
			max = Math.max(max, dp[i]);
		pr.println(max);
		pr.close();
	}
	static class Item {
		long v;
		int c;
		Item (int c, long v) {
			this.v = v;
			this.c = c;
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

