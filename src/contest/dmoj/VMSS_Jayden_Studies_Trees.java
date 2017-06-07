package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class VMSS_Jayden_Studies_Trees {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] dist;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();

    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < n - 1; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    dist = new int[n];
    computeDist(0, -1, 0);

    int maxIndex = -1;
    for (int i = 0; i < n; i++)
      if (maxIndex == -1 || (dist[maxIndex] < dist[i]))
        maxIndex = i;

    dist = new int[n];
    computeDist(maxIndex, -1, 0);

    int ans = 0;
    for (int i = 0; i < n; i++)
      ans = Math.max(ans, dist[i]);

    out.println(ans);
    out.close();
  }

  static void computeDist (int u, int prev, int depth) {
    dist[u] = depth;
    for (int v : adj.get(u)) {
      if (v != prev) {
        computeDist(v, u, depth + 1);
      }
    }
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
