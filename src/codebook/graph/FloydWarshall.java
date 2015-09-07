package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FloydWarshall {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, q;
	static int[][] adj;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		q = readInt();
		
		adj = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				adj[i][j] = 1 << 29;
			adj[i][i] = 0;
		}
		
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj[a][b] = c;
		}
		
		
		for (int k = 0; k < n; k++) 
			for (int i = 0; i < n; i++) 
				for (int j = 0; j < n; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
		
		for (int i = 0; i < q; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int res = adj[a][b];
			out.println(res == 1 << 29 ? -1 : res);
		}
		
		out.close();
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
