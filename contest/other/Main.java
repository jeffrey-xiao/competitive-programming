package contest.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	ArrayList<ArrayList<Integer>> sets;
	ArrayList<Integer> leaves;

	public static void main (String[] args) {
		System.out.println(new Main().differentTime(new int[] {0, 1},
				new int[] {1, 2}, new int[] {10, 1}));
	}

	public int differentTime (int[] a, int[] b, int[] len) {
		for (int x = 0; x <= a.length; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < a.length; x++) {
			adj.get(a[x]).add(new Edge(b[x], (double) len[x]));
			adj.get(b[x]).add(new Edge(a[x], (double) len[x]));
		}
		leaves = new ArrayList<Integer>();
		for (int x = 0; x < adj.size(); x++)
			if (adj.get(x).size() == 1)
				leaves.add(x);
		sets = new ArrayList<ArrayList<Integer>>();
		getSet(0, leaves.size(), new ArrayList<Integer>());
		HashSet<Double> times = new HashSet<Double>();
		for (int x = 0; x < sets.size(); x++) {
			boolean[] v = new boolean[a.length + 1];
			Queue<Integer> q = new LinkedList<Integer>();
			for (int y = 0; y < sets.get(x).size(); y++) {
				q.offer(sets.get(x).get(y));
				v[sets.get(x).get(y)] = true;
			}
			double totalTime = 0;
			ArrayList<ArrayList<Edge>> copy = new ArrayList<ArrayList<Edge>>();
			for (int y = 0; y < adj.size(); y++) {
				copy.add(new ArrayList<Edge>());
				for (int z = 0; z < adj.get(y).size(); z++) {
					copy.get(y).add(adj.get(y).get(z));
				}
				Collections.sort(copy.get(y));
			}
			while (!q.isEmpty()) {
				Integer curr = q.poll();
				// System.out.println(curr);
				for (int z = copy.get(curr).size() - 1; z >= 0; z--) {
					Edge next = copy.get(curr).get(z);
					if (v[next.dest]) {
						totalTime -= next.cost / 2;
						copy.get(curr).remove(z);
						int index = copy.get(next.dest).indexOf(curr);
						if (index != -1)
							copy.get(next.dest).remove(index);
						continue;
					}
					v[next.dest] = true;
					copy.get(curr).remove(z);
					// System.out.println(next.dest + " " + curr);
					copy.get(next.dest).remove(
							copy.get(next.dest).indexOf(new Edge(curr, 0.0d)));
					totalTime += next.cost;
					q.offer(next.dest);
				}
			}
			// System.out.println(totalTime);
			times.add(totalTime);
			// System.out.println(sets.get(x));
		}
		return times.size();
	}

	private void getSet (int i, int size, ArrayList<Integer> a) {
		if (i == size) {
			if (a.size() != 0)
				sets.add(new ArrayList<Integer>(a));
			return;
		}
		getSet(i + 1, size, a);
		a.add(leaves.get(i));
		getSet(i + 1, size, a);
		a.remove(leaves.get(i));
	}

	static class Edge implements Comparable<Edge> {
		int dest;
		Double cost;

		Edge (int dest, Double cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Edge o) {
			return o.cost.compareTo(cost);
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Edge) {
				Edge e = (Edge) o;
				return dest == e.dest;
			}
			return false;
		}
	}
}