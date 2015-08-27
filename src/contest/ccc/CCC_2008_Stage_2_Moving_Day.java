package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_Moving_Day {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	/*
	 * 3 Pierre 51 43 Guy 28 83 Marie 43 28
	 * 
	 * Guy Marie Pierre
	 */
	public static void main (String[] args) throws IOException {
		/*
		 * L Empty list where we put the sorted elements Q Set of all nodes with
		 * no incoming edges while Q is non-empty do remove a node n from Q
		 * insert n into L for each node m with an edge e from n to m do remove
		 * edge e from the graph if m has no other incoming edges then insert m
		 * into Q if graph has edges then output error message (graph has a
		 * cycle) else output message (proposed topologically sorted order: L)
		 */
		int n = readInt();
		Map<Integer, String> map = new HashMap<Integer, String>();
		Map<Integer, Integer> incoming = new HashMap<Integer, Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		ArrayList<Integer> order = new ArrayList<Integer>();
		Set<Integer> add = new HashSet<Integer>();
		Set<Integer> remove = new HashSet<Integer>();
		for (int x = 0; x < n; x++) {
			String s = next();
			int a = readInt();
			int b = readInt();
			map.put(a, s);
			incoming.put(b, a);
			add.add(b);
			remove.add(a);
		}
		q.addAll(add);
		q.removeAll(remove);
		while (!q.isEmpty()) {
			if (q.peek() == null) {
				q.poll();
				continue;
			}
			int next = q.poll();

			order.add(next);
			q.offer(incoming.get(next));
			// System.out.println(next + " " + q.size() + " " + q.peek());
			incoming.remove(next);
		}
		if (incoming.size() > 0)
			System.out.println("Impossible");
		else
			for (int x = 0; x < order.size(); x++)
				if (map.get(order.get(x)) != null)
					System.out.println(map.get(order.get(x)));
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
