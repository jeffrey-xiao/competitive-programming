package contest.ecoo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ECOO_2016_R1_P1 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("DATA11.txt"));

		for (int T = 1; T <= 10; T++) {

			int wt = readInt();
			int wa = readInt();
			int wp = readInt();
			int wq = readInt();
			
			int N = readInt();
			int ans = 0;
			for (int i = 0; i < N; i++) {
				int t = readInt();
				int a = readInt();
				int p = readInt();
				int q = readInt();
				if (t * wt + a * wa + wp * p + wq * q >= 5000)
					ans++;
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
