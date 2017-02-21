package contest.hackercup;
import java.util.*;
import java.io.*;

public class FHC_2015_Round_1_Homework {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int c = 1; c <= t; c++) {
			int a = readInt();
			int b = readInt();
			int k = readInt();
			
			boolean[] primes = new boolean[b + 1];
			int[] count = new int[b + 1];
			
			// true means not prime, false means prime
			primes[1] = true;

			for (int x = 1; x <= b; x++) {
				if (primes[x] == false) {
					for (int y = x * 2; y <= b; y += x) {
						primes[y] = true;
						count[y]++;
					}
					count[x]++;
				}
			}
			
			int total = 0;
			for (int x = a; x <= b; x++)
				if (count[x] == k)
					total++;

			out.printf("Case #%d: %d\n", c, total);
		}
		
		out.close();
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
