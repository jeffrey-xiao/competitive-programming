package contest.pegtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Peg_Test_2014_Assembly_Elves_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	@SuppressWarnings ("unchecked")
	static TreeSet<Integer>[][] dp = new TreeSet[101][101];
	static boolean[][] mem = new boolean[101][101];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		TreeSet<Integer> best = compute(n, new TreeSet<Integer>(), 0);
		System.out.println(best.size());
		for (Integer i : best) {
			System.out.print(i + " ");
		}
	}

	private static TreeSet<Integer> compute (int i, TreeSet<Integer> prev, int with) {
		if (mem[i][with])
			return (TreeSet<Integer>) dp[i][with].headSet(i, true);
		
		TreeSet<Integer> curr = new TreeSet<Integer>();
		curr.addAll(prev);
		
		if (i == 1) {
			curr.add(i);
			return curr;
		}
		
		if (i % 2 == 0) {
			curr.add(i);
			curr.addAll(compute(i / 2, curr, with));
			return curr;
		} else {
			int size = Integer.MAX_VALUE;
			prev.addAll(curr);
			prev.add(i);
			TreeSet<Integer> next = new TreeSet<Integer>();
			for (int x = 1; x <= i / 2; x++) {
				next.addAll(prev);
				next.addAll(compute(x, next, 0));
				next.addAll(compute(i - x, next, x));
				
				if (next.size() < size) {
					curr = new TreeSet<Integer>();
					curr.addAll(next);
					size = next.size();
				}
				
				next.clear();
			}
		}
		
		mem[i][with] = true;
		curr.add(i);
		dp[i][with] = curr;
		return curr;
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
