package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: milk2
 */
import java.util.*;
import java.io.*;

public class milk2 {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("milk2.in"));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

		int n = readInt();
		int start = Integer.MAX_VALUE;
		int end = 0;
		int[] diff = new int[1000100];
		for (int x = 0; x < n; x++) {
			int a = readInt();
			int b = readInt();
			start = Math.min(a, start);
			end = Math.max(b, end);
			diff[a]++;
			diff[b]--;
		}
		int maxMilk = 0;
		int maxNoMilk = 0;
		int currMilk = 0;
		int currNoMilk = 0;
		int curr = 0;
		for (int x = start; x < end; x++) {
			diff[x] = (curr += diff[x]);
			if (diff[x] == 0) {
				currMilk = 0;
				currNoMilk++;
			} else {
				currMilk++;
				currNoMilk = 0;
			}
			maxMilk = Math.max(maxMilk, currMilk);
			maxNoMilk = Math.max(maxNoMilk, currNoMilk);
		}
		pr.println(maxMilk + " " + maxNoMilk);
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
