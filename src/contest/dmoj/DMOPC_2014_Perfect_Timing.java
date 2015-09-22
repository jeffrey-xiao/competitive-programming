package contest.dmoj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class DMOPC_2014_Perfect_Timing {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException, ParseException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int x1 = readInt();
		int y1 = readInt();
		int x2 = readInt();
		int y2 = readInt();
		SimpleDateFormat date = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		date.format(date.parse(readLine()));
		date.getCalendar().add(Calendar.SECOND, Math.abs(x1 - x2) + Math.abs(y1 - y2));
		out.println(date.format(date.getCalendar().getTime()));
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

