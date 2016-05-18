package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_353D_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		TreeMap<Integer, Node> tree = new TreeMap<Integer, Node>();
		
		for (int i = 0; i < N; i++) {
			if (i == 0)
				tree.put(readInt(), new Node(false, false));
			else {
				int val = readInt();
				Map.Entry<Integer, Node> lower = tree.lowerEntry(val);
				Map.Entry<Integer, Node> higher = tree.higherEntry(val);
				if (lower != null && !lower.getValue().right) {
					tree.get(lower.getKey()).right = true;
					out.printf("%d ", lower.getKey());
				} else {
					tree.get(higher.getKey()).left = true;
					out.printf("%d ", higher.getKey());
				}
				tree.put(val, new Node(false, false));
			}
		}
		
		out.close();
	}

	static class Node {
		boolean left, right;
		Node (boolean left, boolean right) {
			this.left = left;
			this.right = right;
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

