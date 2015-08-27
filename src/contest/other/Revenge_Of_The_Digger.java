package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Revenge_Of_The_Digger {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int[] node;
	static ArrayList<ArrayList<Integer>> adjlist;
	static int end;

	public static void main (String[] args) throws IOException {
		n = readInt();
		int m = readInt();
		adjlist = new ArrayList<ArrayList<Integer>>();
		node = new int[n];

		for (int x = 0; x < n; x++) {
			node[x] = readInt();
			if (node[x] == 0) {
				end = x;
				node[x] = 100000;
			}
			adjlist.add(new ArrayList<Integer>());
		}
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adjlist.get(a).add(b);
		}
		int[] augmented = ff(node);
		int total = 0;
		for (int x = 0; x < adjlist.get(0).size(); x++) {
			int next = adjlist.get(0).get(x);
			total += node[next] - augmented[next];
		}
		System.out.println(total);
	}

	static int[] ff (int[] flow) {
		int[] augmented = Arrays.copyOf(flow, flow.length);
		while (bfs(augmented))
			;
		/*
		 * for(int x = 0; x < augmented.length; x++){
		 * System.out.print(augmented[x] + " "); } System.out.println();
		 */
		return augmented;
	}

	static boolean bfs (int[] augmented) {
		int[] prev = new int[n];
		boolean[] visited = new boolean[n];
		prev[0] = -1;
		Queue<Integer> moves = new LinkedList<Integer>();
		moves.add(0);
		while (!moves.isEmpty()) {

			int curr = moves.poll();
			// System.out.println(curr+1 + " " + end);
			if (curr == end)
				break;
			for (int x = 0; x < adjlist.get(curr).size(); x++) {
				int next = adjlist.get(curr).get(x);

				if (visited[next] || augmented[next] == 0)
					continue;
				// System.out.println("NEXT " +(next+1));
				visited[next] = true;
				moves.add(next);
				prev[next] = curr;
			}
		}
		if (!visited[end])
			return false;
		int curr = end;
		int augmentValue = Integer.MAX_VALUE;
		while (curr != -1) {
			augmentValue = Math.min(augmentValue, augmented[curr]);
			curr = prev[curr];
		}
		// System.out.println(augmentValue);
		curr = end;
		;
		while (curr != -1) {
			augmented[curr] -= augmentValue;
			curr = prev[curr];
		}
		return true;
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
