/* Branch and bound concept
 * Reference problem: IOI 1994 Buses
 */

package codebook.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BranchAndBound {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[] freq;
	static int min = 1 << 30;
	static ArrayList<Schedule> minS = new ArrayList<Schedule>();
	static ArrayList<Schedule> curr = new ArrayList<Schedule>();
	static ArrayList<Schedule> candidates = new ArrayList<Schedule>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		freq = new int[60];
		for (int i = 0; i < n; i++)
			freq[readInt()]++;
		for (int i = 0; i <= 59; i++)
			for (int j = i + 1; j <= 60 - i; j++) {
				boolean valid = true;
				for (int k = i; k <= 59; k += j)
					if (freq[k] == 0)
						valid = false;
				if (valid)
					candidates.add(new Schedule(i, j));
			}
		Collections.sort(candidates);
		solve(n);
		out.println(min);
		for (Schedule s : minS)
			out.println(s.start + " " + s.interval);
		out.close();
	}

	static void solve (int i) {
		if (min == 17)
			return;
		if (i == 0) {
			if (curr.size() <= min) {
				min = curr.size();
				minS.clear();
				for (Schedule s : curr)
					minS.add(s);
			}
			return;
		}
		for (Schedule s : candidates) {
			if (s.occ > i)
				continue;
			if ((i + s.occ - 1) / s.occ + curr.size() >= min)
				return;
			boolean valid = true;
			for (int j = s.start; j <= 59; j += s.interval)
				if (freq[j] == 0)
					valid = false;
			if (valid) {
				for (int j = s.start; j <= 59; j += s.interval)
					freq[j]--;

				curr.add(s);
				solve(i - s.occ);
				curr.remove(curr.size() - 1);
				for (int j = s.start; j <= 59; j += s.interval)
					freq[j]++;
			}
		}
	}

	static class Schedule implements Comparable<Schedule> {
		int start, interval, occ;

		Schedule (int start, int interval) {
			this.start = start;
			this.interval = interval;
			this.occ = (59 - start) / interval + 1;
		}

		@Override
		public int compareTo (Schedule s) {
			return s.occ - occ;
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
