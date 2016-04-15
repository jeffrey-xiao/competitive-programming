package contest.ecoo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class ECOO_2016_R1_P3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("DATA31.txt"));

		for (int T = 1; T <= 10; T++) {
			int N = readInt();
			ArrayList<Integer> val = new ArrayList<Integer>();
			for (int i = 0; i < N; i++)
				val.add(readInt());
			int prev = 0;
			int curr = 0;
			int ans = 0;
			for (int i = N; i >= 2; i--) {
				for (int j = 0; j < N; j++) {
					if (val.get(j) == i)
						prev = j;
					if (val.get(j) == i - 1)
						curr = j;
				}
				if (curr > prev) {
					ans += curr;
					int add = val.get(curr);
					val.remove(curr);
					val.add(0, add);
				}
			}
			out.println(ans);
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
