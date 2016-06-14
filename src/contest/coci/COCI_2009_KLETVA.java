package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2009_KLETVA {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int W, L, N;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		W = readInt();
		L = readInt();
		N = readInt();
		
		ArrayList<ArrayList<Integer>> sorted = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < N; i++) {
			int[] a = new int[2 * L]; // top first
			int[] b = new int[2 * L]; // top first
			int[] c = new int[2 * L]; // bot first
			int[] d = new int[2 * L]; // bot first
			
			for (int j = 0; j < L; j++) {
				int h = readInt();
				a[j] = h;
				b[j + L] = h;
				c[L - j - 1] = h;
				d[L + L - j - 1] = h;
			}
			
			for (int j = 0; j < L; j++) {
				int h = readInt();
				a[j + L] = h;
				b[j] = h;
				c[L + L - j - 1] = h;
				d[L - j - 1] = h;
			}

			int[] min = compute(a);
			min = min(min, compute(b));
			min = min(min, compute(c));
			min = min(min, compute(d));
			
			ArrayList<Integer> ret = new ArrayList<Integer>();
			for (int j = 0; j < 2 * L; j++)
				ret.add(min[j]);
			
			sorted.add(ret);
		}
		
		final ListComparator C = new ListComparator();
		Collections.sort(sorted, C);
		
		int ans = 1;
		for (int i = 1; i < sorted.size(); i++)
			if (C.compare(sorted.get(i - 1), sorted.get(i)) == -1)
				ans++;
		
		out.println(ans);
		out.close();
	}

	static int[] compute (int[] a) {
		int[] top = new int[2 * L];
		int[] bot = new int[2 * L];
		
		int topMin = 1 << 30;
		int botMin = 1 << 30;
		
		for (int i = 0; i < L; i++) {
			topMin = Math.min(a[i], topMin);
			botMin = Math.min(a[i + L], botMin);
		}
		
		for (int i = 0; i < L; i++) {
			top[i] = a[i] - topMin; top[i + L] = a[i + L] + topMin;
			bot[i] = a[i] + botMin; bot[i + L] = a[i + L] - botMin;
		}
		
		return min(top, bot);
	}
	
	static class ListComparator implements Comparator<ArrayList<Integer>> {
		@Override
		public int compare (ArrayList<Integer> a, ArrayList<Integer> b) {
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i) < b.get(i))
					return -1;
				else if (a.get(i) > b.get(i))
					return 1;
			}
			return 0;
		}
	}
	
	static int[] min (int[] a, int[] b) {
		for (int i = 0; i < 2 * L; i++) {
			if (a[i] < b[i])
				return a;
			else if (a[i] > b[i])
				return b;
		}
		return a;
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

