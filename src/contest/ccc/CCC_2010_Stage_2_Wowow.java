package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Wowow {

	static BufferedReader br;
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static HashMap<Integer, Integer> fromRating = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> fromId = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> toId = new HashMap<Integer, Integer>();
	static int[] bit;
	static int count;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader(new File("C:/Documents and Settings/Jeffrey/workspace/Contest_4/src/ccc/test.txt")));
		int n = readInt();
		Command[] c = new Command[n];
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<Integer> rating = new ArrayList<Integer>();
		for (int x = 0; x < n; x++) {
			char com = next().charAt(0);
			if (com == 'N' || com == 'M') {
				int a = readInt();
				int b = readInt();
				id.add(a);
				rating.add(b);
				c[x] = new Command(com, a, b);
			} else {
				int a = readInt();
				c[x] = new Command(com, a);
			}
		}
		Collections.sort(id);
		Collections.sort(rating);
		count = 1;
		for (Integer i : id) {
			fromId.put(i, count);
			toId.put(count++, i);
		}
		int[] idToRating = new int[count];
		count = 1;
		for (Integer i : rating) {
			fromRating.put(i, count++);
		}
		int[] ratingToId = new int[count];
		bit = new int[count];
		int totalPeople = 0;
		for (int x = 0; x < n; x++) {
			Command curr = c[x];
			if (curr.c == 'N') {
				idToRating[fromId.get(curr.a)] = fromRating.get(curr.b);
				ratingToId[fromRating.get(curr.b)] = fromId.get(curr.a);
				update(idToRating[fromId.get(curr.a)], 1);
				totalPeople++;
			} else if (curr.c == 'M') {
				int currIdIndex = fromId.get(curr.a);
				update(idToRating[currIdIndex], -1);
				idToRating[currIdIndex] = fromRating.get(curr.b);
				ratingToId[fromRating.get(curr.b)] = currIdIndex;
				update(idToRating[currIdIndex], 1);
			} else if (curr.c == 'Q') {
				int lo = 1;
				int hi = count;
				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					if (freqTo(mid) > totalPeople - curr.a) {
						hi = mid - 1;
					} else
						lo = mid + 1;
				}
				int idIndex = ratingToId[lo];
				System.out.println(toId.get(idIndex));
			}
		}
	}

	private static void update (int idxx, int value) {
		for (int x = idxx; x <= count + 1; x += (x & -x))
			bit[x] += value;
	}

	private static int freqTo (int idxx) {
		int sum = 0;
		for (int x = idxx; x > 0; x -= (x & -x))
			sum += bit[x];
		return sum;
	}

	static class Command {
		Character c;
		Integer a, b;

		Command (Character c, Integer a) {
			this.c = c;
			this.a = a;
		}

		Command (Character c, Integer a, Integer b) {
			this.c = c;
			this.a = a;
			this.b = b;
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
