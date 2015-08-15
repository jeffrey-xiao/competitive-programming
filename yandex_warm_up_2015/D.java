package yandex_warm_up_2015;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		String[] in = readLine().split(" ");
		int[] f = new int[in.length];
		for (int i = 0; i < in.length; i++)
			f[i] = Integer.parseInt(in[i]);
		Arrays.sort(f);
		int max = 1;
		int min = 0;
		int cnt = 1;
		for (int i = 1; i < f.length; i++) {
			if (f[i] != f[i - 1]) {
				min++;
				cnt = 1;
			} else {
				cnt++;
				max = Math.max(cnt, max);
			}
		}
		int exceed = max - (f.length + 1) / 2;
		// System.out.println(max);
		if (exceed > 0)
			max = f.length - exceed * 2 - (f.length % 2 == 1 ? 1 : 0);
		else
			max = f.length - 1;
		pr.println(min + " " + max);
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
