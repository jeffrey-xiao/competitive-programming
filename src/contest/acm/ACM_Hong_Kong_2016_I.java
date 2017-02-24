package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_Hong_Kong_2016_I {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final double EPS = 1e-9;

	static int N, M, s, t;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			int a = readInt();
			int b = readInt();
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		s = readInt();
		t = readInt();

		double[][] matrix = new double[N * N][N * N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				matrix[getIndex(i, j)][getIndex(i, j)] = 1;
				if (i == j) {
				} else {
					double total = adj.get(i).size() * adj.get(j).size();
					if (adj.get(i).size() * adj.get(j).size() == 0)
						matrix[getIndex(i, j)][getIndex(i, j)] = 0;
					matrix[getIndex(i, j)][N * N] = 1;
					for (int ni : adj.get(i))
						for (int nj : adj.get(j)) {
							matrix[getIndex(i, j)][getIndex(ni, nj)] = -1.0 / total;
						}
				}
			}
		}

		double[][] sol = solve(matrix);

		int index = getIndex(s, t);
		boolean valid = sol[index][index] == 1;
		for (int i = 0; i < N * N; i++) {
			if (i == index)
				continue;
			if (Math.abs(sol[index][i]) > EPS)
				valid = false;
		}

		if (!valid)
			out.println("never meet");
		else
			out.println(sol[getIndex(s, t)][N * N]);

		out.close();
	}

	public static double[][] solve (double[][] A) {
		int M = A.length;
		int N = A[0].length - 1;

		if (N > M)
			return null;

		// forward elimination
		for (int i = 0; i < N; i++) {
			// finding the max value in column i and swapping that row to index i
			int maxIndex = i;
			for (int j = i + 1; j < M; j++)
				if (Math.abs(A[j][i]) > Math.abs(A[maxIndex][i]))
					maxIndex = j;

			if (Math.abs(A[maxIndex][i]) < EPS) {
				continue;
			}

			double[] tempA = A[i];
			A[i] = A[maxIndex];
			A[maxIndex] = tempA;

			// ensuring that it's positive
			if (A[i][i] < 0) {
				for (int j = i; j <= N; j++)
					A[i][j] *= -1;
			}

			// eliminating zeroes in rows bigger than i
			for (int j = i + 1; j < N; j++) {
				if (A[i][i] == 0)
					continue;
				double factor = A[j][i] / A[i][i];
				for (int k = i; k <= N; k++)
					A[j][k] -= A[i][k] * factor;
			}
		}

		// backward elimination
		for (int i = N - 1; i >= 0; i--) {
			if (Math.abs(A[i][i]) < EPS)
				continue;

			// eliminating zeroes in rows smaller than i
			for (int j = i - 1; j >= 0; j--) {
				double factor = A[j][i] / A[i][i];
				for (int k = i; k <= N; k++)
					A[j][k] -= A[i][k] * factor;
			}
		}

		// dividing to create leading ones
		for (int i = 0; i < N; i++) {
			if (Math.abs(A[i][i]) < EPS)
				continue;
			A[i][N] /= A[i][i];
			A[i][i] /= A[i][i];
		}

		return A;
	}

	static int compare (double[] a, double[] b, int i) {
		for (int j = 0; j < a.length; j++) {
			if (a[j] > b[j])
				return 1;
			else if (a[j] < b[j])
				return -1;
		}
		return 0;
	}

	static int getIndex (int i, int j) {
		return i * N + j;
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
