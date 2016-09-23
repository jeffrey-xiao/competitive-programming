package contest.bloomberg;

import java.util.*;
import java.io.*;

public class P5 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static PriorityQueue<Integer> pq;
	static ArrayList<Event> e;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		e = new ArrayList<Event>();
		
		for (int i = 0; i < N; i++) {
			String[] next = next().split("\\|");
			e.add(new Event(Integer.parseInt(next[1]), Integer.parseInt(next[2])));
		}
		
		Collections.sort(e);
		
		int lo = 1;
		int hi = N;
		
		while (lo <= hi) {
			int mid = (hi + lo) >> 1;
			if (can(mid))
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		out.println(lo);
		out.close();
	}

	static boolean can (int sz) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < sz; i++)
			pq.offer(0);
		
		for (Event event : e) {
			if (pq.peek() > event.start)
				return false;
			pq.poll();
			pq.offer(event.end);
		}
		return true;
	}
	
	static class Event implements Comparable<Event> {
		int start, end;
		Event (int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo (Event e) {
			return end - e.end;
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

