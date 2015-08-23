package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2014_NORMA {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static final int MOD = 1000000000;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int[] in = new int[n];
		for (int i = 0; i < n; i++) {
			in[i] = readInt();
		}
		Stack<State> maxS = new Stack<State>();
		Stack<State> minS = new Stack<State>();
		maxS.push(new State(-1, 1 << 30));
		minS.push(new State(-1, -1 << 30));
		int[][] max = new int[n][2];
		int[][] min = new int[n][2];
		for (int i = 0; i < n; i++) {
			while (in[i] >= maxS.peek().value) {
				max[maxS.peek().index][1] = i - 1;
				maxS.pop();
			}
			max[i][0] = maxS.peek().index + 1;
			maxS.push(new State(i, in[i]));

			while (in[i] <= minS.peek().value) {
				min[minS.peek().index][1] = i - 1;
				minS.pop();
			}
			min[i][0] = minS.peek().index + 1;
			minS.push(new State(i, in[i]));
		}
		while (maxS.size() > 1) {
			max[maxS.pop().index][1] = n - 1;
		}
		while (minS.size() > 1) {
			min[minS.pop().index][1] = n - 1;
		}
		for (int i = 0; i < n; i++) {
			System.out.println(max[i][0] + " " + max[i][1]);
		}
		for (int i = 0; i < n; i++) {
			int size = max[i][1] - max[i][0] + 1;
			for (int j = 0; j < size; j++) {
				int left = Math.max(i - j, max[i][0]);
				int right = Math.min(i, max[i][1] - j);
				// ans += min(left, right, j) -> the sum of the ranges of size
				// j+1 starting from left and ending at right
			}
		}
		pr.close();
	}

	static class State {
		int index, value;

		State (int index, int value) {
			this.index = index;
			this.value = value;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
