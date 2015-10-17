package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int[] cards = new int[11];
		for (int i = 0; i < 11; i++)
			cards[i] = readInt();
		int min = 1 << 30;
		for (int i = 0; i < 8; i++) {
			min = Math.min(min, cards[i]);
		}
		int res = min;
		for (int i = 0; i < 8; i++) {
			cards[i] -= min;
		}
//		for (int i = 0; i < 8; i++)
//			System.out.print(cards[i] + " ");
//		System.out.println();
//		System.out.println(res);
		int lo = 0;
		int hi = 100000000;
		while (lo <= hi) {
			int mid = (hi + lo) / 2;
			int needed = Math.max(0, mid - cards[1]) + Math.max(0, mid - cards[5]);
			if (needed > cards[8])
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		cards[8] -= Math.max(0, hi - cards[1]);
		cards[8] -= Math.max(0, hi - cards[5]);
		cards[1] = Math.max(cards[1], hi);
		cards[5] = Math.max(cards[5], hi);
		if (cards[8] == 1)
			cards[1]++;
		lo = 0;
		hi = 100000000;
		while (lo <= hi) {
			int mid = (hi + lo) /2;
			int needed = 0;
			for (int i = 0; i < 8; i++) {
				if (i == 1 || i == 5)
					continue;
				needed += Math.max(0, mid - cards[i]);
			}
			if (needed > cards[9])
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		for (int i = 0; i < 8; i++) {
			if (i == 1 || i == 5)
				continue;
			cards[9] -= Math.max(0, hi - cards[i]);
			cards[i] = Math.max(cards[i], hi);
		}
		for (int i = 0; i < 8; i++) {
			if (i == 1 || i == 5)
				continue;
			if (cards[9] > 0) {
				cards[i]++;
				cards[9]--;
			}
		}
		
		min = 1 << 30;
		for (int i = 0; i < 8; i++) {
			min = Math.min(min, cards[i]);
		}
		for (int i = 0; i < 8; i++) {
			cards[i] -= min;
		}
		res += min;
		
		lo = 0;
		hi = 100000000;
		while (lo <= hi) {
			int mid = (hi + lo)/2;
			int needed = 0;
			for (int i = 0; i < 8; i++) {
				needed += Math.max(0, mid - cards[i]);
			}
			if (needed > cards[10])
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		res += hi;
		out.println(res);
		
		
		
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

