/*
 * Implementation of various sorting and selection algorithm.
 * 
 * Time complexities:
 *  - Shell Sort: O(N^(3/2)) 
 *  - Quick Select: O(N) 
 *  - Quick Sort: O(N log N) 
 *  - Radix Sort (MSD, LSD): O(NK) where K is the number of digits.
 *  - Merge Sort: O(N log N) 
 *  - Counting Sort: O(NK) where K is the largest integer.
 *  - Bubble Sort: O(N^2) 
 *  - Insertion Sort: O(N^2) 
 *  - Heap Sort: O(N log N) 
 *  - Selection Sort: O(N^2) 
 */

package codebook.algorithms;

import java.util.Arrays;

public class Sort {

	public static void shell (int[] a) {
		for (int x = 0; x < a.length; x++)
			for (int y = x + 4; y < a.length; y += 4)
				if (a[x] > a[y])
					swap(a, x, y);
	}

	public int find (int k, int beg, int end, int[] seq) {
		int i = median(seq, beg, end, k);
		if (i == k)
			return seq[i];
		else if (i < k)
			return find(k, i + 1, end, seq);
		return find(k - (seq.length - i), beg, i, seq);
	}

	private int median (int[] seq, int beg, int end, int k) {
		if (end - beg + 1 <= 5) {
			Arrays.sort(seq, beg, end + 1);
			return beg + k - 1;
		}

		for (int i = 0; i < (end + 1) / 5; i++) {
			int left = 5 * i;
			int right = left + 4;
			if (right > end)
				right = end;
			int median = median(seq, left, right, 3);
			swap(seq, median, i);
		}
		return median(seq, 0, (end + 1) / 5, (end + 1) / 10);
	}

	public static void quickSort (int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	private static void quickSort (int[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int x = partition(a, lo, hi);
		quickSort(a, lo, x - 1);
		quickSort(a, x + 1, hi);
	}

	private static int partition (int[] a, int lo, int hi) {
		// random partition
		int rand = (int)(Math.random() * (hi - lo + 1) + lo);
		swap(a, rand, lo);
		int x = lo;
		int y = hi + 1;
		while (true) {
			while (a[++x] < a[lo])
				if (x == hi)
					break;
			while (a[lo] < a[--y])
				if (y == lo)
					break;
			if (x >= y)
				break;
			swap(a, x, y);
		}
		swap(a, lo, y);
		return y;
	}

	public static void LSD (int[] a) {
		int n = a.length;
		int[] aux = new int[n];
		for (int i = 1; i <= 1000000000; i *= 10) {
			int[] count = new int[10];
			for (int j = 0; j < n; j++)
				count[(a[j] / i) % 10]++;
			for (int j = 1; j < 10; j++)
				count[j] += count[j - 1];
			for (int j = n - 1; j >= 0; j--)
				aux[--count[(a[j] / i) % 10]] = a[j];
			for (int j = 0; j < n; j++)
				a[j] = aux[j];
		}
	}

	public static void MSD (int[] a) {
		int[] aux = new int[a.length];
		MSD(a, aux, 0, a.length - 1, 1000000000);
	}

	private static void MSD (int[] a, int[] aux, int lo, int hi, int d) {
		if (hi <= lo || d == 0)
			return;
		int[] count = new int[11];
		int[] count2 = new int[11];
		for (int i = lo; i <= hi; i++)
			count[(a[i] / d) % 10 + 1]++;
		for (int i = 1; i < 11; i++)
			count[i] += count[i - 1];
		for (int i = 0; i < 11; i++)
			count2[i] = count[i];
		for (int i = hi; i >= lo; i--)
			aux[count[(a[i] / d) % 10]++] = a[i];
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];
		for (int i = 0; i < 10; i++)
			MSD(a, aux, lo + count2[i], lo + count2[i + 1] - 1, d / 10);
	}

	public static void mergeSort1 (int[] a) {
		mergeSort1(a, new int[a.length], 0, a.length - 1);
	}

	public static void mergeSort2 (int[] a) {
		int length = a.length;
		int[] aux = new int[length];
		for (int gap = 1; gap < length; gap *= 2)
			for (int low = 0; low < length - gap; low += gap * 2)
				merge(a, aux, low, low + gap - 1, Math.min(length - 1, low + gap + gap - 1));
	}

	private static void mergeSort1 (int[] a, int[] aux, int lo, int hi) {
		if (hi - lo <= 0)
			return;
		int mid = (hi + lo) >> 1;
		mergeSort1(a, aux, lo, mid);
		mergeSort1(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	private static void merge (int[] a, int[] aux, int lo, int mid, int hi) {
		for (int i = lo; i <= mid; i++)
			aux[i] = a[i];
		for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
			if (j == hi + 1 || (i <= mid && aux[i] < a[j]))
				a[k] = aux[i++];
			else
				a[k] = a[j++];
		}
	}

	public static void heapSort (int[] a) {
		for (int i = a.length / 2 - 1; i >= 0; i--)
			pushDown(a, i, a.length);
		for (int i = a.length - 1; i >= 0; i--) {
			swap(a, 0, i);
			pushDown(a, 0, i);
		}
	}

	private static void pushDown (int[] a, int i, int n) {
		while (true) {
			int minChild = 2 * i + 1;
			if (minChild >= n)
				break;
			if (minChild + 1 < n && a[minChild + 1] > a[minChild])
				minChild++;
			if (a[i] >= a[minChild])
				break;
			swap(a, i, minChild);
			i = minChild;
		}
	}

	public static void bubbleSort (int[] a) {
		for (int i = 0; i + 1 < a.length; i++)
			for (int j = 0; j + 1 < a.length; j++)
				if (a[j + 1] < a[j])
					swap(a, j + 1, j);
	}

	public static void insertionSort (int[] a) {
		for (int i = 1; i < a.length; i++)
			for (int j = i; j > 0; j--) {
				if (a[j - 1] > a[j])
					swap(a, j - 1, j);
				else
					break;
			}
	}

	public static void countingSort (int[] a) {
		int max = 0;
		for (int x : a)
			max = Math.max(max, x);
		int[] cnt = new int[max + 1];
		for (int x : a)
			cnt[x]++;
		for (int i = 1; i < cnt.length; i++)
			cnt[i] += cnt[i - 1];
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++)
			b[--cnt[a[i]]] = a[i];
		System.arraycopy(b, 0, a, 0, a.length);
	}

	public static void selectionSort (int[] a) {
		int[] p = new int[a.length];
		for (int i = 0; i < a.length; i++)
			p[i] = i;
		for (int i = 0; i < a.length - 1; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[p[i]] > a[p[j]])
					swap(p, i, j);
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[p[i]];
		System.arraycopy(b, 0, a, 0, a.length);
	}

	private static void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main (String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			int[] a = new int[10000];
			int[] b = new int[10000];
			for (int j = 0; j < 10; j++)
				a[j] = (int)(Math.random() * 10000);
			System.arraycopy(a, 0, b, 0, a.length);
			Arrays.sort(b);
			mergeSort1(a);
			if (!Arrays.equals(a, b))
				throw new Exception();
		}
	}
}
