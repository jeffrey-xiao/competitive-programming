package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2012_Stage_2_Mhocskain_Languages {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int v = readInt();
		int t = readInt();
		ArrayList<Character> vs = new ArrayList<Character>();
		ArrayList<Character> ts = new ArrayList<Character>();
		ArrayList<ArrayList<Character>> mapV = new ArrayList<ArrayList<Character>>();
		ArrayList<ArrayList<char[]>> mapT = new ArrayList<ArrayList<char[]>>();
		for (int i = 0; i < 26; i++) {
			mapV.add(new ArrayList<Character>());
			mapT.add(new ArrayList<char[]>());
		}
		for (int i = 0; i < v; i++)
			vs.add(readCharacter());
		for (int i = 0; i < t; i++)
			ts.add(readCharacter());
		int r1 = readInt();
		for (int i = 0; i < r1; i++) {
			mapV.get(readCharacter() - 'A').add(readCharacter());
		}
		int r2 = readInt();
		for (int i = 0; i < r2; i++) {
			mapT.get(readCharacter() - 'A').add(new char[] {readCharacter(), readCharacter()});
		}
		int w = readInt();
		for (int q = 0; q < w; q++) {
			char[] str = next().toCharArray();
			boolean[][][] dp = new boolean[26][35][35];
			for (int i = 0; i < str.length; i++)
				for (char c : vs)
					for (char p : mapV.get(c - 'A'))
						if (str[i] == p) {
							dp[c - 'A'][1][i] = true;
						}
			for (int len = 2; len <= str.length; len++) {
				for (int i = 0; i < str.length; i++) {
					for (char c : vs) {
						for (char[] p : mapT.get(c - 'A')) {
							for (int j = i + 1; j < i + len; j++) {
								if (dp[p[0] - 'A'][j - i][i] && dp[p[1] - 'A'][(i + len) - j][j]) {
									dp[c - 'A'][len][i] = true;
								}
							}
						}
					}
				}
			}
			if (dp[vs.get(0) - 'A'][str.length][0])
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

	static class Pair {
		char a, b;
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
