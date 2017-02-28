package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: gift1
 */
import java.util.*;
import java.io.*;

public class gift1 {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("gift1.in"));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int n = readInt();
		Map<String, Integer> m = new HashMap<String, Integer>();
		String[] names = new String[n];
		int[] money = new int[n];
		for (int x = 0; x < n; x++) {
			names[x] = next();
			m.put(names[x], x);
		}
		for (int x = 0; x < n; x++) {
			String curr = next();
			int total = readInt();
			int div = readInt();
			if (div == 0)
				continue;
			int indiv = total / div;
			money[m.get(curr)] -= indiv * div;
			for (int y = 0; y < div; y++) {
				money[m.get(next())] += indiv;
			}
		}
		for (int x = 0; x < n; x++) {
			pr.println(names[x] + " " + money[x]);
		}
		pr.close();
		System.exit(0);
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
