package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NAQ_2016_H {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int H, B;
	static int[] h, b;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		H = readInt();
		h = new int[H];
		int sumH = 0;
		for (int i = 0; i < H; i++)
			sumH += h[i] = readInt();
		
		B = readInt();
		int sumB = 0;
		b = new int[B];
		for (int i = 0; i < B; i++)
			sumB += b[i] = readInt();
		
		int[] dpH = new int[sumH + 1];
		int[] dpB = new int[sumB + 1];
		Arrays.fill(dpH, 1 << 30);
		Arrays.fill(dpB, 1 << 30);
		dpH[0] = dpB[0] = 0;
		
		for (int i = 0; i < H; i++)
			for (int j = sumH; j >= 0; j--)
				if (j - h[i] >= 0 && dpH[j - h[i]] != 1 << 30)
					dpH[j] = Math.min(dpH[j], dpH[j - h[i]] + 1);
		
		for (int i = 0; i < B; i++)
			for (int j = sumB; j >= 0; j--)
				if (j - b[i] >= 0 && dpB[j - b[i]] != 1 << 30)
					dpB[j] = Math.min(dpB[j], dpB[j - b[i]] + 1);
		
		int ans = 1 << 30;			
		for (int i = 1; i <= Math.min(sumB, sumH); i++)
			if (dpB[i] != 1 << 30 && dpH[i] != 1 << 30) {
				ans = Math.min(ans, dpB[i] + dpH[i]);
			}
		
		if (ans == 1 << 30)
			out.println("impossible");
		else
			out.println(ans);
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
