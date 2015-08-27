package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class DMOPC_2013_Phone_Microwave {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		Calendar c = Calendar.getInstance();
		// TimeZone.setDefault(TimeZone.getTimeZone("Vietnam"));
		int i = readInt();
		// System.out.println(i);
		String[] in1 = next().split("/");
		String[] in2 = next().split(":");
		c.set(i(in1[0]), i(in1[1]) - 1, i(in1[2]), i(in2[0]), i(in2[1]), i(in2[2]));
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// System.out.println(df.format(c.getTime()));
		// c.add(Calendar.HOUR_OF_DAY, -5);
		int j = 0;
		for (; j + 24 < i; j += 24) {
			c.add(Calendar.HOUR_OF_DAY, -24);

		}
		// System.out.println(j);
		c.set(Calendar.HOUR_OF_DAY, i(in2[0]));
		// System.out.println(df.format(c.getTime()));
		c.add(Calendar.HOUR_OF_DAY, -(i - j));
		System.out.println(df.format(c.getTime()));

	}

	static int i (String s) {
		return Integer.parseInt(s);
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
