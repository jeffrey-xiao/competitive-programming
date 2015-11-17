package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Glenforest_Stalactites {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int N;
	static long[][][] bit;

	public static void main (String[] args) throws IOException {
		N = readInt();
		bit = new long[N + 1][N + 1][N + 1];
		int Q = readInt();
		long res = 0;
		for (int i = 0; i < Q; i++) {
			char c = readCharacter();
			if (c == 'C') {
				int x = readInt();
				int y = readInt();
				int z = readInt();
				int len = readInt();
				update(x, y, z, len - query(x - 1, y - 1, z - 1, x, y, z));
			} else {
				int x1 = readInt() - 1;
				int y1 = readInt() - 1;
				int z1 = readInt() - 1;
				int x2 = readInt();
				int y2 = readInt();
				int z2 = readInt();
				res += query(x1, y1, z1, x2, y2, z2);
			}
		}
		System.out.println(res);
	}

	static long query (int x1, int y1, int z1, int x2, int y2, int z2) {
		return +query(x2, y2, z2) - query(x1, y2, z2) - query(x2, y1, z2) - query(x2, y2, z1) + query(x1, y1, z2) + query(x2, y1, z1) + query(x1, y2, z1) - query(x1, y1, z1);
	}

	static void update (int indx, int indy, int indz, long value) {
		for (int x = indx; x <= N; x += (x & -x)) {
			for (int y = indy; y <= N; y += (y & -y)) {
				for (int z = indz; z <= N; z += (z & -z)) {
					bit[x][y][z] += value;
				}
			}
		}
	}

	static long query (int indx, int indy, int indz) {
		long sum = 0;
		for (int x = indx; x > 0; x -= (x & -x)) {
			for (int y = indy; y > 0; y -= (y & -y)) {
				for (int z = indz; z > 0; z -= (z & -z)) {
					sum += bit[x][y][z];
				}
			}
		}
		return sum;
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
