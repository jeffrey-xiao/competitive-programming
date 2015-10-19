package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2000_Stage_2_Ransom_Note {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static StringBuilder in;
	static int searchPos = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		String search = readLine();
		in = new StringBuilder("");
		while (br.ready()) 
			in.append(br.readLine() + "  ");
		ArrayList<String> clips = new ArrayList<String>();
		for (int i = 0; i < search.length(); ) {
			while (search.charAt(i) == ' ')
				i++;
			int lo = i+1;
			int hi = search.length();
			while (lo <= hi) {
				int mid = (hi + lo) / 2;
				if (search(search.substring(i, mid)))
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			clips.add(in.substring(searchPos, hi - i + searchPos));
			i = hi;
		}
		out.println(clips.size());
		for (String s : clips)
			out.println(s);
		
		out.close();
	}
	static boolean equal (Character c1, Character c2) {
		return Character.toLowerCase(c1) == Character.toLowerCase(c2);
	}
	static int[] buildLCP (String s) {
		int[] prefix = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			int j = prefix[i-1];
			while (j > 0 && !equal(s.charAt(j), s.charAt(i)))
				j = prefix[j-1];
			if (equal(s.charAt(j), s.charAt(i)))
				j++;
			prefix[i] = j;
		}
		return prefix;
	}
	static boolean search (String s) {
		int j = 0;
		int[] prefix = buildLCP(s);
		for (int i = 0; i < in.length(); i++) {
			while (j > 0 && !equal(s.charAt(j), in.charAt(i)))
				j = prefix[j-1];
			if (equal(s.charAt(j), in.charAt(i)))
				j++;
			if (j == s.length()) {
				searchPos = i - s.length() + 1; 
				return true;
			}
		}
		return false;
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

