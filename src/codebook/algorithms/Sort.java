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

  public static void shellSort(int[] seq) {
    for (int i = 0; i < seq.length; i++)
      for (int j = i + 4; j < seq.length; j += 4)
        if (seq[i] > seq[j])
          swap(seq, i, j);
  }

  public static int quickSelect(int[] seq, int k) {
    return quickSelect(seq, 0, seq.length - 1, k);
  }

  private static int quickSelect(int[] seq, int l, int r, int k) {
    while (true) {
      if (l == r)
        return l;

      int pivot = median(seq, l, r);
      pivot = partition(seq, l, r, pivot);

      if (k == pivot)
        return k;
      else if (k < pivot)
        r = pivot - 1;
      else
        l = pivot + 1;
    }
  }

  public static void quickSort(int[] seq) {
    quickSort(seq, 0, seq.length - 1);
  }

  private static void quickSort(int[] seq, int l, int r) {
    if (r <= l)
      return;
    int pivot = median(seq, l, r);
    pivot = partition(seq, l, r, pivot);
    quickSort(seq, l, pivot - 1);
    quickSort(seq, pivot + 1, r);
  }

  private static int median(int[] seq, int l, int r) {
    if (r - l + 1 <= 5) {
      Arrays.sort(seq, l, r + 1);
      return (l + r) / 2;
    }

    for (int i = 0; i < (r - l) / 5 + 1; i++) {
      int left = 5 * i + l;
      int right = Math.min(left + 4, r);

      Arrays.sort(seq, left, right + 1);
      int median = (left + right) / 2;
      swap(seq, median, i + l);
    }
    return quickSelect(seq, l, l + (r - l) / 5, l + (r - l) / 10);
  }

  private static int partition(int[] seq, int l, int r, int pivot) {
    swap(seq, pivot, l);
    int i = l;
    int j = r + 1;
    while (true) {
      while (seq[++i] < seq[l])
        if (i == r)
          break;
      while (seq[l] < seq[--j])
        if (j == l)
          break;
      if (i >= j)
        break;
      swap(seq, i, j);
    }
    swap(seq, l, j);
    return j;
  }

  public static void LSD(int[] seq) {
    int n = seq.length;
    int[] aux = new int[n];
    for (int i = 1; i <= 1000000000; i *= 10) {
      int[] count = new int[10];
      for (int j = 0; j < n; j++)
        count[(seq[j] / i) % 10]++;
      for (int j = 1; j < 10; j++)
        count[j] += count[j - 1];
      for (int j = n - 1; j >= 0; j--)
        aux[--count[(seq[j] / i) % 10]] = seq[j];
      for (int j = 0; j < n; j++)
        seq[j] = aux[j];
    }
  }

  public static void MSD(int[] seq) {
    int[] aux = new int[seq.length];
    MSD(seq, aux, 0, seq.length - 1, 1000000000);
  }

  private static void MSD(int[] a, int[] aux, int l, int r, int d) {
    if (r <= l || d == 0)
      return;
    int[] count = new int[11];
    int[] count2 = new int[11];
    for (int i = l; i <= r; i++)
      count[(a[i] / d) % 10 + 1]++;
    for (int i = 1; i < 11; i++)
      count[i] += count[i - 1];
    for (int i = 0; i < 11; i++)
      count2[i] = count[i];
    for (int i = r; i >= l; i--)
      aux[count[(a[i] / d) % 10]++] = a[i];
    for (int i = l; i <= r; i++)
      a[i] = aux[i - l];
    for (int i = 0; i < 10; i++)
      MSD(a, aux, l + count2[i], l + count2[i + 1] - 1, d / 10);
  }

  public static void recursiveMergeSort(int[] seq) {
    recursiveMergeSort(seq, new int[seq.length], 0, seq.length - 1);
  }

  private static void recursiveMergeSort(int[] seq, int[] aux, int l, int r) {
    if (r - l <= 0)
      return;
    int mid = (r + l) >> 1;
    recursiveMergeSort(seq, aux, l, mid);
    recursiveMergeSort(seq, aux, mid + 1, r);
    merge(seq, aux, l, mid, r);
  }

  public static void iterativeMergeSort(int[] seq) {
    int length = seq.length;
    int[] aux = new int[length];
    for (int gap = 1; gap < length; gap *= 2)
      for (int l = 0; l < length - gap; l += gap * 2)
        merge(seq, aux, l, l + gap - 1, Math.min(length - 1, l + gap + gap - 1));
  }

  private static void merge(int[] seq, int[] aux, int l, int m, int r) {
    for (int i = l; i <= m; i++)
      aux[i] = seq[i];
    for (int i = l, j = m + 1, k = l; k <= r; k++) {
      if (j == r + 1 || (i <= m && aux[i] < seq[j]))
        seq[k] = aux[i++];
      else
        seq[k] = seq[j++];
    }
  }

  public static void heapSort(int[] seq) {
    for (int i = seq.length / 2 - 1; i >= 0; i--)
      pushDown(seq, i, seq.length);
    for (int i = seq.length - 1; i >= 0; i--) {
      swap(seq, 0, i);
      pushDown(seq, 0, i);
    }
  }

  private static void pushDown(int[] seq, int i, int n) {
    while (true) {
      int minChild = 2 * i + 1;
      if (minChild >= n)
        break;
      if (minChild + 1 < n && seq[minChild + 1] > seq[minChild])
        minChild++;
      if (seq[i] >= seq[minChild])
        break;
      swap(seq, i, minChild);
      i = minChild;
    }
  }

  public static void bubbleSort(int[] seq) {
    for (int i = 0; i + 1 < seq.length; i++)
      for (int j = 0; j + 1 < seq.length; j++)
        if (seq[j + 1] < seq[j])
          swap(seq, j + 1, j);
  }

  public static void insertionSort(int[] seq) {
    for (int i = 1; i < seq.length; i++)
      for (int j = i; j > 0; j--) {
        if (seq[j - 1] > seq[j])
          swap(seq, j - 1, j);
        else
          break;
      }
  }

  public static void countingSort(int[] seq) {
    int max = 0;
    for (int val : seq)
      max = Math.max(max, val);
    int[] cnt = new int[max + 1];
    for (int val : seq)
      cnt[val]++;
    for (int i = 1; i < cnt.length; i++)
      cnt[i] += cnt[i - 1];
    int[] b = new int[seq.length];
    for (int i = 0; i < seq.length; i++)
      b[--cnt[seq[i]]] = seq[i];
    System.arraycopy(b, 0, seq, 0, seq.length);
  }

  public static void selectionSort(int[] seq) {
    int[] p = new int[seq.length];
    for (int i = 0; i < seq.length; i++)
      p[i] = i;
    for (int i = 0; i < seq.length - 1; i++)
      for (int j = i + 1; j < seq.length; j++)
        if (seq[p[i]] > seq[p[j]])
          swap(p, i, j);
    int[] b = new int[seq.length];
    for (int i = 0; i < seq.length; i++)
      b[i] = seq[p[i]];
    System.arraycopy(b, 0, seq, 0, seq.length);
  }

  private static void swap(int[] seq, int i, int j) {
    int temp = seq[i];
    seq[i] = seq[j];
    seq[j] = temp;
  }

  public static void main(String[] args) throws Exception {
    int[] a = new int[10000];
    int[] b = new int[10000];

    for (int j = 0; j < 10000; j++)
      a[j] = (int)(Math.random() * 10000);
    System.arraycopy(a, 0, b, 0, a.length);

    Arrays.sort(b);
    quickSort(a);

    assert Arrays.equals(a, b);

    a = new int[10000];
    b = new int[10000];

    for (int j = 0; j < 10000; j++)
      a[j] = (int)(Math.random() * 10000);
    System.arraycopy(a, 0, b, 0, a.length);

    int k = (int)(Math.random() * 10000);
    Arrays.sort(a);
    int answer = b[quickSelect(b, 0, b.length - 1, k)];
    assert a[k] == answer;
  }
}
