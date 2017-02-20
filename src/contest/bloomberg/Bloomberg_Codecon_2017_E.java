package contest.bloomberg;

import java.util.*;
import java.io.*;

public class Bloomberg_Codecon_2017_E {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
	static int[] hours;
	static int ans = 0;
	static int[] time;
	static ArrayList<ArrayList<Integer>> places = new ArrayList<ArrayList<Integer>>();
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		hours = new int[N];
		
		for (int i = 0; i < N; i++) {
			String place = next();
			toIndex.put(place, i);
			hours[i] = readInt();
		}
		
		
		M = readInt();
		for (int i = 0; i < M; i++)
			places.add(new ArrayList<Integer>());
		time = new int[M];
		for (int i = 0; i < M; i++) {
			int K = readInt();
			next();
			time[i] = readInt();
			for (int k = 0; k < K; k++)
				places.get(i).add(toIndex.get(next()));
		}
		compute(0);
		out.println(ans);
		out.close();
	}

	static void compute (int i) {
		if (i == M) {
			ans++;
			return;
		}
		for (int j : places.get(i)) {
			if (hours[j] >= time[i]) {
				hours[j] -= time[i];
				compute(i + 1);
				hours[j] += time[i];
			}
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

