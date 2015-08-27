package contest.coci;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2009_OGRADA {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int x = readInt();
		int[] left = new int[n];
		int[] in = new int[n];
		for (int i = 0; i < n; i++) {
			in[i] = readInt();
			left[i] = -1;
		}
		int[] q = new int[1000001];
		int l = 0, r = 0;
		// computing min for left
		for (int i = 0; i < n; i++) {
			if (r > l && q[l] <= i - x)
				l++;
			while (r > l && in[i] <= in[q[r - 1]])
				r--;
			q[r++] = i;
			if (i >= x - 1) {
				left[i] = in[q[l]];
			}
			// System.out.println(left[i]);
		}
		long ans = 0;
		int cnt = 0;
		int lastUsed = 1 << 30;
		l = 0;
		r = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (r > l && q[l] >= i + x)
				l++;
			while (r > l && left[i] >= left[q[r - 1]])
				r--;
			q[r++] = i;
			ans += in[i] - left[q[l]];
			if (lastUsed - i >= x || (left[lastUsed] < in[i] && left[lastUsed] < left[q[l]])) {
				lastUsed = q[l];
				cnt++;
			}
		}
		System.out.println(ans);
		System.out.println(cnt);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
