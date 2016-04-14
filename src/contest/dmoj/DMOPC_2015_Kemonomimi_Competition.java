package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Kemonomimi_Competition {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] C = new int[4];
	static int[] index, P, S, T;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		for (int i = 0; i < 4; i++)
			C[i] = readInt();
		
		index = new int[N];
		P = new int[N];
		S = new int[N];
		T = new int[N];
		
		int last = 0;
		
		for (int i = 0; i < N; i++) {
			index[i] = readInt() - 1;
			P[i] = readInt();
			S[i] = readInt();
			T[i] = readInt();
			last = Math.max(S[i], last);
		}

		for (int i = 0; i < N; i++) {
			if (P[i] == 10) {
				out.println(0);
			} else if (180 - last >= T[i] * C[index[i]]) {
				out.println(10 - P[i]);
			} else {
				out.println("The kemonomimi are too cute!");
			}
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

