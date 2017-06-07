package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2013_Vacation_Planning {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static ArrayList<ArrayList<int[]>> con = new ArrayList<ArrayList<int[]>>();
  static int hubNum;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    hubNum = readInt() - 1;
    int paths = readInt();
    for (int x = 0; x < n; x++)
      con.add(new ArrayList<int[]>());
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      con.get(a).add(new int[] {b, c});
    }
    int count = 0;
    int total = 0;
    for (int x = 0; x < paths; x++) {
      int ori = readInt() - 1;
      int dest = readInt() - 1;
      boolean[] visited = new boolean[n];
      int value = getMinCost(ori, dest, visited, 0, false);
      if (value != Integer.MAX_VALUE) {
        count++;
        total += value;
      }
    }
    System.out.println(count + "\n" + total);
  }

  private static int getMinCost (int o, int d, boolean[] v, int cost, boolean hubPassed) {
    if (cost == Integer.MAX_VALUE)
      return cost;
    v[o] = true;
    if (o <= hubNum)
      hubPassed = true;
    if (hubPassed && o == d)
      return cost;
    else if (!hubPassed && o == d)
      return Integer.MAX_VALUE;
    int min = Integer.MAX_VALUE;
    for (int x = 0; x < con.get(o).size(); x++) {
      int[] connection = con.get(o).get(x);
      if (!v[connection[0]])
        min = Math.min(min, getMinCost(connection[0], d, Arrays.copyOf(v, v.length), cost + connection[1], hubPassed));
    }
    return min;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
