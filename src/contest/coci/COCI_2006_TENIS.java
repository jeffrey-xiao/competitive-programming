package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_TENIS {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String n1 = next();
		String n2 = next();
		int t = readInt();
		main : for (int x = 0; x < t; x++) {
			String[] match = readLine().split(" ");
			int gamesA = 0;
			int gamesB = 0;
			if (match.length > 3 || match.length == 1) {
				System.out.println("ne");
				continue;
			}
			for (int y = 0; y < match.length; y++) {
				String[] scores = match[y].split(":");
				int a = Integer.parseInt(scores[0]);
				int b = Integer.parseInt(scores[1]);
				if (a >= 6
						&& (a >= b + 2 && y == 2 || (a >= b + 2 && b <= 5 && y != 2)))
					gamesA++;
				else if (b >= 6
						&& (b >= a + 2 && y == 2 || (b >= a + 2 && a <= 5 && y != 2)))
					gamesB++;
				else if (a >= b + 1 && b == 6 && y != 2)
					gamesA++;
				else if (b >= a + 1 && a == 6 && y != 2)
					gamesB++;
				else {
					System.out.println("ne");
					continue main;
				}
			}
			if (n1.equals("federer") && gamesB != 0 || n2.equals("federer")
					&& gamesA != 0) {
				System.out.println("ne");
			} else if (gamesA == gamesB || Math.abs(gamesA - gamesB) >= 3) {
				System.out.println("ne");
			} else {
				System.out.println("da");
			}
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
