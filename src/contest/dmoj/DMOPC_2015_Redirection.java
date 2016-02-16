package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Redirection {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		int[] time = new int[N];
		
		for (int i = 0; i < N; i++)
			time[i] = readLine().length();
		
		int M = readInt();
		int[] centers = new int[M];
		for (int i = 0; i < N; i++) {
			int minIndex = 0;
			for (int j = 1; j < M; j++)
				if (centers[minIndex] > centers[j])
					minIndex = j;
			out.println(minIndex + 1);
			centers[minIndex] += time[i];
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

