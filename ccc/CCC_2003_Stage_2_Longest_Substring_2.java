package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_Longest_Substring_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintStream ps = new PrintStream(System.out);
	static final int MAX = 65536;

	public static void main (String[] args) throws IOException {
		int[] seq = new int[MAX];
		int[] bs = new int[MAX];
		boolean[] nums = new boolean[MAX];

		int s = readInt();
		int first = 0, maxFirst = 0, maxLast = 0;
		int count = 0;
		while (s != 0) {
			if (nums[s]) {
				while (nums[s])
					nums[seq[(first++) % MAX]] = false;
			}
			seq[count % MAX] = s;
			nums[s] = true;

			if (count - first > maxLast - maxFirst) {
				// max = count-first;
				// maxFirst = first;
				// maxLast = count;
				// for(int x = maxFirst; x <= maxLast; x++)
				// bs[x%MAX] = seq[x%MAX];
				if (first > maxLast)
					for (int x = first; x <= count; x++)
						bs[x % MAX] = seq[x % MAX];
				else
					for (int x = maxFirst + 1; x <= count; x++)
						bs[x % MAX] = seq[x % MAX];
				maxLast = count;
				maxFirst = first;
			}
			s = readInt();
			count++;
		}
		for (int x = maxFirst; x <= maxLast; x++)
			ps.println(bs[x % MAX]);
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
