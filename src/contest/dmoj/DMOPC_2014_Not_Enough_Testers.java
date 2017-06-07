package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DMOPC_2014_Not_Enough_Testers {

  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int[] num = new int[100001];
    for (int i = 1; i <= 100000; i++) {
      int curr = i;
      while (curr <= 100000) {
        num[curr]++;
        curr += i;
      }
    }
    boolean[] sorted = new boolean[100001];
    HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
    for (int i = 1; i <= 100000; i++) {
      if (hm.get(num[i]) == null)
        hm.put(num[i], new ArrayList<Integer>());
      hm.get(num[i]).add(i);
    }
    int t = readInt();
    for (int qq = 1; qq <= t; qq++) {
      int k = readInt();
      int a = readInt();
      int b = readInt();
      if (hm.get(k) == null) {
        System.out.println(0);
        continue;
      }
      if (!sorted[k]) {
        Collections.sort(hm.get(k));
        sorted[k] = true;
      }
      int i1 = bsearch(hm.get(k), a, false);
      int i2 = bsearch(hm.get(k), b, true);
      System.out.println(i2 - Math.max(0, i1) + 1);
    }
  }

  static int bsearch (ArrayList<Integer> a, int i, boolean lower) {
    int lo = 0;
    int hi = a.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (lower) {
        if (a.get(mid) <= i)
          lo = mid + 1;
        else
          hi = mid - 1;
      } else {
        if (a.get(mid) < i)
          lo = mid + 1;
        else
          hi = mid - 1;
      }
    }
    return lower ? hi : lo;
  }

  private static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  private static int readInt () throws IOException {
    return Integer.parseInt(next());
  }
}