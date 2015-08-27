package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Glenforest_Tic_Tac_Moe {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		char[][] g = new char[3][3];
		for (int i = 0; i < 3; i++)
			g[i] = next().toCharArray();
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		hm.put('X', 0);
		hm.put('O', 0);
		if (g[0][0] == g[0][1] && g[0][1] == g[0][2]) {
			hm.put(g[0][0], hm.get(g[0][0]) + 1);
		}
		if (g[1][0] == g[1][1] && g[1][1] == g[1][2]) {
			hm.put(g[1][0], hm.get(g[1][0]) + 1);
		}
		if (g[2][0] == g[2][1] && g[2][1] == g[2][2]) {
			hm.put(g[2][0], hm.get(g[2][0]) + 1);
		}
		if (g[0][0] == g[1][0] && g[1][0] == g[2][0]) {
			hm.put(g[0][0], hm.get(g[0][0]) + 1);
		}
		if (g[0][1] == g[1][1] && g[1][1] == g[2][1]) {
			hm.put(g[0][1], hm.get(g[0][1]) + 1);
		}
		if (g[0][2] == g[1][2] && g[1][2] == g[2][2]) {
			hm.put(g[0][2], hm.get(g[0][2]) + 1);
		}
		if (g[0][0] == g[1][1] && g[1][1] == g[2][2]) {
			hm.put(g[0][0], hm.get(g[0][0]) + 1);
		}
		if (g[0][2] == g[1][1] && g[1][1] == g[2][0]) {
			hm.put(g[0][2], hm.get(g[0][2]) + 1);
		}
		if (hm.get('O') > 0 && hm.get('X') > 0)
			System.out.println("Error, redo");
		else if (hm.get('O') == 0 && hm.get('X') == 0)
			System.out.println("Tie");
		else
			System.out.println(hm.get('O') != 0 ? "Griffy" : "Timothy");
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
