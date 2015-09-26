package codebook.graph.network;

import java.util.*;
import java.io.*;

public class MinCostMaxMatchingHungarian {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[][] matrix, aux;
	static boolean[] markedRow, markedCol;
	static boolean[] assignedRow, assignedCol;
	static boolean[][] assigned;
	
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader	(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		// precondition: a square matrix where the rows represent workers and the columns represents jobs
		n = readInt();
		matrix = new int[n][n];
		aux = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matrix[i][j] = aux[i][j] = readInt();
		while (true) {
			reduceMatrix(aux);
			markedRow = new boolean[n];
			markedCol = new boolean[n];
			assignedRow = new boolean[n];
			assignedCol = new boolean[n];
			assigned = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (aux[i][j] == 0 && !assignedRow[i] && !assignedCol[j]) {
						assignedRow[i] = true;
						assignedCol[j] = true;
						assigned[i][j] = true;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (!assignedRow[i]) {
					markedRow[i] = true;
					for (int j = 0; j < n; j++) {
						if (aux[i][j] == 0) {
							markedCol[j] = true;
							for (int k = 0; k < n; k++)
								if (assigned[k][j])
									markedRow[k] = true;
						}
					}
				}
			}
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (markedCol[i])
					cnt++;
				if (!markedRow[i])
					cnt++;
			}
			if (cnt == n)
				break;
			int min = 1 << 30;
			for (int i = 0; i < n; i++) 
				for (int j = 0; j < n; j++) 
					if (!markedCol[j] && markedRow[i])
						min = Math.min(aux[i][j], min);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (markedCol[j])
						aux[i][j] += min;
					if (markedRow[i])
						aux[i][j] -= min;
				}
			}
		}
		int res = 0;
		for (int i = 0; i < n; i++) 
			for (int j = 0; j < n; j++) 
				if (assigned[i][j])
					res += matrix[i][j];
		out.println(res);
		out.close();
	}

	static void reduceMatrix (int[][] aux) {
		for (int i = 0; i < n; i++) {
			int min = 1 << 30;
			for (int j = 0; j < n; j++)
				min = Math.min(min, aux[i][j]);
			for (int j = 0; j < n; j++)
				aux[i][j] -= min;
		}
		for (int j = 0; j < n; j++) {
			int min = 1 << 30;
			for (int i = 0; i < n; i++)
				min = Math.min(min, aux[i][j]);
			for (int i = 0; i < n; i++)
				aux[i][j] -= min;
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

