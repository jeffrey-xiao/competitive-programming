package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Good_Strategy_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int N = readInt();
		int M = readInt();
		TreeMap<Integer, TreeSet<Integer>> buckets = new TreeMap<Integer, TreeSet<Integer>>();
		buckets.put(0, new TreeSet<Integer>());
		int[] diff = new int[N];
		for (int x = 0; x < N; x++) {
			buckets.get(0).add(x);
		}
		for (int x = 0; x < M; x++) {
			int team = readInt();
			int problem = readInt() - 1;
			// System.out.println(problems.size());
			if (team == 1 && diff[problem] != -1) {
				buckets.get(diff[problem]).remove(problem);
				if (buckets.get(diff[problem]).size() == 0) {
					buckets.remove(diff[problem]);
				}
				diff[problem] = -1;
			} else if (team == 2 && diff[problem] != -1) {
				// System.out.println("ASD " + x + " " + problem);

				buckets.get(diff[problem]).remove(problem);
				if (buckets.get(diff[problem]).size() == 0) {
					buckets.remove(diff[problem]);
				}

				diff[problem]++;

				if (buckets.get(diff[problem]) == null) {
					buckets.put(diff[problem], new TreeSet<Integer>());
				}
				buckets.get(diff[problem]).add(problem);
			}
			/*
			 * for(int z = 0; z < diff.length; z++) System.out.print(diff[z] +
			 * " "); System.out.println();
			 */
			// System.out.println("SIZE: " + buckets.size());
			if (buckets.size() == 0)
				System.out.println("Make noise");
			else {
				// System.out.println(buckets.get(0).size());
				System.out.println(buckets.lastEntry().getValue().first() + 1
						+ " " + (1 + buckets.firstEntry().getValue().last()));
			}
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
