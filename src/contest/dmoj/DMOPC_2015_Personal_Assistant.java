package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DMOPC_2015_Personal_Assistant {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		Anime[] a = new Anime[n];
		for (int i = 0; i < n; i++)
			a[i] = new Anime(readLong(), readLong(), readLong());
		TreeMap<Long, Long> states = new TreeMap<Long, Long>();
		long ans = 0;
		for (int i = 0; i < n; i++) {
			Long bestValue = states.floorKey(a[i].start);
			if (bestValue == null) {
				bestValue = 0l;
			} else {
				bestValue = states.get(bestValue);
			}
			Long pos = a[i].start + a[i].end;
			bestValue += a[i].val;
			Long nextState = states.ceilingKey(pos);
			while (nextState != null && states.get(nextState) <= bestValue) {
				states.remove(nextState);
				nextState = states.ceilingKey(pos);
			}
			Long prevState = states.floorKey(pos);
			if (prevState == null || states.get(prevState) < bestValue)
				states.put(pos, bestValue);
			ans = Math.max(ans, bestValue);
		}
		out.println(ans);

		out.close();
	}

	static class Anime {
		long start, end, val;

		Anime (long start, long end, long val) {
			this.start = start;
			this.end = end;
			this.val = val;
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
