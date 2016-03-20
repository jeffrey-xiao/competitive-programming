package contest.dmoj;

import java.util.*;
import java.io.*;

public class New_Year_Arithmetic_Snowman {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int[] a = new int[n];

		HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();

		for (int i = 0; i < n; i++) {
			a[i] = readInt();
			if (!hs.containsKey(a[i]))
				hs.put(a[i], 0);
			hs.put(a[i], hs.get(a[i]) + 1);
		}

		int max = 0;

		for (Map.Entry<Integer, Integer> e : hs.entrySet())
			if (e.getValue() >= 3)
				max = Math.max(max, e.getKey() * 3);

		Arrays.sort(a);
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (hs.containsKey(a[j] + a[j] - a[i]) && a[i] != a[j])
					max = Math.max(max, a[j] + a[j] - a[i] + a[j] + a[i]);

		out.println(max);

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
