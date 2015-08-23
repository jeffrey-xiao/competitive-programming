package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_STROJOPIS {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		String[] s = {"`1QAZ", "2WSX", "3EDC", "4RFV5TGB", "6YHN7UJM", "8IK,",
				"9OL.", "0P;/-['=]"};
		char[] in = next().toCharArray();
		int[] cnt = new int[8];
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < 8; j++) {
				if (s[j].indexOf((char) in[i]) != -1)
					cnt[j]++;
			}
		}
		for (int i = 0; i < 8; i++)
			ps.println(cnt[i]);
		ps.close();
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