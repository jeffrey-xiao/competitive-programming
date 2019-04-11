package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Foxlings {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();

  public static void main(String[] args) throws IOException {
    int n = readInt();
    WeightedQuickUnion qu = new WeightedQuickUnion(200000);
    int count = 0;
    for (int x = readInt(); x > 0; x--) {
      int a = readInt();
      int b = readInt();
      if (!hs.containsKey(a)) {
        hs.put(a, count);
        a = count;
        count++;
      } else
        a = hs.get(a);
      if (!hs.containsKey(b)) {
        hs.put(b, count);
        b = count;
        count++;
      } else
        b = hs.get(b);
      qu.union(a, b);
    }
    System.out.println(n - qu.total);
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class WeightedQuickUnion {
    private int[] id;
    private int total;
    private int count;

    public WeightedQuickUnion(int n) {
      id = new int[n];
      count = n;
      for (int x = 0; x < n; x++) {
        id[x] = x;
      }
    }

    public int find(int i) {
      while (i != id[i]) {
        id[i] = id[id[i]];
        i = id[i];
      }
      return i;
    }

    public boolean connected(int x, int y) {
      return find(x) == find(y);
    }

    public int count() {
      return count;
    }

    public void union(int x, int y) {
      int rootx = find(x);
      int rooty = find(y);
      if (rootx == rooty)
        return;
      count--;
      id[rootx] = id[rooty];
      id[rooty] = id[rootx];
      total++;
    }

    public void print() {
      for (int i : id)
        System.out.print(i + " ");
      System.out.println();
    }
  }
}
