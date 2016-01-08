package contest.dmoj;

import java.util.*;
import java.io.*;

public class Mackenzie_Rocks {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int SIZE = 10000;

	static int[] bit = new int[SIZE + 1];

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int q = readInt();
		int sz = 1;
		HashMap<String, Integer> toPos = new HashMap<String, Integer>();
		for (int i = 0; i < q; i++) {
			char c = readCharacter();
			if (c == 'A') {
				String rock = next();
				if (toPos.containsKey(rock))
					out.println("Can't add " + rock);
				else {
					toPos.put(rock, sz);
					update(sz, valueOf(rock));
					sz++;
				}
			} else if (c == 'S') {
				String rock1 = next();
				String rock2 = next();
				int pos1 = toPos.get(rock1);
				int pos2 = toPos.get(rock2);

				update(pos1, -valueOf(rock1));
				update(pos2, -valueOf(rock2));


				update(pos1, valueOf(rock2));
				update(pos2, valueOf(rock1));

				toPos.put(rock1, pos2);
				toPos.put(rock2, pos1);
			} else if (c == 'M') {
				String rock1 = next();
				String rock2 = next();	
				int pos1 = toPos.get(rock1);
				int pos2 = toPos.get(rock2);
				out.println(queryTo(Math.max(pos1, pos2)) - queryTo(Math.min(pos1, pos2) - 1));
			} else if (c == 'R') {
				String rock1 = next();
				String rock2 = next();
				int pos = toPos.get(rock1);
				toPos.remove(rock1);
				toPos.put(rock2, pos);

				update(pos, -valueOf(rock1));
				update(pos, valueOf(rock2));
			} else if (c == 'N')
				out.println(sz - 1);
		}

		out.close();
	}

	static int valueOf (String rock) {
		int sum = 0;
		for (int i = 0; i < rock.length(); i++)
			sum += rock.charAt(i) - 'a' + 1;
		return sum;
	}

	static int queryTo (int x) {
		int sum = 0;
		for (int i = x; i > 0; i -= (i & -i))
			sum += bit[i];
		return sum;
	}

	static int queryAt (int x) {
		return queryTo(x) - queryTo(x - 1);
	}

	static void update (int x, int val) {
		for (int i = x; i <= SIZE; i += (i & -i))
			bit[i] += val;
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

