package contest.dmoj;

import java.util.*;
import java.io.*;

public class April_Fool_Piggy {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		out.println("                                             )\\   /|");
		out.println("                                          ,-/',|_/ |");
		out.println("                       __            __,-' (   / \\/          ");
		out.println("                   .-'\"  \"'-,,__,-'\"\"          -o.`-._   ");
		out.println("                  /                                   '/");
		out.println("          *--,_ ./                                 _.-- ");
		out.println("                |                              _,-' ");
		out.println("                :                           .-/   ");
		out.println("                 \\                       )_ /");
		out.println("                  \\                _)   / \\(");
		out.println("                    `.   /-.___.---'(  /   \\\\");
		out.println("                     (  /   \\\\       \\(     L\\");
		out.println("                      \\(     L\\       \\\\");
		out.println("                       \\\\              \\\\");
		out.println("                        L\\              L\\");
		out.println("^^");

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