package contest.woburn;
import java.util.*;
import java.io.*;

public class Woburn_Challenge_2001_Blind_Date{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		while (n != -1) {
			Queue<State> q = new ArrayDeque<State>();
			v.clear();
			q.offer(new State(2*n, 0, 0, 0));
			v.add(new State(2*n, 0, 0, 0));
			boolean valid = false;
			while (!q.isEmpty()) {
				State curr = q.poll();
				if (curr.x == curr.y && curr.z == 0) {
					System.out.println(curr.moves);
					valid = true;
					break;
				}
				int nx = 0; // 2*n
				int ny = 0; // n+k
				int nz = 0; // n-k
				int fill = 0;
				// x -> y
				fill = Math.min(curr.x, n+k - curr.y);
				nx = curr.x - fill;
				ny = curr.y + fill;
				nz = curr.z;
				if (!v.contains(new State(nx, ny, nz, curr.moves+1))) {
					v.add(new State(nx, ny, nz, curr.moves+1));
					q.offer(new State(nx, ny, nz, curr.moves+1));
				}
				// x <- y
				fill = Math.min(curr.y, 2*n - curr.x);
				nx = curr.x + fill;
				ny = curr.y - fill;
				nz = curr.z;
				if (!v.contains(new State(nx, ny, nz, curr.moves+1))) {
					v.add(new State(nx, ny, nz, curr.moves+1));
					q.offer(new State(nx, ny, nz, curr.moves+1));
				}
				// y -> z
				fill = Math.min(curr.y, n-k - curr.z);
				nx = curr.x;
				ny = curr.y - fill;
				nz = curr.z + fill;
				if (!v.contains(new State(nx, ny, nz, curr.moves+1))) {
					v.add(new State(nx, ny, nz, curr.moves+1));
					q.offer(new State(nx, ny, nz, curr.moves+1));
				}
				// y <- z
				fill = Math.min(curr.z, n+k - curr.y);
				nx = curr.x;
				ny = curr.y + fill;
				nz = curr.z - fill;
				if (!v.contains(new State(nx, ny, nz, curr.moves+1))) {
					v.add(new State(nx, ny, nz, curr.moves+1));
					q.offer(new State(nx, ny, nz, curr.moves+1));
				}
				// x -> z
				fill = Math.min(curr.x, n-k - curr.z);
				nx = curr.x - fill;
				ny = curr.y;
				nz = curr.z + fill;
				if (!v.contains(new State(nx, ny, nz, curr.moves+1))) {
					v.add(new State(nx, ny, nz, curr.moves+1));
					q.offer(new State(nx, ny, nz, curr.moves+1));
				}
				// x <- z
				fill = Math.min(curr.z, 2*n - curr.x);
				nx = curr.x + fill;
				ny = curr.y;
				nz = curr.z - fill;
				if (!v.contains(new State(nx, ny, nz, curr.moves+1))) {
					v.add(new State(nx, ny, nz, curr.moves+1));
					q.offer(new State(nx, ny, nz, curr.moves+1));
				}
			}
			if (!valid)
				System.out.println("infinity");
			n = readInt();
			k = readInt();
		}
	}
	static HashSet<State> v = new HashSet<State>();
	static class State {
		int x, y, z, moves;
		State (int x, int y, int z, int moves) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.moves = moves;
		}
		@Override
		public int hashCode () {
			return new Integer(x).hashCode() * 31 * 31 + new Integer(y).hashCode() * 31 + new Integer(z).hashCode();
		}
		@Override
		public boolean equals (Object o) {
			if (o instanceof State) {
				State p = (State)o;
				return p.x == x && p.y == y && p.z == z;
			}
			return false;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}