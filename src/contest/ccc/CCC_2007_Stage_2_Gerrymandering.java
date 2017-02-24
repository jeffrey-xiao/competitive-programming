package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2007_Stage_2_Gerrymandering {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int ridings = readInt();
		int parties = readInt();
		int[][] input = new int[ridings][parties];
		for (int i = 0; i < ridings; i++)
			for (int j = 0; j < parties; j++)
				input[i][j] = readInt();

		// at position i, how many ridings can you win if you merge j times;
		int[][] dp = new int[ridings][ridings];
		for (int i = 0; i < ridings; i++) {
			boolean greater = true;
			for (int j = 1; j < parties; j++)
				if (input[i][0] <= input[i][j])
					greater = false;
			if (greater)
				dp[i][0] = 1;
		}

		for (int i = 1; i < ridings; i++) {
			int[] sum = new int[parties];
			for (int j = i; j >= 0; j--) {
				for (int k = 0; k < parties; k++)
					sum[k] += input[j][k];

				boolean greater = true;
				for (int k = 1; k < parties; k++)
					if (sum[0] <= sum[k])
						greater = false;

				for (int k = 0; k <= j - 1; k++)
					dp[i][i - j + k] = Math.max(dp[i][i - j + k], dp[j - 1][k] + (greater ? 1 : 0));
				if (j == 0)
					dp[i][i] = greater ? 1 : 0;
			}
		}
		int min = 1 << 30;
		for (int i = 0; i < ridings; i++)
			if (dp[ridings - 1][i] >= (ridings - i) / 2 + 1)
				min = Math.min(i, min);
		pr.println(min == 1 << 30 ? -1 : min);
		pr.close();
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
