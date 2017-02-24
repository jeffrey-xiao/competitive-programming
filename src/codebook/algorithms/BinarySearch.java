/*
 * Finds an element in a sorted array.
 *
 * Time complexity: O(log N) where N is the length of the array.
 */

package codebook.algorithms;

public class BinarySearch {
	public static int bsearch (int[] a, int lo, int hi, int val) {
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (a[mid] == val)
				return mid;
			else if (a[mid] < val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
	}

	public static int bsearchCeil (int[] a, int lo, int hi, int val) {
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (a[mid] < val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

	public static int bsearchFloor (int[] a, int lo, int hi, int val) {
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (a[mid] <= val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return hi;
	}

	public static void main (String[] args) {
		int[] a = {1, 2, 3, 4, 4, 4, 4, 4, 4, 6, 7, 8};
		System.out.println(bsearchCeil(a, 0, 11, 9));
		System.out.println(bsearchFloor(a, 0, 11, 4));
	}
}
