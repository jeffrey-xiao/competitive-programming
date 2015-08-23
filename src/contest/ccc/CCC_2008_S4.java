package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2008_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static boolean[] visited;
	static int[] nums;
	static int max;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			Stack<Integer> s = new Stack<Integer>();
			visited = new boolean[4];
			nums = new int[4];
			max = 0;
			for (int x = 0; x < 4; x++)
				nums[x] = readInt();
			compute(0, s);
			System.out.println(max);
		}
	}

	@SuppressWarnings ("unchecked")
	private static void compute (int x, Stack<Integer> s) {
		if (s.size() == 1 && x == 4) {
			int result = s.peek();
			if (result > max && result <= 24)
				max = result;
			return;
		}
		if (x < 4) {
			Stack<Integer> newStack = (Stack<Integer>) s.clone();
			for (int y = 0; y < 4; y++) {
				if (visited[y])
					continue;
				visited[y] = true;
				newStack.push(nums[y]);
				compute(x + 1, (Stack<Integer>) newStack.clone());
				newStack.pop();
				visited[y] = false;

			}
		}
		if (s.size() >= 2) {
			int b = s.pop();
			int a = s.pop();
			Stack<Integer> placeholder = (Stack<Integer>) s.clone();
			placeholder.push(a + b);
			compute(x, (Stack<Integer>) placeholder.clone());
			placeholder.pop();

			placeholder.push(a - b);
			compute(x, (Stack<Integer>) placeholder.clone());
			placeholder.pop();

			placeholder.push(a * b);
			compute(x, (Stack<Integer>) placeholder.clone());
			placeholder.pop();

			if (b != 0 && a % b == 0) {
				placeholder.push(a / b);
				compute(x, (Stack<Integer>) placeholder.clone());
				placeholder.pop();
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
