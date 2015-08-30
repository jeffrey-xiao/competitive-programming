package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Longest_Increasing_Subsequence {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		// key and cnt
		TreeMap<Integer, Integer> tm1 = new TreeMap<Integer, Integer>();
		// cnt and key
		TreeMap<Integer, Integer> tm2 = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			int a = readInt();
			if (tm1.lowerKey(a) == null) {
				if (!tm2.containsKey(1) || (tm2.containsKey(1) && tm2.get(1) > a)) {
					if (tm2.containsKey(1))
						tm1.remove(tm2.get(1));
					tm1.put(a, 1);
					tm2.put(1, a);
				}
			} else {
				int newLen = tm1.get(tm1.lowerKey(a)) + 1;
				if (!tm2.containsKey(newLen) || (tm2.containsKey(newLen) && tm2.get(newLen) > a)) {
					if (tm2.containsKey(newLen))
						tm1.remove(tm2.get(newLen));
					tm1.put(a, newLen);
					tm2.put(newLen, a);
				}
			}
		}
		System.out.println(tm2.lastKey());
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
