package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MITACM_A_City_Of_Skyscrapers {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();

		int c = readInt();
		int C = readInt();
		int[] rows = new int[n];
		int[] columns = new int[m];

		int min = 0;
		int max = 0;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int x = 0; x < n; x++) {
			rows[x] = readInt();
			if (!map.containsKey(rows[x])) {
				map.put(rows[x], 1);
			} else {
				map.put(rows[x], map.get(rows[x]) + 1);
			}
		}
		for (int x = 0; x < m; x++) {
			columns[x] = readInt();
			if (map.containsKey(columns[x]) && map.get(columns[x]) > 0) {
				map.put(columns[x], map.get(columns[x]) - 1);
				if (map.get(columns[x]) == 0)
					map.remove(columns[x]);
				min += columns[x];
			} else
				min += columns[x];
		}
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			min += e.getValue() * e.getKey();
		}
		sortUp(rows);
		sortUp(columns);
		if (checkImpossible(rows, columns, n, m)) {
			System.out.println("Impossible");
			return;
		}

		max = getMax(rows, columns, n, m);
		System.out.printf("Minimum: %d, maximum: %d", min * c, max * C);
	}

	public static void sortUp (int[] a) {
		int length = a.length;
		int[] aux = new int[length];
		for (int gap = 1; gap < length; gap *= 2)
			for (int low = 0; low < length - gap; low += gap * 2)
				merge(a, aux, low, low + gap - 1,
						Math.min(length - 1, low + gap + gap - 1));
	}

	private static void merge (int[] a, int[] aux, int low, int mid, int high) {
		for (int i = low; i <= high; i++)
			aux[i] = a[i];
		int x = low;
		int y = mid + 1;
		for (int i = low; i <= high; i++) {
			if (x > mid)
				a[i] = aux[y++];
			else if (y > high)
				a[i] = aux[x++];
			else if (aux[x] < aux[y])
				a[i] = aux[x++];
			else
				a[i] = aux[y++];
		}
	}

	private static boolean checkImpossible (int[] rows, int[] columns, int n,
			int m) {
		return rows[n - 1] != columns[m - 1];
	}

	private static int getMax (int[] r, int[] c, int n, int m) {
		int max = 0;
		int nextValue = 0;
		for (int x = 0; x < n; x++) {
			for (int y = nextValue; y < m; y++) {
				if (r[x] <= c[y]) {
					max += (m - y) * r[x];
					nextValue = y;
					break;
				} else {
					max += c[y] * (n - x);
				}
			}
		}
		return max;
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
