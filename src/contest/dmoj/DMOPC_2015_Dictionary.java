package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Dictionary {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 26; i++)
			list.add(new ArrayList<String>());
		
		int n = readInt();
		for (int i = 0; i < n; i++) {
			String str = next();
			list.get(str.charAt(0) - 'a').add(str);
		}
		for (int i = 0; i < 26; i++) {
			if (list.get(i).size() == 0)
				continue;
			Collections.sort(list.get(i));
			for (int j = 0; j < list.get(i).size(); j++) {
				out.print(list.get(i).get(j));
				if (j != list.get(i).size() - 1)
					out.print(", ");
			}
			out.println();
		}
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

