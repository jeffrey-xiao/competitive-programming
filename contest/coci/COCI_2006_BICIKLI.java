package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2006_BICIKLI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static final long M = 1000000000L;
	static boolean[] reachable;
	static ArrayList<ArrayList<Integer>> adj;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] in = new int[n];
		reachable = new boolean[n];
		adj = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());
		for (int k = readInt(); k > 0; k--) {
			int src = readInt() - 1;
			int dest = readInt() - 1;
			adj.get(src).add(dest);
			in[dest]++;
		}
		markReachable(0);
		for (int x = 0; x < n; x++)
			if (!reachable[x])
				for (Integer i : adj.get(x))
					in[i]--;
		long[] total = new long[n];
		Queue<Integer> curr = new LinkedList<Integer>();
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		curr.offer(0);
		while (!curr.isEmpty()) {
			Integer c = curr.poll();
			sorted.add(c);
			for (Integer next : adj.get(c)) {
				in[next]--;
				if (in[next] == 0)
					curr.offer(next);
			}
		}
		boolean pad = false;
		for (int x = 0; x < sorted.size(); x++) {
			int i = sorted.get(x);
			if (x == 0)
				total[i] = 1;
			// System.out.println(i);
			for (Integer c : adj.get(i)) {
				if (total[c] + total[i] >= M)
					pad = true;
				total[c] += (total[i]) % M;
			}
		}
		if (pad)
			System.out.printf("%09d", total[1] % M);
		else
			System.out.println(total[1] % M);
	}

	public static void markReachable (int i) {
		reachable[i] = true;
		for (Integer next : adj.get(i))
			if (!reachable[next])
				markReachable(next);
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
