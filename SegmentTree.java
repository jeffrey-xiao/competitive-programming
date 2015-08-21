package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SegmentTree {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int size;
	static int height;
	static int[] tree;
	static int start;
	static int end;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		start = 0;
		end = n - 1;
		height = (int) Math.ceil(Math.log(n) / Math.log(2));
		size = 2 * (int) Math.pow(2, height) - 1;
		tree = new int[size];
		for (int x = 0; x < size; x++)
			tree[x] = Integer.MAX_VALUE;
		int m = readInt();
		int[] input = new int[n];
		for (int x = 0; x < n; x++)
			input[x] = readInt();
		construct(input, start, end, 0);
		for (int x = 0; x < m; x++) {
			char command = next().charAt(0);
			if (command == 'Q')
				System.out.println(sum(readInt(), readInt(), start, end, 0));
			else {
				int index = readInt();
				int update = readInt();
				input[index] = update - input[index];
				update(input[index], index, start, end, 0);
			}
		}

	}

	static int construct (int[] input, int sindex, int eindex, int curr) {
		if (sindex == eindex) {
			tree[curr] = input[sindex];
			return tree[curr];
		}
		int mid = sindex + (eindex - sindex) / 2;
		tree[curr] = construct(input, sindex, mid, curr * 2 + 1)
				+ construct(input, mid + 1, eindex, curr * 2 + 2);
		return tree[curr];
	}

	static void update (int value, int pos, int sindex, int eindex, int curr) {
		if (pos < sindex || pos > eindex)
			return;
		tree[curr] += value;
		if (sindex != eindex) {
			int mid = sindex + (eindex - sindex) / 2;
			update(value, pos, sindex, mid, curr * 2 + 1);
			update(value, pos, mid + 1, eindex, curr * 2 + 2);
		}
	}

	static int sum (int qstart, int qend, int sindex, int eindex, int curr) {
		if (qend < sindex || qstart > eindex)
			return 0;
		if (qstart <= sindex && eindex <= qend)
			return tree[curr];
		int mid = sindex + (eindex - sindex) / 2;
		int left = sum(qstart, qend, sindex, mid, curr * 2 + 1);
		int right = sum(qstart, qend, mid + 1, eindex, curr * 2 + 2);
		return left + right;
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
