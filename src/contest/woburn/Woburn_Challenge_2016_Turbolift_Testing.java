package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_Turbolift_Testing {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, Q;
	static long[][] max, min, net;
	static long[] maxAns, minAns, netAns;
	static long[] sum;
	static int[] buttonIndex;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		Q = readInt();

		max = new long[N][];
		min = new long[N][];
		net = new long[N][];
		maxAns = new long[M + 1];
		minAns = new long[M + 1];
		netAns = new long[M + 1];
		sum = new long[M + 1];
		buttonIndex = new int[M + 1];
		for (int i = 0; i < N; i++) {
			char[] seq = readLine().toCharArray();
			max[i] = new long[seq.length + 1];
			min[i] = new long[seq.length + 1];
			net[i] = new long[seq.length + 1];
			for (int j = 1; j <= seq.length; j++) {
				net[i][j] = net[i][j - 1] + (seq[j - 1] == 'U' ? 1 : -1);
				max[i][j] = Math.max(max[i][j - 1], net[i][j]);
				min[i][j] = Math.min(min[i][j - 1], net[i][j]);
			}
		}

		for (int i = 1; i <= M; i++) {
			int button = readInt() - 1;
			buttonIndex[i] = button;
			netAns[i] = netAns[i - 1] + net[button][net[button].length - 1];
			maxAns[i] = Math.max(maxAns[i - 1], netAns[i - 1] + max[button][max[button].length - 1]);
			minAns[i] = Math.min(minAns[i - 1], netAns[i - 1] + min[button][min[button].length - 1]);
			sum[i] = sum[i - 1] + net[button].length - 1;
		}

		for (int i = 0; i < Q; i++) {
			long buttonPresses = readLong();
			int lo = 1;
			int hi = M;
			while (lo <= hi) {
				int mid = lo + ((hi - lo) >> 1);
				if (sum[mid] <= buttonPresses)
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			int pressesRemaining = (int)(buttonPresses - sum[hi]);
			long currMin = minAns[hi];
			long currMax = maxAns[hi];
			if (pressesRemaining > 0) {
				currMin = Math.min(currMin, netAns[hi] + min[buttonIndex[hi + 1]][pressesRemaining]);
				currMax = Math.max(currMax, netAns[hi] + max[buttonIndex[hi + 1]][pressesRemaining]);
			}
			out.printf("%d %d\n", currMin, currMax);
		}

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
