package codebook.algorithms;

public class BinarySearch {
	public int binarySearch (int[] a, int lo, int hi, int val) {
		while (lo <= hi) {
			int mid = lo + (hi - lo) >> 1;
			if (a[mid] == val)
				return mid;
			else if (a[mid] < val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return -1;
	}
}

