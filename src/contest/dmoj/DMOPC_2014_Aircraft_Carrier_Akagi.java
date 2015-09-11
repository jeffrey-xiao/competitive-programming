package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Aircraft_Carrier_Akagi {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int SIZE = 600001;
	static int offset = 0;
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		long[] left = new long[SIZE];
		long[] right = new long[SIZE];
		long[] leftSum = new long[SIZE];
		long[] rightSum = new long[SIZE];
		int n = readInt();
		int k = readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt() + 300000;
		long min = 1l << 60;
		for (int i = 0; i < n; i++) {
			offset--;
			if (i % 2 == 0) {
				update(left, (a[i - i/2] - i/2 - 1), 1);
				update(right, (a[i - i/2] + i/2), -1);

				update(leftSum, (a[i - i/2] - i/2 - 1), (a[i - i/2] - i/2 - 1));
				update(rightSum, (a[i - i/2] + i/2), -(a[i - i/2] + i/2));
//				out.println("LEFT ADDED " + (a[i - i/2] - i/2 - 1));
//				out.println("REMOVED " + (a[i - i/2] + i/2));
			}
//			out.println("ADDED " + (a[i] - (1 + offset)));
			update(right, a[i] - (1 + offset), 1);
			update(rightSum, a[i] - (1 + offset), a[i] - (1 + offset));
			
			if (i < k - 1 || (i + 1) % 2 == 0)
				continue;
			int lo = 1;
			int hi = SIZE - 1;
			int median = 1 << 30;
			long leftSz = 0;
			long rightSz = 0;
			while (lo <= hi) {
				int mid = (hi + lo)/2;
				leftSz = query(left, mid) + query(right, mid - offset); 
				rightSz = (i + 1) - leftSz;
				if (leftSz == rightSz) {
					median = mid;
					break;
				} else if (leftSz < rightSz)
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			
			if (median == 1 << 30)
				median = lo;
			leftSz = query(left, median) + query(right, median - offset); 
			rightSz = (i + 1) - leftSz;
//			out.println((i+1) + " THE MEDIAN IS " + median);
			
			long smallRightSz = query(right, median - offset);
			long bigRightSz = query(right, SIZE-1) - smallRightSz;
			
			long totalSmallerSum = query(leftSum, median) + query(rightSum, median - offset) + offset * smallRightSz;
			long totalBiggerSum = query(leftSum, SIZE - 1) + query(rightSum, SIZE - 1) + offset * (smallRightSz + bigRightSz) - totalSmallerSum;
			min = Math.min(min,  (leftSz) * median - totalSmallerSum + totalBiggerSum - median*(rightSz));
			
//			out.printf("small sum %d and big sum %d left size %d and right size %d\n", totalSmallerSum, totalBiggerSum, leftSz, rightSz);
//			out.println("RES " + (leftSz * median - totalSmallerSum + totalBiggerSum - median*rightSz));
		}
		out.println(min == 1l << 60 ? -1 : min);
		out.close();
	}

	static void update (long[] tree, int x, int val) {
		for (int i = x; i < SIZE; i += (i & -i))
			tree[i] += val;
	}
	
	static long query (long[] tree, int x) {
		long sum = 0;
		for (int i = x; i > 0; i -= (i & -i))
			sum += tree[i];
		return sum;
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

