package contest.hackerrank;

import java.util.*;
import java.io.*;

public class University_Codesprint_2_G {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int G, N, M, S;
	static int ans;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		G = readInt();

		for (int g = 0; g < G; g++) {
			N = readInt();
			M = readInt();
			S = readInt();

			ans = 1 << 30;

			//getArrangements(0, new int[N - 1], S, S);
			
			int[] val = new int[N - 1];
			for (int m = 1; m <= S / (N - 1); m++) { 
				for (int i = 0; i < N - 1; i++) {
					val = new int[N - 1];
					int left = S;
					for (int j = 0; j < i; j++) {
						val[j] = m;
						left -= m;
					}
					for (int j = 0; j < N - 1 - i; j++) {
						val[j + i] = left / (N - 1 - i);
						if (left % (N - 1 - i) > j) {
							val[j + i]++;
						}
					}

					PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
					int currAns = 0;
					for (int j = 0; j < N - 1; j++)
						for (int k = j + 1; k < N - 1; k++)
							pq.offer(Math.max(val[k], val[j]));
					for (int j = 0; j < M - (N - 1); j++)
						currAns += pq.poll();
					for (int j = 0; j < N - 1; j++)
						currAns += val[j];
					ans = Math.min(ans, currAns);
				}
			}
			out.println(ans);
		}

		out.close();
	}

	static void getArrangements (int n, int[] val, int curr, int left) {
		if (n == N - 1 && left == 0) {
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			int currAns = 0;
			for (int i = 0; i < N - 1; i++)
				for (int j = i + 1; j < N - 1; j++)
					pq.offer(Math.max(val[i], val[j]));
			for (int i = 0; i < M - (N - 1); i++)
				currAns += pq.poll();
			for (int i = 0; i < N - 1; i++)
				currAns += val[i];
			ans = Math.min(ans, currAns);
			return;
		}
		if (left == 0 || n == N - 1)
			return;
		for (int i = 1; i <= Math.min(left, curr); i++) {
			val[n] = i;
			getArrangements(n + 1, val, i, left - i);
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
