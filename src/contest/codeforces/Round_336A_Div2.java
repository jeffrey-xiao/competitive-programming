package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_336A_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int s = readInt();

		Person[] p = new Person[n];
		for (int i = 0; i < n; i++)
			p[i] = new Person(readInt(), readInt());
		Arrays.sort(p);
		int currTime = 0;
		for (int i = 0; i < n; i++) {
			currTime += s - p[i].floor;
			s = p[i].floor;
			currTime = Math.max(currTime, p[i].time);
		}
		currTime += s;
		out.println(currTime);

		out.close();
	}

	static class Person implements Comparable<Person> {
		int floor, time;

		Person (int floor, int time) {
			this.floor = floor;
			this.time = time;
		}

		@Override
		public int compareTo (Person o) {
			if (o.floor == floor)
				return time - o.time;
			return o.floor - floor;
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
