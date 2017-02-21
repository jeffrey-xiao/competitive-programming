package contest.hackercup;
import java.util.*;
import java.io.*;

public class FHC_2016_Round_1_Yachtzee {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static long a, b;
	static long[] c;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
	
		int T = readInt();
		for (int t = 1; t <= T; t++) {
			n = readInt();
			a = readLong();
			b = readLong();
		
			c = new long[n];
			for (int i = 0; i < n; i++)
				c[i] = readLong();
			
			double ans = sum(b) - sum(a);
			out.printf("Case #%d: %.10f\n", t, ans / (b - a));
		}
		
		out.close();
	}
	
	static double sum (long end) {
		long sum = 0;
		double res = 0;
		double curr = 0;
		outer : while (true) {
			curr = 0;
			for (int i = 0; i < n; i++) {
				// [l, r)
				long l = sum;
				long r = sum + c[i]; 
				sum += c[i];
				if (l >= end) {
					res += curr;
					break outer;
				}
				curr += (Math.min(r, end) - l) * (Math.min(r, end) - l) / 2.0;
			}
			res += curr;
			res += curr * ((end - sum) / sum);
			sum += sum * ((end - sum) / sum);
		}
		return res;
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

