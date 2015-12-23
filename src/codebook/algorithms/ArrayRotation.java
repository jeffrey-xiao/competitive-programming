/*
 * Rotates an array by the given start-, mid-, and end-points.
 * 
 * Time complexity: O(N)
 * 
 */

package codebook.algorithms;

import java.util.*;
import java.io.*;

public class ArrayRotation {

	static void rotate1 (int[] a, int lo, int mid, int hi) {
		int next = mid;
		while (lo != next) {
			swap(a, lo++, next++);
			if (next == hi)
				next = mid;
			else if (lo == mid)
				mid = next;
		}
	}
	
	static void rotate2 (int[] a, int lo, int mid, int hi) {
		reverse(a, lo, mid);
		reverse(a, mid, hi);
		reverse(a, lo, hi);
	}
	
	static void rotate3 (int[] a, int lo, int mid, int hi) {
		int n = hi - lo, jump = mid - lo;
		int gcf = gcf(jump, n), cycle = n / gcf;
		for (int i = 0; i < gcf; i++) {
			int curr = i, next = 0;
			for (int j = 0; j < cycle - 1; j++) {
				next = curr + jump;
				if (next >= n)
					next -= n;
				swap(a, lo + curr, lo + next);
				curr = next;
			}
		}
	
	}
	
	static int gcf (int a, int b) {
		return b == 0 ? a : gcf(b, a % b);
	}
	
	static void reverse (int[] a, int i, int j) {
		while (i != j && i != --j)
			swap(a, i++, j);
	}
	
	static void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main (String[] args) throws IOException {
		int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		rotate3(a, 0, 5, 8);
		System.out.println(Arrays.toString(a));
		
		int[] a1 = new int[1000], a2 = new int[1000], a3 = new int[1000];
		for (int i = 0; i < 1000; i++)
			a1[i] = a2[i] = a3[i] = i;
		rotate1(a1, 0, 555, 999);
		rotate2(a2, 0, 555, 999);
		rotate3(a3, 0, 555, 999);
		assert(Arrays.equals(a1, a2) && Arrays.equals(a2, a3));
	}

}

