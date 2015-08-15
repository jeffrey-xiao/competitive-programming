package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Glenforest_Friendship_Is_A_Number {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		ArrayList<User> l = new ArrayList<User>();
		for (int i = 1; i <= n; i++) {
			long product = 1;
			for (int qq = readInt(); qq > 0; qq--) {
				product *= readInt();
			}
			l.add(new User(i, product));
		}
		Collections.sort(l);
		for (int i = 0; i < 3; i++)
			System.out.println(l.get(i).id);
	}

	static class User implements Comparable<User> {
		int id;
		Long point;

		User (int id, long point) {
			this.id = id;
			this.point = point;
		}

		@Override
		public int compareTo (User o) {
			return o.point.compareTo(point);
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
