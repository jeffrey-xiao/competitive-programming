package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Epiccode_C {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();
		int[] boy = new int[n];
		TreeMap<Integer, Integer> girl = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			boy[i] = readInt();
		}
		for (int i = 0; i < n; i++) {
			int in = readInt();
			if (!girl.containsKey(in))
				girl.put(in, 0);
			girl.put(in, girl.get(in) + 1);
		}
		Arrays.sort(boy);
		int ans = 0;
		for (int i = 0; i < n; i++) {
			Integer next = girl.ceilingKey(boy[i] - k);
			if (next != null && Math.abs(next - boy[i]) <= k) {
				ans++;
				girl.put(next, girl.get(next) - 1);
				if (girl.get(next) == 0)
					girl.remove(next);
			}
		}
		System.out.println(ans);

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
