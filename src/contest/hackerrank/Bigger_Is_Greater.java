package contest.hackerrank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Bigger_Is_Greater {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static char[] str;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int i = 0; i < t; i++) {
			str = next().toCharArray();
			if (nextPermutation())
				System.out.println(new String(str));
			else
				System.out.println("no answer");
		}

		pr.close();
	}

	static boolean nextPermutation () {
		int i = str.length - 1;
		while (i > 0 && str[i - 1] >= str[i])
			i--;
		if (i <= 0)
			return false;
		int j = str.length - 1;
		while (str[j] <= str[i - 1])
			j--;
		char temp = str[i - 1];
		str[i - 1] = str[j];
		str[j] = temp;
		j = str.length - 1;
		while (i < j) {
			temp = str[i];
			str[i] = str[j];
			str[j] = temp;
			i++;
			j--;
		}
		return true;
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
