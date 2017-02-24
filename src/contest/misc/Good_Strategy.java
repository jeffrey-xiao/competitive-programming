package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Good_Strategy {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int N = readInt();
		int M = readInt();
		TreeSet<Problem> problems = new TreeSet<Problem>();
		HashSet<Integer> solvedProblems = new HashSet<Integer>();
		int[] diff = new int[N];
		for (int x = 0; x < N; x++) {
			problems.add(new Problem(x, 0));
		}
		for (int x = 0; x < M; x++) {
			int team = readInt();
			int problem = readInt() - 1;
			if (team == 1) {
				problems.remove(new Problem(problem, diff[problem]));
				solvedProblems.add(problem);
			} else if (team == 2 && !solvedProblems.contains(problem)) {

				problems.remove(new Problem(problem, diff[problem]));
				diff[problem]++;
				Problem s = new Problem(problem, diff[problem]);
				problems.add(s);
			}
			if (problems.size() == 0)
				System.out.println("Make noise");
			else
				System.out.println(problems.first().id + 1 + " " + (problems.last().id + 1));
		}

	}

	@SuppressWarnings("rawtypes")
	static class Problem implements Comparable {
		int id;
		int difficulty;

		Problem (int id, int difficulty) {
			this.id = id;
			this.difficulty = difficulty;
		}

		@Override
		public int compareTo (Object arg) {
			if (arg instanceof Problem) {
				Problem p = (Problem)arg;
				if (p.difficulty == this.difficulty)
					return -p.id + this.id;
				return p.difficulty - this.difficulty;
			}
			return 0;
		}

		@Override
		public boolean equals (Object arg) {
			if (arg instanceof Problem) {
				Problem p = (Problem)arg;
				return p.id == this.id;
			}
			return true;
		}

		@Override
		public String toString () {
			return "" + id;
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
