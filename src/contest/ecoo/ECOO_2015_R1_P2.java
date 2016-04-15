package contest.ecoo;
import java.util.*;
import java.io.*;

public class ECOO_2015_R1_P2 {

	static BufferedReader br;
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("DATA21.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int qq = 0; qq < 10; qq++) {
			int n = readInt();
			String[] in = readLine().split(" ");
			int curr = 0;
			String s = "";
			for (int i = 0; i < in.length;) {
				if (curr == 0) {
					while (in[i].length() > n) {
						System.out.println(in[i].substring(0, n));
						in[i] = in[i].substring(n, in[i].length());
					}
					if (in[i].length() != 0) {
						curr += in[i].length();
						s += in[i];
					}
					i++;
				} else {
					if (in[i].length() + 1 + curr > n) {
						System.out.println(s);
						s = "";
						curr = 0;
					} else {
						curr += in[i].length() + 1;
						s += " " + in[i];
						i++;
					}
				}
			}
			if (curr != 0)
				System.out.println(s);
			System.out.println("=====");
		}
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}
