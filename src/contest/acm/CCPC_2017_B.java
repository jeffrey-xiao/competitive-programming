package contest.acm;

import java.util.*;
import java.io.*;

public class CCPC_2017_B {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MAX_SIZE = 10;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int T = readInt();
		
		main : for (int t = 1; t <= T; t++) {
			String input = readLine();
			for (int i = MAX_SIZE; i >= 1; i--) {
				ArrayList<HashMap<Integer, Integer>> curr = new ArrayList<HashMap<Integer, Integer>>();
				int[] sz = new int[i];
				for (int j = 0; j <= i; j++)
					curr.add(new HashMap<Integer, Integer>());
				int[] value = new int[input.length() + 1];
				for (int j = 0; j + i <= input.length(); j++) {
					value[j] = toValue(input.substring(j, j + i));
					insert(curr.get(j % (i)), value[j]);
					sz[j % (i)]++;
					if (sz[j % (i)] > (1 << i)) {
						sz[j % (i)]--;
						remove(curr.get(j % (i)), value[j - (1 << i) * i]);
					}
					if (sz[j % (i)] == (1 << i)) {
						if (curr.get(j % (i)).size() == (1 << i)) {
							out.println(i + " " + (j - (1 << i) * i + i));
							continue main;
						}
					}
				}
			}
		}
		
		out.close();
	}

	static void insert (HashMap<Integer, Integer> hs, int val) {
		if (!hs.containsKey(val))
			hs.put(val, 1);
		else
			hs.put(val, hs.get(val) + 1);
	}
	
	static void remove (HashMap<Integer, Integer> hs, int val) {
		if (hs.get(val) == 1)
			hs.remove(val);
		else
			hs.put(val, hs.get(val) - 1);
	}
	
	static int toValue (String str) {
		int ret = 0;
		for (int i = 0; i < str.length(); i++)
			ret = ret << 1 | (str.charAt(i) == 'H' ? 1 : 0);
		return ret;
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

