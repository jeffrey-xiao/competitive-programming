package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class TEST2 {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		pr.close();
		// for(int x = 1; x <= 12; x++)
		// System.out.println(hamming(60, x));

		// System.out.println(timeToTurn("RRRLRLLLRL"));

		// System.out.println(getShots(new int[]{8,7,6,0,5,2}, 7));
		// System.out.println(isInSet());
	}

	static int[] disc, low;
	static int n, index;
	static Stack<Integer> curr;
	static ArrayList<ArrayList<Edge>> adj;

	static void SCC () throws IOException {
		// initializing all variables
		int n = readInt();
		int[] index = new int[n];
		int[] low = new int[n];
		curr = new Stack<Integer>();
		adj = new ArrayList<ArrayList<Edge>>();
		for (int x = 0; x < n; x++) {
			index[x] = -1;
			low[x] = -1;
		}
		// performing the dfs to get SCCs
		for (int x = 0; x < n; x++)
			if (index[x] == -1)
				dfs(x);
	}

	private static void dfs (int x) {
		// labeling current vertex
		disc[x] = low[x] = index++;
		curr.push(x);
		// setting the low value and traversing graph
		for (Edge next : adj.get(x)) {
			if (disc[next.dest] == -1) {
				dfs(next.dest);
				low[x] = Math.min(low[x], low[next.dest]);
			} else
				low[x] = Math.min(low[x], disc[next.dest]);
		}
		// outputing SCCs if it is a root vertex
		if (disc[x] == low[x]) {
			while (curr.peek() != x)
				System.out.println(curr.pop());
			System.out.println(curr.pop() + "\n");
		}
	}

	static class Edge {
		int dest;
	}

	// women and men are labeled from 0 to n inclusive
	int[] marry (ArrayList<LinkedList<Integer>> m, ArrayList<LinkedList<Integer>> w, int n) {
		Queue<Integer> freeM = new LinkedList<Integer>();
		// female match
		int[] match = new int[n];
		for (int x = 0; x < n; x++) {
			freeM.add(x);
			match[x] = -1;
		}
		while (!freeM.isEmpty() && m.get(freeM.peek()).size() > 0) {
			int currM = freeM.peek();
			int woman = m.get(currM).getFirst();
			m.get(currM).removeFirst();

			if (match[woman] == -1) {
				match[woman] = currM;
				freeM.poll();
			} else {
				if (w.get(woman).indexOf(match[woman]) < w.get(woman).indexOf(currM)) {
					freeM.offer(match[woman]);
					match[woman] = currM;
					freeM.poll();
				}
			}
		}
		return match;
	}

	int find (int k, int beg, int end, int[] seq) {
		int i = median(seq, beg, end, k); // obtains the approximate median
		// linear pass that shifts all the elements smaller than seq[i] to the
		// left
		// and all the elements larger than seq[i] to the right
		if (i == k)
			return seq[i];
		else if (i < k)
			return find(k, i + 1, end, seq);
		return find(k - (seq.length - i), beg, i, seq);
	}

	int median (int[] seq, int beg, int end, int k) {
		// the median of medians starts by dividing the elements into groups of
		// 5
		// Sort the groups of 5 individually. From out knowledge of sorting, it
		// will take 5lg(5) per group
		// O (N) overall
		// take the median out of all the groups: n/5 elements
		// take the median out of the n/5 elements: n/10 elements

		if (end - beg + 1 <= 5) {
			Arrays.sort(seq, beg, end + 1);
			return beg + k - 1;
		}

		for (int i = 0; i < (end + 1) / 5; i++) {
			int left = 5 * i;
			int right = left + 4;
			if (right > end)
				right = end;
			int median = median(seq, left, right, 3);
			swap(seq, median, i);
		}

		return median(seq, 0, (end + 1) / 5, (end + 1) / 10);
	}

	void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	int g (int x, int n) {
		return (x * x + 1) % n;
	}

	public ArrayList<Point> getConvexHull (Point[] points) {
		// initiate lower convex hull and upper convex hull
		ArrayList<Point> l = new ArrayList<Point>();
		ArrayList<Point> u = new ArrayList<Point>();
		Arrays.sort(points);
		int n = points.length;
		for (int x = 0; x < n; x++) {
			int i = l.size();
			// if there more than one point in the convex hull lower portion
			// and the next point does not form a counter-clock wise turn, then
			// remove it
			while (i >= 2 && ccw(l.get(i - 2), l.get(i - 1), points[x]) <= 0) {
				l.remove(i - 1);
				i = l.size();
			}
			l.add(points[x]);
		}
		for (int x = n - 1; x >= 0; x--) {
			int i = u.size();
			// if there more than one point in the convex hull upper portion
			// and the next point does not form a counter-clock wise turn, then
			// remove it
			while (i >= 2 && ccw(u.get(i - 2), u.get(i - 1), points[x]) <= 0) {
				u.remove(i - 1);
				i = u.size();
			}
			u.add(points[x]);
		}
		// remove the last points in both hulls as they are repeated in the
		// other hull
		u.remove(u.size() - 1);
		l.remove(l.size() - 1);
		// merge the two
		l.addAll(u);
		return l;
	}

	// ccw > 0 -> clockwise turn; this is based on the cross product of the
	// vectors
	static int ccw (Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}

	static class Point implements Comparable<Point> {
		int x, y;

		@Override
		public int compareTo (Point o) {
			if (x == o.x)
				return y - o.y;
			return x - o.x;
		}
	}

	public int polygonArea (Point[] points) {
		int area = 0; // Accumulates area in the loop
		int j = points.length - 1; // The last vertex is the 'previous' one to
		// the first

		for (int i = 0; i < points.length; i++) {
			area += (points[j].x + points[i].x) * (points[j].y - points[i].y);
			j = i; // j is previous vertex to i
		}
		return area / 2;
	}

	static int cycleDetection (int n, int d) {
		int rd, td; // rd and td represent the quotient
		int rr, tr; // rr and tr represent the remainder
		rd = td = n;
		rr = tr = n % d;
		do { // rabbit moves two and turtle moves one
			rr *= 10;
			rd = rr / d;
			rr = rr % d;

			tr *= 10;
			td = tr / d;
			tr = tr % d;

			tr *= 10;
			td = tr / d;
			tr = tr % d;
		} while (rd != td || rr != tr);
		int count = 0;
		do { // rabbit moves one until it meets turtle
			tr *= 10;
			td = tr / d;
			tr = tr % d;
			count++;
		} while (rd != td || rr != tr);
		return count;
	}

	static boolean isInSet (int a, int b, int c) {
		// c must be in the form of:
		// c = (a+1)^n*(b+1)^n where n is a natural number.
		a++;
		b++;
		c++;
		while (c % a == 0)
			c /= a;
		while (c % b == 0)
			c /= b;
		return c == 1;
	}

	static int getShots (int[] a, int n) {
		// greedily makes the shots to clear the current spot
		int totalShots = 0;
		for (int x = 0; x < a.length - 1; x++) {
			if (a[x] > 0) {
				int shots = (a[x] + n - 1) / n;
				totalShots += shots;
				int elim = shots * n - a[x];
				a[x] = 0;
				a[x + 1] = Math.max(0, a[x + 1] - elim);
			}
		}
		return totalShots;
	}

	static int maxSum (int[] a) {
		// maintain the current value where it is bigger than 0
		// return the maximum of all current values
		int curr = 0, max = 0;
		for (int x = 0; x < a.length; x++) {
			curr = Math.max(0, a[x] + curr);
			max = Math.max(max, curr);
		}
		return max;
	}

	static int timeToTurn (String s) {
		// maintain the index of last L and number of L
		// maintain extra time caused by consecutive L's
		s = " " + s;
		// the line uses one based indexing as it must track previous element
		int[] time = new int[s.length()];
		int lastL = 0;
		int numOfL = 0;
		for (int x = 1; x < s.length(); x++) {
			time[x] = time[x - 1];
			// if there are two consecutive L's then increase extra time by 1
			if (s.charAt(x) == 'L' && s.charAt(x - 1) == 'L')
				time[x]++;
			// increment the last index of L and the num of Ls
			if (s.charAt(x) == 'L') {
				lastL = x;
				numOfL++;
			}
		}
		return lastL + time[lastL] - numOfL;
	}

	static int[] factor (int i) {
		// function that returns the factors of i in an array
		String f = "";
		for (int x = 2; x * x <= i; x++) {
			if (i % x == 0) {
				f += (x + " ");
				while (i % x == 0)
					i /= x;
			}
		}
		if (i != 1)
			f += (i + " ");
		String[] fs = f.substring(0, f.length() - 1).split(" ");
		int[] factors = new int[fs.length];
		for (int x = 0; x < fs.length; x++)
			factors[x] = Integer.parseInt(fs[x]);
		return factors;
	}

	// static boolean[][] adj; //adjacency matrix that represents the
	// //edges from the left set to the right set
	// static int[] prev; //array that tracks the connection to the left set
	// static boolean[] visited; //array that tracks which node have been
	// visited
	// //this is reseted for each dfs (attempt to find augmenting path)
	// static int right; //size of the right set
	// static int left; //size of the left set
	// public static void main(String[] args){
	// int count = 0; //count of matches
	// for(int x = 0; x < right; x++){ //iterates through the right set
	// visited = new boolean[left]; //reset the the visited array with size of
	// left set
	// //no need to track right set
	// count += hungary(x)?1:0; //add one to the number of matches if a further
	// match is found
	// }
	// }
	// private static boolean hungary(int x){
	// for(int y = 0; y < right; y++){ //iterates through the adjacent right
	// vertexes
	// if(adj[x][y] && !visited[y]){ //checks if it was not visited in the
	// current dfs yet
	// visited[y] = true; //sets to true
	// if(prev[y] == -1 || hungary(prev[y])){ //tries to find an alternative
	// path if there is an intersection
	// prev[y] = x; //an alternating path is found, so the prev vertex must be
	// set
	// return true; //return true because the path has been found
	// }
	// }
	// }
	// return false; //return false because an alternating path has not been
	// found
	// }
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

	public TEST2 () {
	}
}
