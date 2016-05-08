package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2016_R2_P2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int TEST_CASES = 10;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));

		// br = new BufferedReader(new FileReader("in.txt"));
		br = new BufferedReader(new FileReader("DATA21.txt"));

		for (int t = 1; t <= TEST_CASES; t++) {
			int k = readInt();
			String[] in = readLine().split(" ");
			if (in.length > 1) {

				String append = get(in.length);
				for (int i = 0; i < in.length; i++)
					append += (char)('a' + in[i].length());

				for (int i = 0; i < in.length; i++)
					append += in[i];

				int[] val = new int[append.length()];
				for (int i = append.length() - 1; i >= 0; i--) {
					if (i == append.length() - 1)
						val[i] = append.charAt(i) - 'a';
					else
						val[i] = append.charAt(i) - 'a' + val[i + 1];
				}

				for (int i = 0; i < append.length(); i++) {
					if (i < append.length() - 1)
						out.print((char)(((append.charAt(i) - 'a' + (k + val[i + 1])) % 26) + 'a'));
					else
						out.print((char)(((append.charAt(i) - 'a' + k) % 26) + 'a'));
				}
			} else {
				char[] e = in[0].toCharArray();
				char[] d = new char[e.length];
				
				int sum = 0;
				for (int i = d.length - 1; i >= 0; i--) {
					if (i == d.length - 1)
						d[i] = (char)(((e[i] - 'a' - k) % 26 + 26) % 26 + 'a');
					else
						d[i] = (char)(((e[i] - 'a' - k - sum) % 26 + 26) % 26 + 'a');
					sum += d[i] - 'a';
				}
				int numOfWords = (d[0] - 'a') * 26 + (d[1] - 'a');
				ArrayList<Integer> lengths = new ArrayList<Integer>();
				for (int i = 0; i < numOfWords; i++) {
					lengths.add((d[2 + i] - 'a'));
				}
				sum = 0;
				String res = new String(d);
				for (int i = 0; i < lengths.size(); i++) {
					out.print(res.substring(sum + 2 + numOfWords, sum + 2 + numOfWords + lengths.get(i)) + " ");
					sum += lengths.get(i);
				}
				out.println();
			}
			out.println();
		}

		out.close();
	}

	static String get (int n) {
		int a = n / 26;
		int b = n % 26;
		return "" + (char)('a' + a) + (char)('a' + b);
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

