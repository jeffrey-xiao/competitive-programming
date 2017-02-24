package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: ride
 */
import java.io.*;
import java.util.*;

public class ride {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("ride.in"));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		String s1 = next();
		String s2 = next();
		int sum1 = 1;
		int sum2 = 1;
		for (int x = 0; x < s1.length(); x++)
			sum1 = (sum1 * (s1.charAt(x) - 64)) % 47;
		for (int x = 0; x < s2.length(); x++)
			sum2 = (sum2 * (s2.charAt(x) - 64)) % 47;
		if (sum1 == sum2)
			pr.println("GO");
		else
			pr.println("STAY");
		pr.close();
		System.exit(0);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
