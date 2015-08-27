package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2007_VECI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[] ch;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		// for(int x = n+1; x < Math.pow(10, (int)Math.log(n)); x++){
		// char[] c1 = Integer.toString(n).toCharArray();
		// char[] c2 = Integer.toString(x).toCharArray();
		// Arrays.sort(c1);
		// Arrays.sort(c2);
		// if(equals(c1, c2)){
		// System.out.println(x);
		// return;
		// }
		// }
		// System.out.println(0);
		ch = Integer.toString(n).toCharArray();
		Arrays.sort(ch);
		if (!permute(ch.length, ch.length, new ArrayList<Integer>()))
			System.out.println(0);
	}

	private static boolean permute (int i, int c, ArrayList<Integer> a) {
		if (c == 0) {
			String s = "";
			for (int x = 0; x < a.size(); x++)
				s += ch[a.get(x)];
			if (Integer.parseInt(s) > n) {
				System.out.println(s);
				return true;
			}
			return false;
		}
		for (int x = 0; x < i; x++) {
			if (!a.contains(x)) {
				a.add(x);
				if (permute(i, c - 1, a)) {
					return true;
				}
				a.remove((Integer) x);
			}
		}
		return false;
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
