package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_King_And_Weber {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static final boolean P = true;
	static final boolean I = false;
	static ArrayList<ArrayList<Edge>> adj;
	static int[] types;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int q = readInt();
		adj = new ArrayList<ArrayList<Edge>>();
		HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
		int count = 0;
		for (int x = 0; x < n; x++) {
			String s1 = next();
			String s2 = next();
			boolean type = next().equals("parallel");
			if (!toIndex.containsKey(s1))
				toIndex.put(s1, count++);
			if (!toIndex.containsKey(s2))
				toIndex.put(s2, count++);
			int a = toIndex.get(s1);
			int b = toIndex.get(s2);
			for (int y = adj.size(); y < toIndex.size(); y++)
				adj.add(new ArrayList<Edge>());
			adj.get(a).add(new Edge(b, type));
			adj.get(b).add(new Edge(a, type));
		}
		if (isImpossible()) {
			System.out.println("Waterloo");
			return;
		}
		for (int x = 0; x < q; x++) {
			String s1 = next();
			String s2 = next();
			if (toIndex.get(s1) == null || toIndex.get(s2) == null) {
				System.out.println("unknown");
				continue;
			}
			int a = toIndex.get(s1);
			int b = toIndex.get(s2);
			if (types[a] == types[b])
				System.out.println("parallel");
			else if ((types[a] % 2 == 0 && types[a] - types[b] == -1)
					|| (types[b] % 2 == 0 && types[b] - types[a] == -1))
				System.out.println("intersect");
			else
				System.out.println("unknown");
		}
	}

	static boolean isImpossible () {
		types = new int[adj.size()];
		for (int x = 0; x < types.length; x++)
			types[x] = -1;
		int count = 0;
		for (int x = 0; x < types.length; x++) {
			if (types[x] != -1)
				continue;
			types[x] = count;
			Queue<Integer> moves = new LinkedList<Integer>();
			moves.offer(x);
			while (!moves.isEmpty()) {
				int curr = moves.poll();
				for (Edge e : adj.get(curr)) {
					int nextType = count + (types[curr] + (e.type ? 0 : 1)) % 2;
					if (types[e.dest] == -1) {
						moves.offer(e.dest);
						types[e.dest] = nextType;
					} else {
						if (types[e.dest] != nextType)
							return true;
					}
				}
			}
			count += 2;
		}
		// for(int x = 0; x < types.length; x++)
		// System.out.println(types[x]);
		return false;
	}

	static class Edge {
		int dest;
		boolean type;

		Edge (int dest, boolean type) {
			this.dest = dest;
			this.type = type;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
