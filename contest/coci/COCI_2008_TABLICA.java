package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class COCI_2008_TABLICA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		int count = 0;
		Move[] m = new Move[k];
		int[] X = new int[k];
		int[] Y = new int[k];
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < k; i++) {
			int a = readInt() - 1;
			if (!hm.containsKey(a)) {
				hm.put(a, count);
				X[count] = a / n;
				Y[count] = a % n;
				count++;
			}
			// System.out.println(X[i] + " " + Y[i]);
			m[i] = new Move(a, readInt() - 1, readInt() - 1);
		}
		// System.out.println(count);
		for (int i = 0; i < k; i++) {
			int index = hm.get(m[i].i);
			int diffX = m[i].x - X[index];
			int diffY = m[i].y - Y[index];
			if (diffX < 0)
				diffX += n;
			if (diffY < 0)
				diffY += n;
			int targetX = X[index];
			int targetY = m[i].y;
			for (int j = 0; j < count; j++) {
				if (X[j] == targetX) {
					Y[j] = (Y[j] + diffY + n) % n;
				}
			}
			for (int j = 0; j < count; j++) {
				if (Y[j] == targetY) {
					X[j] = (X[j] + diffX + n) % n;
				}
			}
			System.out.println(Math.abs(diffX) + Math.abs(diffY));
		}
	}

	static class Move {
		int i, x, y;

		Move (int i, int x, int y) {
			this.i = i;
			this.x = x;
			this.y = y;
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
