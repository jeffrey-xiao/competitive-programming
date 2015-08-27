package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2001_J3_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

	public static void main (String[] args) throws IOException {
		String input = next();
		ArrayList<ArrayList<Character>> hands = new ArrayList<ArrayList<Character>>();
		for (int x = 0; x < 4; x++)
			hands.add(new ArrayList<Character>());
		int count = 0;
		for (int x = 0; x < input.length(); x++) {
			char curr = input.charAt(x);
			if (curr == 'C')
				count = 0;
			else if (curr == 'D')
				count = 1;
			else if (curr == 'H')
				count = 2;
			else if (curr == 'S')
				count = 3;
			else
				hands.get(count).add(curr);
		}
		System.out.println("Cards Dealt Points");
		int total = 0;
		for (int x = 0; x < 4; x++) {
			System.out.print(suits[x] + " ");
			int points = 0;
			int size = hands.get(x).size();
			for (int y = 0; y < size; y++) {
				char curr = hands.get(x).get(y);
				System.out.print(curr + " ");
				if (curr == 'A')
					points += 4;
				else if (curr == 'K')
					points += 3;
				else if (curr == 'Q')
					points += 2;
				else if (curr == 'J')
					points += 1;
			}
			if (size == 0)
				points += 3;
			else if (size == 1)
				points += 2;
			else if (size == 2)
				points += 1;
			System.out.println(points);
			total += points;
		}
		System.out.println("Total " + total);
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
