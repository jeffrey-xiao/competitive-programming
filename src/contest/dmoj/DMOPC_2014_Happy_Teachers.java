package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Happy_Teachers {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Teacher[] t = new Teacher[n + 1];
		for (int x = 1; x <= n; x++)
			t[x] = new Teacher(readInt(), readInt(), readInt());
		int time = readInt();
		State[][] dp = new State[2][time + 1];
		dp[0][0] = new State(0, 0);
		for (int x = 1; x <= n; x++) {
			for (int y = 0; y <= time; y++)
				dp[x % 2][y] = null;
			for (int y = 0; y <= time; y++) {
				if (dp[(x - 1) % 2][y] != null) {
					if (dp[x % 2][y] != null) {
						if (dp[(x - 1) % 2][y].happy > dp[x % 2][y].happy || (dp[x % 2][y].happy == dp[(x - 1) % 2][y].happy && dp[(x - 1) % 2][y].time < dp[(x) % 2][y].time))
							dp[x % 2][y] = dp[(x - 1) % 2][y];
					} else
						dp[x % 2][y] = dp[(x - 1) % 2][y];
					int sh = dp[(x - 1) % 2][y].happy;
					int st = dp[(x - 1) % 2][y].time;
					int currTime = y + t[x].p;
					int count = 1;
					int nh = sh + t[x].h;
					int nt = st + 1;
					while (currTime <= time) {
						State cs = dp[x % 2][currTime];
						if (cs == null) {
							dp[x % 2][currTime] = new State(nh, nt);
						} else {
							if ((nh > cs.happy) || (nh == cs.happy && nt < cs.time))
								dp[x % 2][currTime] = new State(nh, nt);
						}
						nh += t[x].h - t[x].e * count;
						nt++;
						currTime += t[x].p;

						count++;
					}
				}
			}
			// for(int z = 0; z <= time; z++){
			// if(dp[x%2][z] == null){
			// System.out.print("(-1) ");
			// continue;
			// }
			// System.out.printf("(%d, %d) ",dp[x%2][z].happy, dp[x%2][z].time);
			// }
			// System.out.println();
		}
		int maxHappy = 0;
		int minTime = 0;
		for (int x = 0; x <= time; x++) {
			if (dp[n % 2][x] == null) {
				// System.out.print("(-1) ");
				continue;
			}
			if (dp[n % 2][x].happy > maxHappy) {
				maxHappy = dp[n % 2][x].happy;
				minTime = dp[n % 2][x].time;
			} else if (dp[n % 2][x].happy == maxHappy && dp[n % 2][x].time < minTime)
				minTime = dp[n % 2][x].time;
			// System.out.printf("(%d, %d) ",dp[n%2][x].happy, dp[n%2][x].time);
		}
		System.out.println(maxHappy + "\n" + minTime);
	}

	static class Teacher {
		int h, e, p;

		Teacher (int h, int e, int p) {
			this.h = h;
			this.e = e;
			this.p = p;
		}
	}

	static class State {
		int happy, time;

		State (int happy, int time) {
			this.happy = happy;
			this.time = time;
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
