package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class IOI_2000_Walls {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int m, n, l;
  static ArrayList<ArrayList<Integer>> townsToRegions = new ArrayList<ArrayList<Integer>>();
  static ArrayList<Integer> towns = new ArrayList<Integer>();
  static int[][] adj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    m = readInt();
    n = readInt();
    l = readInt();
    adj = new int[m][m];
    HashMap<Pair, ArrayList<Integer>> hm = new HashMap<Pair, ArrayList<Integer>>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < m; j++)
        adj[i][j] = 1 << 29;
      adj[i][i] = 0;
    }
    for (int i = 0; i < l; i++)
      towns.add(readInt() - 1);
    for (int i = 0; i < n; i++)
      townsToRegions.add(new ArrayList<Integer>());
    for (int i = 0; i < m; i++) {
      int k = readInt();
      int[] a = new int[k];
      for (int j = 0; j < k; j++) {
        a[j] = readInt() - 1;
        townsToRegions.get(a[j]).add(i);
      }
      for (int j = 0; j < k; j++) {
        Pair p = new Pair(a[j], a[(j + 1) % k]);
        if (!hm.containsKey(p))
          hm.put(p, new ArrayList<Integer>());
        hm.get(p).add(i);
      }
    }
    for (Map.Entry<Pair, ArrayList<Integer>> entry : hm.entrySet()) {
      for (int i = 0; i < entry.getValue().size(); i++)
        for (int j = i + 1; j < entry.getValue().size(); j++)
          adj[entry.getValue().get(i)][entry.getValue().get(j)] = adj[entry.getValue().get(j)][entry.getValue().get(i)] = 1;
    }
    for (int k = 0; k < m; k++)
      for (int i = 0; i < m; i++)
        for (int j = 0; j < m; j++)
          adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
    int min = 1 << 30;
    int minIndex = -1;
    for (int region = 0; region < m; region++) {
      int total = 0;
      for (int town : towns) {
        int localMin = 1 << 30;
        for (int i : townsToRegions.get(town)) {
          localMin = Math.min(localMin, adj[i][region]);
        }
        total += localMin;
      }
      if (total < min) {
        min = total;
        minIndex = region + 1;
      }
    }
    out.println(min + "\n" + minIndex);
    out.close();
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Pair {
    int x, y;

    Pair(int x, int y) {
      this.x = Math.min(x, y);
      this.y = Math.max(x, y);
    }

    @Override
    public int hashCode() {
      return x + y;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Pair) {
        Pair p = (Pair)o;
        return x == p.x && y == p.y;
      }
      return false;
    }
  }
}
