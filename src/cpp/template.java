import java.util.*;
import java.io.*;

public class Sample {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;
	
	public static void main (String[] args) throws IOException {
		
		// USE THIS FOR STANDARD INPUT AND OUTPUT
		// br = new BufferedReader(new InputStreamReader(System.in));
		// ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		// USE THIS FOR FILE INPUT AND OUTPUT
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");
	
	
		// TO READ USE THE AUXILIARY METHODS FOR WHAT DATA TYPE YOU'RE READING
		// TO OUTPUT USE ps.print() or ps.println()
	
		ps.close();
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