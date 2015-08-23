package contest.other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UTS_Open_P2_Solution {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;
	static int[] map1, map2;

	public static void main (String[] args) throws IOException {
		String c = "2";
		// letters, cases, length
		UTS_Open_P2_Generation.main(new String[] {"3", "5", "5", c});
		br = new BufferedReader(new FileReader("UTS - P2 - " + c + ".in"));
		ps = new PrintWriter("UTS - P2 - " + c + ".out");
		// br = new BufferedReader(new InputStreamReader(System.in));
		// ps = new PrintWriter(new OutputStreamWriter(System.out));
		int n = readInt();
		map1 = new int[n];
		map2 = new int[n];
		int[] map = new int[n];
		boolean[] alreadyMapped = new boolean[n];
		for (int x = 0; x < n; x++) {
			map1[x] = readCharacter() - 'a';
			map2[x] = readCharacter() - 'a';
		}
		int q = readInt();
		main : for (int x = 0; x < q; x++) {
			char[] in = next().toCharArray();
			char[] out = next().toCharArray();
			for (int y = 0; y < n; y++) {
				map[y] = -1;
				alreadyMapped[y] = false;
			}
			for (int y = 0; y < in.length; y++) {
				if (map[in[y] - 'a'] == -1) {
					if ((out[y] - 'a' != map1[in[y] - 'a'] && out[y] - 'a' != map2[in[y] - 'a'])
							|| alreadyMapped[out[y] - 'a']) {
						ps.println("NO");
						continue main;
					}
					map[in[y] - 'a'] = out[y] - 'a';
					alreadyMapped[out[y] - 'a'] = true;
				} else {
					if (map[in[y] - 'a'] != out[y] - 'a') {
						ps.println("NO");
						continue main;
					}
				}
			}
			if (!checkOthers(0, n, alreadyMapped, map)) {
				ps.println("NO");
			} else {
				ps.println("YES");
			}
		}
		ps.close();
	}

	private static boolean checkOthers (int i, int n, boolean[] alreadyMapped,
			int[] map) {
		if (i == n)
			return true;
		if (map[i] == -1) {
			boolean res = false;
			if (!alreadyMapped[map1[i]]) {
				alreadyMapped[map1[i]] = true;
				res |= checkOthers(i + 1, n, alreadyMapped, map);
				alreadyMapped[map1[i]] = false;
			}
			if (!alreadyMapped[map2[i]]) {
				alreadyMapped[map2[i]] = true;
				res |= checkOthers(i + 1, n, alreadyMapped, map);
				alreadyMapped[map2[i]] = false;
			}
			return res;
		}
		return checkOthers(i + 1, n, alreadyMapped, map);
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
