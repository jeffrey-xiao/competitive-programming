package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2014_PIRAMIDA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static long n;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//ps = new PrintWriter(new FileWriter("in.txt"));

		n = readLong();

		char[] input = (" " + next()).toCharArray();
		int N = input.length - 1;
		int q = readInt();
		Query[] qs = new Query[q];
		for (int i = 0; i < q; i++) {
			qs[i] = new Query(readLong(), readCharacter(), i);
		}
		Arrays.sort(qs);
		char prevChar = ' ';
		long[] prefix = new long[N + 2];
		long[] suffix = new long[N + 2];
		long[] ans = new long[q];
		for (int i = 0; i < q; i++) {
			if (qs[i].c != prevChar) {
				for (int j = 1; j <= N; j++) {
					prefix[j] = prefix[j - 1] + (input[j] == qs[i].c ? 1 : 0);
				}

				for (int j = N; j >= 1; j--) {
					suffix[j] = suffix[j + 1] + (input[j] == qs[i].c ? 1 : 0);

				}
				for (int j = 1; j <= N; j++) {
					// System.out.print(suffix[j] + " " );
				}
				// System.out.println();
				prevChar = qs[i].c;
			}
			long l = qs[i].line;
			long diff = l - 1;
			int left = 0, right = 0;
			if (l % 2 == 0) {
				left = (int) ((((l - 1) % N) * (l / 2 % N)) % N + 1);
				right = (int) ((((l + 1) % N) * (l / 2 % N) - 1) % N + 1);
			} else {
				left = (int) ((((l - 1) / 2 % N) * (l % N)) % N + 1);
				right = (int) ((((l + 1) / 2 % N) * (l % N) - 1) % N + 1);
			}

			// System.out.println(hi - lo + " " + diff);
			// System.out.println(left + " " + right + " " + left1 + " " +
			// right1 + " " + N + " " + l);
			// if (hi - lo != diff || left != left1 || right != right1)
			// break;
			long num = ((diff + 1 + N - 1)) / N;
			if (left > right)
				num++;
			// System.out.println(prefix[N] + " " + prefix[left -1] + " " +
			// suffix[right+1]);
			long res = num * prefix[N] - prefix[left - 1] - suffix[right + 1];
			// System.out.println(res);
			ans[qs[i].index] = res;
		}

		for (int i = 0; i < q; i++) {
			ps.println(ans[i]);
		}
		ps.close();
	}

	static class Query implements Comparable<Query> {
		long line;
		char c;
		int index;

		Query (long line, char c, int index) {
			this.line = line;
			this.c = c;
			this.index = index;
		}

		@Override
		public int compareTo (Query o) {
			return c - o.c;
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