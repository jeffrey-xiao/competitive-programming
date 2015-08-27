package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2002_J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		switch (readInt()) {
			case 0:
				System.out.println(" * * * \n" + "*     *\n" + "*     *\n" + "*     *\n" + "       \n" + "*     *\n" + "*     *\n" + "*     *\n" + " * * * \n");
				break;
			case 1:
				System.out.println("       \n" + "      *\n" + "      *\n" + "      *\n" + "       \n" + "      *\n" + "      *\n" + "      *\n" + "       \n");
				break;
			case 2:
				System.out.println(" * * * \n" + "      *\n" + "      *\n" + "      *\n" + " * * * \n" + "*      \n" + "*      \n" + "*      \n" + " * * * \n");
				break;
			case 3:
				System.out.println(" * * * \n" + "      *\n" + "      *\n" + "      *\n" + " * * * \n" + "      *\n" + "      *\n" + "      *\n" + " * * * \n");
				break;
			case 4:
				System.out.println("       " + "*     *\n" + "*     *\n" + "*     *\n" + " * * * \n" + "      *\n" + "      *\n" + "      *\n" + "       \n");
				break;
			case 5:
				System.out.println(" * * * \n" + "*      \n" + "*      \n" + "*      \n" + " * * * \n" + "      *\n" + "      *\n" + "      *\n" + " * * * \n");
				break;
			case 6:
				System.out.println(" * * * \n" + "*      \n" + "*      \n" + "*      \n" + " * * * \n" + "*     *\n" + "*     *\n" + "*     *\n" + " * * * \n");
				break;
			case 7:
				System.out.println(" * * * \n" + "      *\n" + "      *\n" + "      *\n" + "       \n" + "      *\n" + "      *\n" + "      *\n" + "       \n");
				break;
			case 8:
				System.out.println(" * * * \n" + "*     *\n" + "*     *\n" + "*     *\n" + " * * * \n" + "*     *\n" + "*     *\n" + "*     *\n" + " * * * \n");
				break;
			case 9:
				System.out.println(" * * * \n" + "*     *\n" + "*     *\n" + "*     *\n" + " * * * \n" + "      *\n" + "      *\n" + "      *\n" + " * * * \n");
		}
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
