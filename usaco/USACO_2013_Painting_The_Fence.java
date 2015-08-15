package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class USACO_2013_Painting_The_Fence {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int curr = 0;
		ArrayList<Event> events = new ArrayList<Event>();
		for (int z = 0; z < n; z++) {
			int x1 = curr;
			int move = readInt();
			char c = next().charAt(0);
			if (c == 'L')
				move = -move;
			curr += move;
			int x2 = curr;
			events.add(new Event(Math.min(x1, x2), 1));
			events.add(new Event(Math.max(x1, x2), -1));
		}
		curr = Integer.MIN_VALUE;
		int total = 0;
		Collections.sort(events);
		int count = 0;
		int tiles = 0;
		while (count < events.size()) {
			if (events.get(count).x > curr) {
				curr = events.get(count).x;
				total += events.get(count).value;
				count++;
			}
			while (count < events.size() && events.get(count).x == curr) {
				curr = events.get(count).x;
				total += events.get(count).value;
				count++;
			}
			if (total >= 2 && count < events.size())
				tiles += events.get(count).x - curr;
		}
		System.out.println(tiles);
	}

	static class Event implements Comparable<Event> {
		int x;
		int value;

		Event (int x, int value) {
			this.x = x;
			this.value = value;
		}

		@Override
		public int compareTo (Event o) {
			return x - o.x;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
