package contest.hackerrank;

import java.util.*;
import java.io.*;

public class June_2015_A {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		char[] in = next().toCharArray();
		int[] cnt = new int[26];
		for (int i = 0; i < in.length; i++)
			cnt[in[i] - 'a']++;
		int res = 0;
		HashMap<Integer, Integer> buckets = new HashMap<Integer, Integer>();
		for (int i = 0; i < 26; i++)
			if (cnt[i] > 0) {
				if (!buckets.containsKey(cnt[i]))
					buckets.put(cnt[i], 0);
				buckets.put(cnt[i], buckets.get(cnt[i])+1);
			}
		int min = 1 << 30;
		for (Map.Entry<Integer, Integer> e : buckets.entrySet()) {
			min = Math.min(min, e.getValue());
		}
		if (buckets.size() == 1 || (buckets.size() == 2 && min <= 1))
			System.out.println("YES");
		else
			System.out.println("NO");
		
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

