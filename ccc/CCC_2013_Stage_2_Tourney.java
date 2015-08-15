package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class CCC_2013_Stage_2_Tourney {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintStream ps = new PrintStream(System.out);
	static Player[] b;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int size = (int) Math.pow(2, n + 1);
		b = new Player[size];
		int m = readInt();
		for (int x = size / 2; x < size; x++) {
			b[x] = new Player(x - size / 2 + 1, readInt());
		}

		for (int x = size / 2 - 1; x >= 1; x--) {
			if (b[x * 2].skill > b[x * 2 + 1].skill)
				b[x] = new Player(b[x * 2].id, b[x * 2].skill);
			else
				b[x] = new Player(b[x * 2 + 1].id, b[x * 2 + 1].skill);
		}
		for (int x = 0; x < m; x++) {
			String c = readLine();
			StringTokenizer next = new StringTokenizer(c);
			String command = next.nextToken();
			if (command.equals("W"))
				ps.println(b[1].id);
			else if (command.equals("S")) {
				int id = Integer.parseInt(next.nextToken());
				int i = (id - 1 + size / 2) / 2;
				int count = 0;
				while (i != 0 && b[i].id == id) {
					count++;
					i /= 2;
				}
				ps.println(count);
			} else if (command.equals("R")) {
				int id = Integer.parseInt(next.nextToken());
				int index = id - 1 + size / 2;
				int skill = Integer.parseInt(next.nextToken());
				b[index] = new Player(id, skill);
				update(index / 2);
			}
		}
	}

	private static void update (int x) {
		if (b[x * 2].skill > b[x * 2 + 1].skill)
			b[x] = new Player(b[x * 2].id, b[x * 2].skill);
		else
			b[x] = new Player(b[x * 2 + 1].id, b[x * 2 + 1].skill);
		if (x == 1)
			return;
		update(x / 2);
	}

	static class Player {
		int id, skill;

		Player (int id, int skill) {
			this.id = id;
			this.skill = skill;
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
