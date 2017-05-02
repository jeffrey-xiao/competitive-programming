package contest.codejam;

import java.util.*;
import java.io.*;

public class GCJ_2017_Round_1C_B {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, C, J;
	static int leftC, leftJ;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 1; t <= T; t++) {
			C = readInt();
			J = readInt();
			leftC = 720;
			leftJ = 720;
			
			Activity[] A = new Activity[C + J];
			
			for (int i = 0; i < C; i++) {
				int l = readInt();
				int r = readInt();
				leftC -= r - l;
				A[i] = new Activity(l, r, 0);
			}
			
			for (int i = 0; i < J; i++) {
				int l = readInt();
				int r = readInt();
				leftJ -= r - l;
				A[i + C] = new Activity(l, r, 1);
			}
			
			Arrays.sort(A);
			
			ArrayList<Integer> diffC = new ArrayList<Integer>();
			ArrayList<Integer> diffJ = new ArrayList<Integer>();
			
			int exchanges = 0;
			
			for (int i = 0; i < C + J; i++) {
				int j = (i + 1) % (C + J);
				if (A[i].type != A[j].type) {
					exchanges++;
					continue;
				}
				exchanges += 2;
				if (i < C + J - 1) {
					if (A[i].type == 0)
						diffC.add(A[j].start - A[i].end);
					else
						diffJ.add(A[j].start - A[i].end);
				} else {
					if (A[i].type == 0)
						diffC.add(A[j].start - (A[i].end - 720 * 2));
					else
						diffJ.add(A[j].start - (A[i].end - 720 * 2));
				}
			}
			
			Collections.sort(diffC);
			Collections.sort(diffJ);
			
			for (int i = 0; i < diffC.size(); i++) {
				if (diffC.get(i) <= leftC) {
					exchanges -= 2;
					leftC -= diffC.get(i);
				}
			}
			
			for (int i = 0; i < diffJ.size(); i++) {
				if (diffJ.get(i) <= leftJ) {
					exchanges -= 2;
					leftJ -= diffJ.get(i);
				}
			}
			
			out.printf("Case #%d: %d\n", t, exchanges);
		}
		
		out.close();
	}

	static class Activity implements Comparable<Activity> {
		int start, end, type;
		Activity (int start, int end, int type) {
			this.start = start;
			this.end = end;
			this.type = type;
		}
		@Override
		public int compareTo (Activity a) {
			return start - a.start;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}

