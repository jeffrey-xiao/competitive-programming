package contest.dmoj;

import java.util.*;
import java.io.*;

public class TLE_Poetry {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] a;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		a = new int[N];

		for (int i = 0; i < N; i++)
			a[i] = readInt();

		String[] words = readLine().split(" ");

		int index = 0;
		int stringIndex = 0;
		StringBuilder buffer = new StringBuilder("");
		for (int i = 0; i < words.length;) {
			if (words[i].length() - stringIndex > a[index % N] && buffer.length() == 0) {
				out.println(words[i].substring(stringIndex, stringIndex + a[index % N]));
				stringIndex += a[index % N];
				index++;
			} else if (words[i].length() - stringIndex + buffer.length() + (buffer.length() == 0 ? 0 : 1) > a[index % N]) {
				out.println(buffer);
				index++;
				buffer = new StringBuilder("");
			} else {
				if (buffer.length() != 0)
					buffer.append(" ");
				buffer.append(words[i].substring(stringIndex));
				stringIndex = 0;
				i++;
			}
		}
		if (buffer.length() != 0)
			out.println(buffer);
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