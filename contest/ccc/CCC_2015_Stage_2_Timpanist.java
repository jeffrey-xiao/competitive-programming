package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2015_Stage_2_Timpanist {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int n, m;
	static int[] note, time;
	static ArrayList<ArrayList<Integer>> states = new ArrayList<ArrayList<Integer>>();
	static int ns;
	static double[][] dp;
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		m = readInt();
		n = readInt();
		note = new int[m];
		time = new int[m];
		for (int i = 0; i < m; i++) {
			time[i] = readInt();
			note[i] = readInt();
		}
		computeStates(0, new ArrayList<Integer>());
		ns = states.size();
		dp = new double[states.size()][m];
		for (int i = 0; i < ns; i++) {
			for (int j = 0; j < m; j++)
				dp[i][j] = -1;
		}
		double ans = 0;
		for (int i = 0; i < ns; i++)
			ans = Math.max(ans, compute(i, 0));
		System.out.printf("%.2f\n", ans == 1 << 30 ? 0 : ans);
		pr.close();
	}
	private static double compute (int state, int i) {
		if (dp[state][i] != -1)
			return dp[state][i];
		double res = 0;
		boolean validState = false;
		for (int j : states.get(state))
			if (j == note[i])
				validState = true;
		if (!validState)
			return dp[state][i] = 0;
		if (i == m-1)
			return dp[state][i] = 1 << 30;
		for (int j = 0; j < ns; j++) {
			double cnt = 0.0;
			for (int k = 0; k < n; k++)
				if (states.get(state).get(k) != states.get(j).get(k))
					cnt++;
			if (cnt == 0)
				res = Math.max(res, compute(j, i+1));
			else {
				res = Math.max(res, Math.min((time[i+1] - time[i])/cnt, compute(j, i+1)));
			}
		}
		return dp[state][i] = res;
	}
	private static void computeStates (int i, ArrayList<Integer> a) {
		if (i == n) {
			for (int j = 1; j < n; j++)
				if (a.get(j) <= a.get(j-1))
					return;
			ArrayList<Integer> add = new ArrayList<Integer>();
			add.addAll(a);
			states.add(add);
		} else {
			for (int j = 1; j <= 12; j++) {
				a.add(j);
				computeStates(i+1, a);
				a.remove(i);
			}
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

