package contest.dmoj;

import java.util.*;
import java.io.*;

public class Glenforest_Starry_Sky {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final long MOD = 1000000007;

	static int r, c;
	static HashMap<Integer, Long> newHm = new HashMap<Integer, Long>();
	static HashMap<Integer, Long> hm = new HashMap<Integer, Long>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		r = readInt();
		c = readInt();

		long ans = 0;
		ArrayList<Integer> validSeq = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<Integer>> toValid = new HashMap<Integer, ArrayList<Integer>>();

		for (int j = 0; j < 1 << c; j++)
			if (valid(j))
				validSeq.add(j);

		for (int j : validSeq) {
			hm.put(j, 1l);
			int marked = 0;
			for (int k = 0; k < c; k++)
				if ((j & 1 << k) > 0) {
					marked |= 1 << (k - 1);
					marked |= 1 << (k);
					marked |= 1 << (k + 1);
				}
			toValid.put(j, new ArrayList<Integer>());
			for (int k : validSeq) {
				if ((marked & k) == 0) {
					toValid.get(j).add(k);
				}
			}
		}
		for (int i = 1; i < r; i++) {
			if (i < r - 1) {
				newHm.clear();
				for (Integer j : hm.keySet()) {
					for (Integer v : toValid.get(j)) {
						if (!newHm.containsKey(v))
							newHm.put(v, 0l);
						newHm.put(v, (newHm.get(v) + hm.get(j)) % MOD);
					}
				}
				hm.clear();
				hm.putAll(newHm);
			} else {
				for (Integer j : hm.keySet()) {
					ans = (ans + hm.get(j)) % MOD;
				}
			}

		}
		out.println((ans - 1 + MOD) % MOD);
		out.close();
	}

	static boolean valid (int pattern) {
		if ((pattern & 1) > 0 || (pattern & 1 << (c - 1)) > 0)
			return false;
		for (int j = 2; j < c; j++)
			if (((pattern & (1 << j)) > 0) && ((pattern & (1 << (j - 1))) > 0) || ((pattern & (1 << j)) > 0) && ((pattern & (1 << (j - 2))) > 0))
				return false;
		return true;
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
