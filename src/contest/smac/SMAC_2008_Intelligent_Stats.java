package contest.smac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class SMAC_2008_Intelligent_Stats {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static HashMap<Integer, String> toName = new HashMap<Integer, String>();
	static HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int count;
	static int c;
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		for (int x = 0; x < 20000; x++)
			adj.add(new ArrayList<Integer>());

		for (int x = 0; x < n; x++) {
			String a = next();
			String b = next();
			if (!toIndex.containsKey(a)) {
				toName.put(count, a);
				toIndex.put(a, count++);
			}
			if (!toIndex.containsKey(b)) {
				toName.put(count, b);
				toIndex.put(b, count++);
			}
			int i = toIndex.get(a);
			int j = toIndex.get(b);
			adj.get(i).add(j);
		}
		System.out.println(count);
		for (int x = 0; x < count; x++) {
			v = new boolean[count];
			c = 0;
			Stack<Integer> s = new Stack<Integer>();
			s.push(x);
			v[x] = true;
			while (!s.isEmpty()) {
				int curr = s.pop();
				for (Integer i : adj.get(curr))
					if (!v[i]) {
						s.push(i);
						v[i] = true;
						c++;
					}
			}
			System.out.println(toName.get(x) + " " + c);
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
