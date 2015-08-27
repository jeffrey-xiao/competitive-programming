package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CCC_2014_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		HashMap<Integer, Integer> toIndex = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> toCoord = new HashMap<Integer, Integer>();
		int n = readInt();
		int minTint = readInt();
		PriorityQueue<Event> pq = new PriorityQueue<Event>();
		TreeSet<Integer> ys = new TreeSet<Integer>();
		for (int x = 0; x < n; x++) {
			int x1 = readInt();
			int y1 = readInt();
			int x2 = readInt();
			int y2 = readInt();
			int t = readInt();
			pq.offer(new Event(x1, y1, y2, t));
			pq.offer(new Event(x2, y1, y2, -t));
			ys.add(y1);
			ys.add(y2);
		}
		int count = 1;
		for (Integer i : ys) {
			toIndex.put(i, count);
			toCoord.put(count, i);
			count++;
		}
		long[] line = new long[count + 1];
		long totalArea = 0;
		long lastx = Integer.MIN_VALUE;
		while (!pq.isEmpty()) {
			Event curr = pq.poll();
			long currx = curr.x;
			// System.out.println(currx + " " + curr.y1 + " " + curr.y2);
			long currTotal = 0;
			for (int x = 0; x < count; x++) {
				currTotal += line[x];
				// System.out.print(currTotal + " ");
				if (lastx != Integer.MIN_VALUE && currTotal >= minTint && x != count - 1) {
					// System.out.println("INCREASE " + currx + " " + lastx
					// +" "+ toCoord.get(x+1)+" " + toCoord.get(x));
					totalArea += (currx - lastx) * ((long) toCoord.get(x + 1) - (long) toCoord.get(x));
					// System.out.println("TOTAL AREA " + totalArea);
				}

			}
			// System.out.println();
			lastx = currx;
			// System.out.println(toIndex.get(curr.y1) + " " +
			// toIndex.get(curr.y2) + " " + curr.value);
			line[toIndex.get(curr.y1)] += curr.value;
			line[toIndex.get(curr.y2)] -= curr.value;
			while (!pq.isEmpty() && pq.peek().x == lastx) {
				curr = pq.poll();
				line[toIndex.get(curr.y1)] += curr.value;
				line[toIndex.get(curr.y2)] -= curr.value;
			}
		}
		System.out.println(totalArea);
	}

	static class Event implements Comparable<Event> {
		int x;
		int y1;
		int y2;
		int value;

		Event (int x, int y1, int y2, int value) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
			this.value = value;
		}

		@Override
		public int compareTo (Event o) {
			return x - o.x;
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
