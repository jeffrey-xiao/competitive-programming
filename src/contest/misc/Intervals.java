package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Intervals {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		int n = readInt();
		int q = readInt();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[q];
		for (int i = 0; i < n; i++) {
			ts.add(a[i] = readInt());
			ts.add(b[i] = readInt());
		}
		for (int i = 0; i < q; i++)
			ts.add(c[i] = readInt());
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int cnt = 0;
		for (int i : ts) {
			hm.put(i, cnt++);
		}
		int[] diff = new int[ts.size() + 1];
		for (int i = 0; i < n; i++) {
			diff[hm.get(a[i])]++;
			diff[hm.get(b[i]) + 1]--;
		}
		cnt = 0;
		for (int i = 0; i <= ts.size(); i++) {
			diff[i] = (cnt += diff[i]);
		}
		for (int i = 0; i < q; i++) {
			pr.println(diff[hm.get(c[i])]);
		}
		pr.close();
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
