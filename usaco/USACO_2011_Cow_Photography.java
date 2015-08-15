package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class USACO_2011_Cow_Photography {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	public static void main (String[] args) throws IOException {
		int n = readInt();
		final HashMap<Photo, Integer> map = new HashMap<Photo, Integer>();
		Integer[] order = new Integer[n];
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < n; y++) {
				int cow = readInt();
				map.put(new Photo(x, cow), y);
				order[y] = cow;
			}
		}

		Arrays.sort(order, new Comparator<Integer>() {
			@Override
			public int compare (Integer id1, Integer id2) {
				int count = 0;
				for (int x = 0; x < 5; x++) {
					// System.out.println(x + " " + id1 + " " + id2);
					if (map.get(new Photo(x, id1)) < map.get(new Photo(x, id2)))
						count++;
				}
				return count - 3;
			}
		});

		for (int x = n - 1; x >= 0; x--)
			ps.println(order[x]);
		ps.close();
	}

	static class Photo {
		int id;
		int cowValue;

		Photo (int id, int cowValue) {
			this.id = id;
			this.cowValue = cowValue;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Photo) {
				Photo p = (Photo) o;
				return cowValue == p.cowValue && id == p.id;
			}
			return false;
		}

		@Override
		public int hashCode () {
			return cowValue * 147 + id;
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
