/*
 * Stable marriage algorithm. The men will get their best choice and it can be proven
 * by contradiction.
 * 
 * Time complexity: O(N^2)
 */

package codebook.algorithms;

import java.util.*;
import java.io.*;

public class Stable_Marriage {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[][] menPref, womenPref;
	
	// for the ith woman, what man j is indexed
	static int[][] menToPref;
	static int[] menChoice, womenChoice;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		n = readInt();
		
		// menPref[i] describes the preferences of the ith man
		menPref = new int[n][n];
		
		// womanPref[i] describes the preferences of the ith wpman
		womenPref = new int[n][n];
		menToPref = new int[n][n];
		
		// initially all men will propose to their first preference on their list (index)
		menChoice = new int[n];
		
		// the man they currently choose
		womenChoice = new int[n];
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				menPref[i][j] = readInt();

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				int man = readInt();
				womenPref[i][j] = man;
				menToPref[i][man] = j;
			}
		
		Queue<Integer> menLeft = new ArrayDeque<Integer>();
		for (int i = 0; i < n; i++) {
			menLeft.offer(i);
			womenChoice[i] = -1;
		}
		
		while (!menLeft.isEmpty()) {
			int currMan = menLeft.poll();
			int currWoman = menPref[currMan][menChoice[currMan]++];
			
			if (womenChoice[currWoman] == -1) {
				womenChoice[currWoman] = currMan;
			} else {
				if (menToPref[currWoman][womenChoice[currWoman]] > menToPref[currWoman][currMan]) {
					menLeft.offer(womenChoice[currWoman]);
					womenChoice[currWoman] = currMan;
				} else {
					menLeft.offer(currMan);
				}
			}
		}
		for (int i = 0; i < n; i++)
			out.printf("Woman %d gets married with man %d\n", i, womenChoice[i]);
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

