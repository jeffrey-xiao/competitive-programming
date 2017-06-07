/*
 * Given an array of length n, reassigns values to the array elements 
 * such that the magnitude of each new value is no more than n, 
 * while preserving the relative order of each element 
 * as they existed in the original array.
 * 
 * TIme complexity; O(N log N)
 * 
 */

package codebook.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class CoordinateCompress {

  static void compress (int[] a, int lo, int hi) {
    TreeSet<Integer> ts = new TreeSet<Integer>();
    for (int i = lo; i <= hi; i++)
      ts.add(a[i]);

    int cnt = 0;
    HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

    for (int val : ts)
      hm.put(val, cnt++);

    for (int i = lo; i <= hi; i++)
      a[i] = hm.get(a[i]);
  }

  public static void main (String[] args) {
    int[] a = new int[] {1, 2, 1, 5, 20, 20, 30, 20, 5, 1};
    compress(a, 0, 9);
    System.out.println(Arrays.toString(a));
  }
}
