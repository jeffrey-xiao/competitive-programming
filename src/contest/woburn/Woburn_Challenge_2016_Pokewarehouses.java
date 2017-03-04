package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_Pokewarehouses {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, ans;
	static Stack<Integer> s = new Stack<Integer>();
	static int[] val;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		val = new int[N];
		
		for (int i = 0; i < N; i++)
			val[i] = readInt();
		
		int currValue = N;
		int currIndex = 0;
		while (currValue > 0) {
			if (currIndex < N && val[currIndex] == currValue) {
				currValue--;
				currIndex++;
			} else if (!s.isEmpty() && s.peek() == currValue) {
				currValue--;
				s.pop();
			} else if (currIndex < N) {
				ans = 1;
				s.push(val[currIndex++]);
			} else {
				ans = 2;
				break;
			}
		}
		
		out.println(ans);
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
