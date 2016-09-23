package contest.bloomberg;

import java.util.*;
import java.io.*;

public class P2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		TreeMap<Character, TreeSet<Integer>> tm = new TreeMap<Character, TreeSet<Integer>>();
		
		N = readInt();
		HashSet<Character> open = new HashSet<Character>();
		
		for (int i = 0; i < N; i++) {
			String next = readLine();
			if (next.charAt(0) == '^') {
				open.add(next.charAt(1));
				if (tm.get(next.charAt(1)) == null)
				tm.put(next.charAt(1), new TreeSet<Integer>());
			} else if (next.charAt(0) == '/') {
				open.remove(next.charAt(1));
			} else {
				int val = Integer.parseInt(next);
				for (Character c : open) {
					tm.get(c).add(val);
				}
			}
		}
		
		for (Map.Entry<Character, TreeSet<Integer>> e : tm.entrySet()) {
			out.print(e.getKey() + " ");
			for (Integer i : e.getValue())
				out.print(i + " ");
			out.println();
		}
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

