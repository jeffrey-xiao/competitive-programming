package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class COCI_2008_KRTICA {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[][] path, pathIndex;
  static int[][] diameter, diameterIndex;
  static ArrayList<ArrayList<Integer>> adj;
  static int ans = 1 << 30;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    path = new int[N][3];
    pathIndex = new int[N][3];
    diameter = new int[N][3];
    diameterIndex = new int[N][3];

    adj = new ArrayList<ArrayList<Integer>>();

    for (int i = 0; i < N; i++) {
      adj.add(new ArrayList<Integer>());
      pathIndex[i][0] = pathIndex[i][1] = pathIndex[i][2] - 1;
      diameterIndex[i][0] = diameterIndex[i][1] = diameterIndex[i][2] - 1;
    }

    for (int i = 0; i < N - 1; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    computeLongestPaths(0, -1);
    solve(0, -1, -1 << 30, -1 << 30);

    out.println(ans);
    out.close();
  }

  static void solve(int u, int p, int dist, int prevDiameter) {
    int i, j;
    for (int v : adj.get(u)) {
      if (v != p) {
        i = maxIndex(path[u], pathIndex[u], new HashSet<Integer>(Arrays.asList(v)));
        j = maxIndex(path[u], pathIndex[u], new HashSet<Integer>(Arrays.asList(v, pathIndex[u][i])));

        int currDist = path[u][i];
        int currDiameter = path[u][i];
        if (j != -1)
          currDiameter += path[u][j];

        currDiameter = Math.max(currDiameter, prevDiameter);
        currDiameter = Math.max(currDiameter, currDist + Math.max(0, dist));
        i = maxIndex(diameter[u], diameterIndex[u], new HashSet<Integer>(Arrays.asList(v, u)));

        if (i != -1)
          currDiameter = Math.max(currDiameter, diameter[u][i]);

        int d1 = diameter[v][0];
        int d2 = currDiameter;
        ans = Math.min(ans, Math.max((d1 + 1) / 2 + (d2 + 1) / 2 + 1, Math.max(d1, d2)));
        solve(v, u, Math.max(currDist, dist) + 1, d2);

      }
    }
  }

  static int maxIndex(int[] vals, int[] indexes, HashSet<Integer> exclude) {
    for (int i = 0; i < 3; i++)
      if (!exclude.contains(indexes[i]))
        return i;
    return -1;
  }

  static void computeLongestPaths(int u, int p) {
    for (int v : adj.get(u)) {
      if (v != p) {
        computeLongestPaths(v, u);
        replace(path[u], pathIndex[u], path[v][0] + 1, v);
      }
    }
    replace(diameter[u], diameterIndex[u], path[u][0] + path[u][1], u);
    for (int v : adj.get(u)) {
      if (v != p) {
        replace(diameter[u], diameterIndex[u], diameter[v][0], v);
      }
    }
  }

  static void replace(int[] vals, int[] indexes, int val, int index) {
    if (val > vals[0]) {
      vals[2] = vals[1];
      indexes[2] = indexes[1];
      vals[1] = vals[0];
      indexes[1] = indexes[0];
      vals[0] = val;
      indexes[0] = index;
    } else if (val > vals[1]) {
      vals[2] = vals[1];
      indexes[2] = indexes[1];
      vals[1] = val;
      indexes[1] = index;
    } else if (val > vals[2]) {
      vals[2] = val;
      indexes[2] = index;
    }
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
}
