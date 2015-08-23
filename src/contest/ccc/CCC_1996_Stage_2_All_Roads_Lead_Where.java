package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_1996_Stage_2_All_Roads_Lead_Where {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> adjlist;

	public static void main (String[] args) throws IOException {
		adjlist = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < 26; x++)
			adjlist.add(new ArrayList<Integer>());
		int n = readInt();
		int m = readInt();
		for (int x = 0; x < n; x++) {
			int a = next().charAt(0) - 65;
			int b = next().charAt(0) - 65;
			adjlist.get(a).add(b);
			adjlist.get(b).add(a);
		}
		for (int x = 0; x < m; x++) {
			int a = next().charAt(0) - 65;
			int b = next().charAt(0) - 65;
			System.out.println(new StringBuilder(bfs(a, b)).reverse()
					.toString());
		}

	}

	private static String bfs (int a, int b) {
		boolean visited[] = new boolean[26];
		int[] prev = new int[26];
		prev[a] = -1;
		Queue<Integer> moves = new LinkedList<Integer>();
		moves.add(a);
		visited[a] = true;
		while (!moves.isEmpty()) {
			int curr = moves.poll();
			if (curr == b)
				break;
			for (int x = 0; x < adjlist.get(curr).size(); x++) {
				int next = adjlist.get(curr).get(x);
				if (visited[next])
					continue;
				visited[next] = true;
				moves.offer(next);
				prev[next] = curr;
			}
		}
		int curr = b;
		String s = "";
		while (curr != -1) {
			s += (char) (curr + 65);
			curr = prev[curr];
		}
		return s;
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
