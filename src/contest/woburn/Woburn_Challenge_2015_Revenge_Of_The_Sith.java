package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Revenge_Of_The_Sith implements Runnable {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] color;
  static int sz = 0;
  static int color1 = 0;

  public static void main(String[] args) throws IOException, InterruptedException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    color = new int[n];
    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    Thread t = new Thread(null, new Woburn_Challenge_2015_Revenge_Of_The_Sith(), "Main", 1 << 28);
    t.start();
    t.join();
    out.close();
  }

  static void solve() {
    double total = n * n;
    double ways = 0;
    for (int i = 0; i < n; i++) {
      if (color[i] == 0) {
        sz = 0;
        color1 = 0;
        boolean isbipartite = dfs(i, 1);
        if (!isbipartite)
          ways += sz * sz;
        else
          ways += (color1) * (color1) + (sz - color1) * (sz - color1);
      }
    }
    System.out.println(ways / total);
  }

  static boolean dfs(int i, int c) {
    color[i] = c;
    if (c == 1)
      color1++;
    sz++;
    boolean res = true;
    for (int j : adj.get(i)) {
      if (color[j] == 0) {
        if (!dfs(j, -c))
          res = false;
      } else if (color[j] == c) {
        res = false;
      }
    }
    return res;
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

  public void run() {
    solve();
  }
}
