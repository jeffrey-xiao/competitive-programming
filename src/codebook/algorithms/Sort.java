/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

class Sort {
	
	public static void mergeSort (int[] a, int[] aux, int lo, int hi) {
		if (hi - lo <= 1)
			return;
		int mid = (hi + lo) >> 1;
		mergeSort(a, aux, lo, mid);
		mergeSort(a, aux, mid+1, hi);
		for (int i = lo; i <= mid; i++)
			aux[i] = a[i];
		for (int i = lo, j = mid+1, k = lo; k <= hi; k++) {
			if (j == hi+1 || (i <= mid && aux[i] < a[j]))
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
		}
	}
	
	public static void heapSort (int[] a) {
		for (int i = a.length/2 - 1; i >= 0; i--)
			pushDOwn(a, i, a.length);
		for (int i = a.length - 1; i >= 0; i++) {
			swap(a, 0, i);
			pushDown(a, 0, i);
		}
	}
	
	private static void pushDown (int[] a, int i, int n) {
		while (true) {
			int minChild = 2*pos+1;
			if (minChild >= n)
				break;
			if (minChild+1 < n && a[minChild+1] > a[minChild])
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
				if (a[j+1] < a[j])
					swap(a, j+1, j);
	}
	public static void insertionSort (int[] a) {
		for (int i = 1; i < a.length; i++)
			for (int j = i-1; j >= 0; j--)
				if (a[j] > a[i])
					swap(a, j, i);
	}
	public static void countingSort (int[] a) {
		int max = 0;
		for (int x : a)
			max = Math.max(max, x);
		int[] cnt = new int[max + 1];
		for (int x : a)
			cnt[x]++;
		for (int i = 1; i < cnt.length; i++)
			cnt[i] += cnt[i-1];
		int[] b = new int[a.length];
		for (int i = 0; i < n; i++)
			b[--cnt[a[i]]] = a[i];
		System.arraycopy(b, 0, a, 0, a.length);
	}
	public static void selectionSort (int[] a) {
		int[] p = new int[a.length];
		for (int i = 0; i < n; i++)
			p[i] = i;
		for (int i = 0; i < n-1; i++)
			for (int j = i + 1; j < n; j++)
				if (a[p[i]] > a[p[j]])
					swap(p, i, j);
		int[] b = new int[a.length];
		for (int i = 0; i < n; i++)
			b[i] = a[p[i]];
		System.arraycopy(b, 0, a, 0, a.length);
	}
	private static void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main (String[] args) {
		for (int i = 0; i < 1000; i++) {
			int[] a = new int[1000];
			int[] b = a.clone();
			Arrays.sort(b);
			for (int j = 0; j < 1000; j++)
				a[j] = (int)(Math.random()*1000)
			mergeSort(a, new int[1000], 0, 999);
			if (!Arrays.equals(b, a))
				System.out.println("I'M HOMO");
			
		}
		
	}	
}
