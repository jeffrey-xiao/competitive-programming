package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_Longest_Substring {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		Queue<Integer> q = new LinkedList<Integer>();
		ArrayList<Integer> seq = new ArrayList<Integer>();
		boolean[] nums = new boolean[80000];
		// Queue<Integer> copy = null;
		int s = readInt();
		long max = 0;
		int index = 0;
		int counter = 0;
		while (s != 0) {
			if (nums[s])
				while (nums[s])
					nums[q.poll()] = false;
			q.offer(s);
			seq.add(s);
			counter++;
			nums[s] = true;
			if (q.size() > max) {
				max = q.size();
				index = counter - q.size();
			}
			s = readInt();
		}
		for (int x = index; x < index + max; x++)
			System.out.println(seq.get(x));
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
		return Integer.parseInt(readLine());
	}

	static short readShort () throws IOException {
		return Short.parseShort(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
