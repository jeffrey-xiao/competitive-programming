package contest.bloomberg;

import java.util.*;
import java.io.*;

public class P7 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		int won = 0;
		int total = 0;
		N = readInt();
		ArrayList<State> states = new ArrayList<State>();
		for (int i = 0; i < N; i++) {
			next();
			int us = readInt();
			int them = readInt();
			int undecided = 100 - us - them;
			int delegates = readInt();

			total += delegates;
			if (us > them)
				won += delegates;
			else {
				int useUndecided = Math.min(undecided, them - us + 1);
				int useThem = (them - (us + useUndecided) + 2) / 2;
				if ((them - (us + useUndecided)) % 2 == 0 && useUndecided > 0)
					useUndecided--;
				int cost = useUndecided * 1 + useThem * 3;
				states.add(new State(cost, delegates));
			}
		}
		
		if (won * 2 > total)
			out.println(0);
		else {
			int[] dp = new int[total + 1];
			Arrays.fill(dp, 1 << 30);
			dp[0] = 0;

			for (State s : states)
				for (int i = total; i >= 0; i--)
					if (i >= s.cnt)
						dp[i] = Math.min(dp[i], dp[i - s.cnt] + s.cost);
			
			int ans = 1 << 30;
			for (int i = (total - won * 2 + 2) / 2; i <= total; i++)
				ans = Math.min(ans, dp[i]);
			
			out.println(ans);
		}
		
		out.close();
	}

	static class State {
		int cost, cnt;
		State (int cost, int cnt) {
			this.cost = cost;
			this.cnt = cnt;
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

