package contest.dmoj;

import java.util.*;
import java.io.*;

public class Mackenzie_ECOO {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		
		if (N == 0) {
			out.println("No ECOO :(");
		} else {
			Team[] t = new Team[N];
			
			for (int i = 0; i < N; i++) {
				t[i] = new Team(next(), next().equals("general") ? 1 : 0, readInt());
			}
			
			Arrays.sort(t);
			int generalLeft = 2;
			int girlsLeft = 1;
			
			ArrayList<String> best = new ArrayList<String>();
			
			for (int i = 0; i < N; i++) {
				if (generalLeft > 0) {
					best.add(t[i].name);
					generalLeft--;
				} else if (girlsLeft > 0 && t[i].type == 0) {
					best.add(t[i].name);
					girlsLeft--;
				}
			}
			
			Collections.sort(best);
			for (String s : best)
				out.println(s);
			
		}
		
		out.close();
	}

	static class Team implements Comparable<Team> {
		String name;
		int type, score;
		Team (String name, int type, int score) {
			this.name = name;
			this.type = type;
			this.score = score;
		}
		@Override
		public int compareTo (Team o) {
			return o.score - score;
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

