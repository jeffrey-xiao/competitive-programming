package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class COCI_2006_PRASE {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static HashMap<String, Integer> hs = new HashMap<String, Integer>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int count = 0;
		for (int x = 1; x <= n; x++) {
			String s = next();
			add(s);
			if ((hs.get(s) - 1) * 2 >= x)
				count++;
		}
		System.out.println(count);
	}

	private static void add (String n) {
		if (hs.get(n) == null)
			hs.put(n, 1);
		else
			hs.put(n, hs.get(n) + 1);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
