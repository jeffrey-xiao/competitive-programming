package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1999_P1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] cards;

	public static void main (String[] args) throws IOException {
		cards = new int[52];
		for (int x = 0; x < 52; x++) {
			String s = next();
			if (s.equals("ace"))
				cards[x] = 1;
			else if (s.equals("two"))
				cards[x] = 2;
			else if (s.equals("three"))
				cards[x] = 3;
			else if (s.equals("four"))
				cards[x] = 4;
			else if (s.equals("five"))
				cards[x] = 5;
			else if (s.equals("six"))
				cards[x] = 6;
			else if (s.equals("seven"))
				cards[x] = 7;
			else if (s.equals("eight"))
				cards[x] = 8;
			else if (s.equals("nine"))
				cards[x] = 9;
			else if (s.equals("ten"))
				cards[x] = 10;
			else if (s.equals("jack"))
				cards[x] = 11;
			else if (s.equals("queen"))
				cards[x] = 12;
			else if (s.equals("king"))
				cards[x] = 13;
		}
		boolean turnA = true;
		int pointsA = 0;
		int pointsB = 0;
		for (int x = 0; x < 52; x++) {
			int points = 0;
			// System.out.println(cards[x]);
			if (cards[x] == 1)
				points = checkHigh(x, 4) ? 4 : 0;
			else if (cards[x] == 13)
				points = checkHigh(x, 3) ? 3 : 0;
			else if (cards[x] == 12)
				points = checkHigh(x, 2) ? 2 : 0;
			else if (cards[x] == 11)
				points = checkHigh(x, 1) ? 1 : 0;
			if (points > 0) {
				if (turnA) {
					System.out.println("Player A scores " + points
							+ " point(s).");
					pointsA += points;
				} else {
					System.out.println("Player B scores " + points
							+ " point(s).");
					pointsB += points;
				}
			}
			turnA = !turnA;
		}
		System.out.println("Player A: " + pointsA + " point(s).");
		System.out.println("Player B: " + pointsB + " point(s).");
	}

	private static boolean checkHigh (int start, int i) {
		if (start + i >= 52)
			return false;
		for (int x = start + 1; x <= start + i; x++) {
			if (cards[x] == 1 || cards[x] >= 11)
				return false;
		}
		return true;
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
