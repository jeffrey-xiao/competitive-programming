package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class R1B_C {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 1; qq <= t; qq++) {
			int n = readInt();
			ArrayList<Hiker> hikers = new ArrayList<Hiker>();
			for (int i = 0; i < n; i++) {
				long d = readInt();
				long h = readInt();
				long m = readInt();
				for (int j = 0; j < h; j++) {
					hikers.add(new Hiker(d, m + j));
				}
			}
			Collections.sort(hikers);
			if (hikers.size() < 2) {
				pr.printf("Case #%d: %d\n", qq, 0);
				continue;
			}
			long timefast = (360 + (360 - hikers.get(0).pos)) * hikers.get(0).speed;
			long timeslow = (360 - hikers.get(1).pos) * hikers.get(1).speed;
			if (timeslow < timefast) {
				pr.printf("Case #%d: %d\n", qq, 0);
			} else
				pr.printf("Case #%d: %d\n", qq, 1);
		}

		pr.close();
	}

	static class Hiker implements Comparable<Hiker> {
		Long pos, speed;

		Hiker (long pos, long speed) {
			this.pos = pos;
			this.speed = speed;
		}

		@Override
		public int compareTo (Hiker o) {
			return speed.compareTo(o.speed);
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
