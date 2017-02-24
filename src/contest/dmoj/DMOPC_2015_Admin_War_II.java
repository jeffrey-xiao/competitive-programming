package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Admin_War_II {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] val1, val2;
	static TreeMap<Integer, Integer> t1, t2;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		val1 = new int[N];
		val2 = new int[N];

		t1 = new TreeMap<Integer, Integer>();
		t2 = new TreeMap<Integer, Integer>();

		for (int i = 0; i < N; i++) {
			val1[i] = readInt();
			if (!t1.containsKey(val1[i]))
				t1.put(val1[i], 0);
			t1.put(val1[i], t1.get(val1[i]) + 1);
		}
		for (int i = 0; i < N; i++) {
			val2[i] = readInt();
			if (!t2.containsKey(val2[i]))
				t2.put(val2[i], 0);
			t2.put(val2[i], t2.get(val2[i]) + 1);
		}

		Arrays.sort(val1);
		Arrays.sort(val2);

		int ans1 = 0;
		int ans2 = 0;
		for (int i = N - 1; i >= 0; i--) {
			Integer key = t2.higherKey(val1[i]);
			if (key != null) {
				ans1++;
				t2.put(key, t2.get(key) - 1);
				if (t2.get(key) == 0)
					t2.remove(key);
			}
		}

		for (int i = N - 1; i >= 0; i--) {
			Integer key = t1.higherKey(val2[i]);
			if (key != null) {
				ans2++;
				t1.put(key, t1.get(key) - 1);
				if (t1.get(key) == 0)
					t1.remove(key);
			}
		}
		out.printf("%d\n%d\n", ans2, ans1);
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
