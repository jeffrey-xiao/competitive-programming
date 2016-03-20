package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_A_Classic_Problem {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] a = new int[3000001];
	static int[] b = new int[3000001];
	static PriorityQueue<Integer> min = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> max = new PriorityQueue<Integer>(Collections.reverseOrder());

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();

		a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = readInt();
		}

		int ans = 0;

		for (int i = 0, j = 0; i < n; i++) {
			if (i == j) {
				b[a[j]]++;
				min.add(a[j]);
				max.add(a[j]);
				j++;
			}
			while (b[min.peek()] == 0)
				min.poll();

			while (b[max.peek()] == 0)
				max.poll();

			while (j < n && Math.max(max.peek(), a[j]) - Math.min(min.peek(), a[j]) <= k) {
				b[a[j]]++;
				min.add(a[j]);
				max.add(a[j]);
				j++;
			}
			b[a[i]]--;
			ans += j - i;
		}
		out.println(ans);
		out.close();
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