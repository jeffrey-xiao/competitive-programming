package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Directed_Acyclic_Path2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();

		ArrayList<ArrayList<Integer>> incoming = new ArrayList<ArrayList<Integer>>();
		Queue<Integer> q = new LinkedList<Integer>();
		// ArrayList<Integer> order = new ArrayList<Integer>();
		for (int x = 0; x < n; x++) {
			incoming.add(new ArrayList<Integer>());
			q.offer(x);
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (readInt() == 1) {
					incoming.get(y).add(x);
					q.remove(y);
				}
			}
		}
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int x = 0; x < incoming.size(); x++) {
				if (incoming.get(x).contains(curr)) {
					incoming.get(x).remove((Integer) curr);
					if (incoming.get(x).size() == 0)
						q.add(x);
				}
			}
		}
		for (int x = 0; x < incoming.size(); x++) {
			if (incoming.get(x).size() != 0) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
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
