package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static char[][] words;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
		words = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			words[i] = readLine().toCharArray();
		}
		int cost = 0;
		int ways = 1;
		for (int j = 0; j < M; j++) {
			HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
			int wildcard = 0;
			int max = 0;
			for (int i = 0; i < N; i++) {
				if (words[i][j] == '?')
					wildcard++;
				else {
					int curr = 0;
					if (hm.get(words[i][j]) != null)
						curr = hm.get(words[i][j]);
					hm.put(words[i][j], curr + 1);
					max = Math.max(max, curr + 1);
				}
			}
			int maxCnt = 0;
			for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
				if (entry.getValue() == max)
					maxCnt++;
			}
			cost += N - max - wildcard;
			if (wildcard != N) {
				ways = (ways * maxCnt)%1000;
			} else {
				ways = (ways * 26) % 1000;
			}
		}
		out.printf("%d %d\n", cost, ways);
		
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

