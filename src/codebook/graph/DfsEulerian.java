package codebook.graph;

import java.util.*;
import java.io.*;

public class DfsEulerian {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int n, m;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		for (int i = 0; i < m; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		out.close();
	}
	static 
	static boolean isEulerianPath () {
		return getEuler() != -1;
	}
	
	static boolean isEulerianCycle () {
		return getEuler() == 0;
	}
	
	static int getEuler () {
		// assuming that all vertices are connected
		int odd = 0;
		for (int i = 0; i < n; i++)
			if ((adj.get(i).size() & 1) > 0)
				odd++;
		if (odd > 2)
			return -1;
		return odd == 0 ? 0 : 1; 
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

