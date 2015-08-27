package contest.smac;

import java.util.*;
import java.io.*;

public class SMAC_2008_Infinite_Degrees {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int[] out;
	static int[] label;
	static int[] start;
	static boolean[] v;
	static int cnt = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));
		
		int n = readInt();
		int m = readInt();
		
		label = new int[n];
		out = new int[n];
		start = new int[n];
		v = new boolean[n];
		for (int i = 0; i < n; i++) {
			label[i] = -1;
			out[i] = readInt();
		}
		for (int i = n-1; i >= 0; i--)
			if (!v[i])
				dfs(i);
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			if (start[a] == start[b])
				pr.println(1);
			else if (label[start[a]] == label[start[b]])
				pr.println(2);
			else
				pr.println(0);
		}
		pr.close();
	}
	static void dfs (int i) {
		v[i] = true;
		int j = out[i];
		if (v[j]) {
			if (label[j] == -1)
				label[i] = j;
			else
				label[i] = -2;
		} else {
			dfs(j);
			if (label[j] != -1 && j != label[j])
				label[i] = label[j];
			else
				label[i] = -2;
		}
		if (label[i] == -2)
			start[i] = start[j];
		else
			start[i] = i;
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