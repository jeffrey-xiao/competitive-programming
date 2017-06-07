package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IOI_2015_Sorting {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static int[] val, index, x, y;
  static int[] sorted;

  static HashMap<Integer, Integer> toIndex = new HashMap<Integer, Integer>(); // val to index

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();

    val = new int[n];
    index = new int[n]; // element at index i has "real" index index[i]

    for (int i = 0; i < n; i++) {
      val[i] = readInt();
      index[i] = i;
      toIndex.put(val[i], i);
    }
    sorted = Arrays.copyOf(val, n);
    Arrays.sort(sorted);
    m = readInt();
    x = new int[m];
    y = new int[m];
    for (int i = 0; i < m; i++) {
      x[i] = readInt();
      y[i] = readInt();
    }
    int lo = 0, hi = n - 1;
    ArrayList<Integer> s1 = new ArrayList<Integer>();
    ArrayList<Integer> s2 = new ArrayList<Integer>();
    while (lo <= hi) {
      int mid = (hi + lo) / 2;
      for (int i = mid - 1; i >= 0; i--)
        swap(index, x[i], y[i]);

      int cnt = 0;
      for (int j = 0; j < n; j++) {
        if (sorted[index[j]] != val[j]) {
          int toSwap = toIndex.get(sorted[index[j]]);
          s1.add(j);
          s2.add(toSwap);
          swap(val, j, toSwap);
          toIndex.put(val[j], j);
          toIndex.put(val[toSwap], toSwap);
          cnt++;
        }
      }

      for (int i = s1.size() - 1; i >= 0; i--) {
        swap(val, s1.get(i), s2.get(i));
        toIndex.put(val[s2.get(i)], s2.get(i));
        toIndex.put(val[s1.get(i)], s1.get(i));
      }

      for (int i = 0; i < mid; i++)
        swap(index, x[i], y[i]);

      if (cnt <= mid)
        hi = mid - 1;
      else
        lo = mid + 1;

      s1.clear();
      s2.clear();
    }

    // rebuilding sequence

    ArrayList<Integer> ans1 = new ArrayList<Integer>();
    ArrayList<Integer> ans2 = new ArrayList<Integer>();
    for (int i = lo - 1; i >= 0; i--)
      swap(index, x[i], y[i]);

    for (int j = 0; j < n; j++) {
      if (sorted[index[j]] != val[j]) {
        int toSwap = toIndex.get(sorted[index[j]]);
        s1.add(j);
        s2.add(toSwap);
        ans1.add(val[j]);
        ans2.add(val[toSwap]);
        swap(val, j, toSwap);
        toIndex.put(val[j], j);
        toIndex.put(val[toSwap], toSwap);
      }
    }

    for (int i = s1.size() - 1; i >= 0; i--) {
      swap(val, s1.get(i), s2.get(i));
      toIndex.put(val[s2.get(i)], s2.get(i));
      toIndex.put(val[s1.get(i)], s1.get(i));
    }

    for (int i = 0; i < lo; i++)
      swap(index, x[i], y[i]);

    for (int i = 0; i < lo; i++) {
      swap(val, x[i], y[i]);
      toIndex.put(val[x[i]], x[i]);
      toIndex.put(val[y[i]], y[i]);

      if (i >= ans1.size())
        continue;

      int si = toIndex.get(ans1.get(i));
      int sj = toIndex.get(ans2.get(i));
      ans1.set(i, si);
      ans2.set(i, sj);
      swap(val, si, sj);
      toIndex.put(val[si], si);
      toIndex.put(val[sj], sj);
    }

    out.println(lo);
    for (int i = 0; i < lo; i++) {
      if (i < s1.size())
        out.printf("%d %d\n", ans1.get(i), ans2.get(i));
      else
        out.printf("0 0\n");
    }
    out.close();
  }

  // changing the indexes
  static void swap (int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
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
