package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_Stage_2_Splitting_Hares {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static Point[] p, sorted;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		
		p = new Point[N];
		sorted = new Point[N];
		
		for (int i = 0; i < N; i++) {
			int x = readInt(), y = readInt(), w = readInt();
			p[i] = new Point(x, y, w, i);
			sorted[i] = new Point(x, y, w, i);
		}
		
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			final Point curr = p[i];
			
			Arrays.sort(sorted, new Comparator<Point> () {
				@Override
				public int compare (Point p1, Point p2) {
					Double a1 = Math.atan2(p1.y - curr.y, p1.x - curr.x);
					Double a2 = Math.atan2(p2.y - curr.y, p2.x - curr.x);
					return a1.compareTo(a2);
				}
			});
			
			Deque<Integer> plane1 = new ArrayDeque<Integer>();
			Deque<Integer> plane2 = new ArrayDeque<Integer>();
			
			int sum1 = 0;
			int sum2 = 0;
			
			boolean init = false;
//			out.println("SORTING " + curr);
			for (int j = 0; j < N; j++) {
				if (sorted[j].index == curr.index)
					continue;
				if (!init) {
					init = true;
					for (int k = 0; k < N; k++) {
						if (sorted[(k + j) % N].index == curr.index || (k + j) % N == j)
							continue;
						
						long ccw = ccw(curr, sorted[j], sorted[(k + j) % N]);
						
						if (ccw > 0) { // left turn
							plane1.addLast((k + j) % N);
							sum1 += sorted[(k + j) % N].w;
						} else if (ccw < 0) { // right turn
							plane2.addLast((k + j) % N);
							sum2 += sorted[(k + j) % N].w;
						}
					}
				} else {
					int prev = j - 1;
					if (sorted[prev].index == curr.index)
						prev--;
					
					plane2.addLast(prev);
					sum2 += sorted[prev].w;
					
					if (plane1.isEmpty()) {
						int planeRemove = plane2.removeFirst();
						plane1.addLast(planeRemove);
						sum1 += sorted[planeRemove].w;
						sum2 -= sorted[planeRemove].w;
					}
					
					int remove = plane1.removeFirst();
					sum1 -= sorted[remove].w;
					
					while (!plane2.isEmpty() && ccw(curr, sorted[remove], sorted[plane2.getFirst()]) > 0) {
						int planeRemove = plane2.removeFirst();
						plane1.addLast(planeRemove);
						sum1 += sorted[planeRemove].w;
						sum2 -= sorted[planeRemove].w;
					}
				}
				
				int val = Math.max(sum1, sum2) + Math.max(0, curr.w) + Math.max(0, sorted[j].w);
				ans = Math.max(ans, val);
				
//				out.println("DONE SWEEP ITERATION " + sorted[j] + " " + val + " " + (sum1 + " " + sum2));
//				for (int index : plane1)
//					out.print(sorted[index] + " ");
//				out.println();
//				for (int index : plane2)
//					out.print(sorted[index] + " ");
//				out.println();
			}
			
		}
		out.println(ans);
		out.close();
	}

	static class Point {
		long x, y;
		int w, index;
		Point (long x, long y, int w, int index) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.index = index;
		}
		
		@Override
		public String toString () {
			return String.format("(%d)", w);
		}
	}
	
	static long cross (long x0, long y0, long x1, long y1) {
		return x0 * y1 - x1 * y0;
	}
	
	static long ccw (Point p1, Point p2, Point p3) {
		return cross(p2.x - p1.x, p2.y - p1.y, p3.x - p1.x, p3.y - p1.y); 
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

