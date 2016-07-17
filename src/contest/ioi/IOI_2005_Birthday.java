package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2005_Birthday {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] index1, index2;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		index1 = new int[N];
		index2 = new int[N];
		
		for (int i = 0; i < N; i++) {
			int val = readInt();
			index1[val - 1] = i;
			index2[val - 1] = N - i - 1;
		}
		
		out.println(Math.min(compute(index1), compute(index2)));
		
		out.close();
	}
	
	static int compute (int[] index) {
		TreeSet<Integer> peaks = new TreeSet<Integer>();
		int max = N / 2;
		for (int i = 0; i < N; i++) {
			int curr = Math.min(Math.abs(i - index[i]), Math.abs(N + i - index[i]));
			if (N % 2 == 0) {
				if ((i + curr) % N == index[i]) {
					int p = (max - curr + N) % N;
					peaks.add(p);
				} 
				if ((i - curr + N) % N == index[i]) {
					int p = ((N - max + curr) + N) % N;
					peaks.add(p);
				}
			} else {
				if ((i + curr) % N == index[i]) {
					int p = (max - curr + N) % N;
					peaks.add(p);
					peaks.add((p + 1) % N);
				} 
				if ((i - curr + N) % N == index[i]) {
					int p = ((N - max + curr) + N) % N;
					peaks.add(p);
					peaks.add((p - 1 + N) % N);
				}
			}
			
		}
		
		int prev = 0;
		int ret = 1 << 30;
		
		peaks.add(peaks.first() + N);
		
		for (int p : peaks) {
			ret = Math.min(ret, max - (p - prev) / 2);
			prev = p;
		}		
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

