package ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_1998_Magic_Squares {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int count = 0;
	static final int SIZE = 8;

	static HashMap<Perm, Integer> toIndex = new HashMap<Perm, Integer>();

	public static void main (String[] args) throws IOException {
		Perm p = new Perm();
		p.num[0] = 1;
		p.num[1] = 2;
		p.num[2] = 3;
		p.num[3] = 4;
		p.num[4] = 8;
		p.num[5] = 7;
		p.num[6] = 6;
		p.num[7] = 5;
		generate(0, new Perm(p));

		Perm e = new Perm();
		e.num[0] = readByte();
		e.num[1] = readByte();
		e.num[2] = readByte();
		e.num[3] = readByte();
		e.num[7] = readByte();
		e.num[6] = readByte();
		e.num[5] = readByte();
		e.num[4] = readByte();
		boolean[] v = new boolean[40321];
		Queue<State> q = new LinkedList<State>();
		q.offer(new State(p, ""));
		v[toIndex.get(p)] = true;
		while (!q.isEmpty()) {
			State curr = q.poll();
			// for(int x = 0; x < 8; x++)
			// System.out.print(curr.p.num[x] + " ");
			// System.out.println();
			if (curr.p.equals(e)) {
				System.out.println(curr.s.length());
				for (int x = 0; x < curr.s.length(); x++) {
					System.out.println(curr.s.charAt(x));
				}
				break;
			}
			Perm a = new Perm();
			a.num[0] = curr.p.num[4];
			a.num[1] = curr.p.num[5];
			a.num[2] = curr.p.num[6];
			a.num[3] = curr.p.num[7];
			a.num[4] = curr.p.num[0];
			a.num[5] = curr.p.num[1];
			a.num[6] = curr.p.num[2];
			a.num[7] = curr.p.num[3];
			Perm b = new Perm();
			b.num[0] = curr.p.num[3];
			b.num[1] = curr.p.num[0];
			b.num[2] = curr.p.num[1];
			b.num[3] = curr.p.num[2];
			b.num[4] = curr.p.num[7];
			b.num[5] = curr.p.num[4];
			b.num[6] = curr.p.num[5];
			b.num[7] = curr.p.num[6];
			Perm c = new Perm();
			c.num[0] = curr.p.num[0];
			c.num[1] = curr.p.num[5];
			c.num[2] = curr.p.num[1];
			c.num[3] = curr.p.num[3];
			c.num[4] = curr.p.num[4];
			c.num[5] = curr.p.num[6];
			c.num[6] = curr.p.num[2];
			c.num[7] = curr.p.num[7];
			if (!v[toIndex.get(a)]) {
				q.offer(new State(a, curr.s + "A"));
				v[toIndex.get(a)] = true;
			}
			if (!v[toIndex.get(b)]) {
				q.offer(new State(b, curr.s + "B"));
				v[toIndex.get(b)] = true;
			}
			if (!v[toIndex.get(c)]) {
				q.offer(new State(c, curr.s + "C"));
				v[toIndex.get(c)] = true;
			}
		}
	}

	static class State {
		String s;
		Perm p;

		State (Perm p, String s) {
			this.p = p;
			this.s = s;
		}
	}

	private static void generate (int i, Perm p) {

		if (i == SIZE - 1) {
			// for(int x = 0; x < SIZE; x++)
			// System.out.print(p.num[x] + " ");
			// System.out.println();
			toIndex.put(p, count);
			// System.out.println(count);
			count++;
			return;
		}
		for (int y = i + 1; y < SIZE; y++) {
			swap(p.num, i, y);
			generate(i + 1, new Perm(p));
			swap(p.num, i, y);
		}
		generate(i + 1, new Perm(p));
	}

	private static void swap (byte[] b, int x, int y) {
		byte temp = b[x];
		b[x] = b[y];
		b[y] = temp;
	}

	static class Perm {
		byte[] num = new byte[8];

		Perm () {
		}

		Perm (Perm p) {
			for (int x = 0; x < 8; x++)
				num[x] = p.num[x];
		}

		@Override
		public int hashCode () {
			int res = 0;
			for (int x = 0; x < 8; x++)
				res = (res * 31) + new Byte(num[x]).hashCode();
			return res;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Perm) {
				Perm p = (Perm) o;
				for (int x = 0; x < 8; x++)
					if (num[x] != p.num[x])
						return false;
				return true;
			}
			return false;
		}
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static byte readByte () throws IOException {
		return Byte.parseByte(next());
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
