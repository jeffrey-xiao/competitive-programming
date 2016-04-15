package contest.ecoo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ECOO_2016_R1_P4 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int HOUSES = 100;
	static final int TESTCASES = 10;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("DATA42.txt"));

		for (int T = 1; T <= TESTCASES; T++) {
			int X = readInt();
			int Y = readInt();
			HashSet<Point> h = new HashSet<Point>();
			for (int i = 0; i < HOUSES; i++) {
				// R = 0, D = 1
				h.add(new Point(readInt(), readInt(), readCharacter() == 'R' ? 0 : 1));
			}
			int cnt = 0;
			int demo = 0;
			for (int x = X - 50; x <= X + 50; x++) {
				for (int y = Y - 50; y <= Y + 50; y++) {
					if (dist(x, y, X, Y) <= 50*50 && !h.contains(new Point(x, y, 0))) {
						cnt++;
						PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
						for (Point house : h) {
							pq.offer(new Pair(dist(x, y, house.x, house.y), house.type));
						}
						int last = 0;
						int demoCnt = 0;
						int repCnt = 0;
						for (int i = 0; i < 3; i++) {
							Pair curr = pq.poll();
							last = curr.dist;
							if (curr.type == 0)
								repCnt++;
							else
								demoCnt++;
						}
						while (!pq.isEmpty() && pq.peek().dist == last) {
							Pair curr = pq.poll();
							last = curr.dist;
							if (curr.type == 0)
								repCnt++;
							else
								demoCnt++;
						}
						if (demoCnt >= repCnt)
							demo++;
					}
				}
			}
			out.printf("%.1f\n", demo / (double)(cnt) * 100);
		}
		out.close();
	}
		
	static class Pair implements Comparable<Pair> {
		int dist, type;
		Pair (int dist, int type) {
			this.dist = dist;
			this.type = type;
		}
		@Override
		public int compareTo (Pair p) {
			return dist - p.dist;
		}
	}
	
	static class Point {
		int x, y, type;
		Point (int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
		public int hashCode () {
			return 31 * 31 * x + y * 31;
		}
		
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p  = (Point)o;
				return p.x == x && p.y == y;
			}
			return false;
		}
	}

	static int dist (int x1, int y1, int x2, int y2) {
		int dx = x1 - x2;
		int dy = y1 - y2;
		return dx * dx + dy * dy;
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
