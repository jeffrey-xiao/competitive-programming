package contest.ecoo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ECOO_2016_R1_P2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("DATA21.txt"));

		for (int T = 1; T <= 10; T++) {
			int N = readInt();
			int[] val = new int[N];
			for (int i = 0; i < N; i++) {
				val[i] = readInt();
			}
			HashSet<Integer> hm = new HashSet<Integer>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					hm.add(val[i] * val[j]);
					hm.add(val[i] + val[j]);
				}
			}
			for (int i = 0; i < 5; i++) {
				int v = readInt();
				boolean poss = false;
				for (int j = 0; j < N; j++) {
					if (hm.contains(v - val[j])) {
						poss = true;
					}
					if (v % val[j] == 0 && hm.contains(v / val[j]))
						poss = true;
				}
				out.print(poss ? "T" : "F");
			}
			out.println();
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
