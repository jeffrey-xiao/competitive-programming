package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Journey_To_The_Moon {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int n, m;
  static boolean[] v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    v = new boolean[n];
    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());
    for (int i = 0; i < m; i++) {
      int a = readInt();
      int b = readInt();
      adj.get(a).add(b);
      adj.get(b).add(a);
    }
    ArrayList<Long> sz = new ArrayList<Long>();
    for (int i = 0; i < n; i++)
      if (!v[i])
        sz.add((long) dfs(i));
    for (int i = 0; i < sz.size(); i++)
      if (i > 0)
        sz.set(i, sz.get(i - 1) + sz.get(i));
    long ans = 0;
    for (int i = 0; i < sz.size() - 1; i++) {
      ans += (sz.get(i + 1) - sz.get(i)) * sz.get(i);
    }
    out.println(ans);
    out.close();
  }

  static int dfs(int i) {
    v[i] = true;
    int cnt = 1;
    for (int j : adj.get(i))
      if (!v[j])
        cnt += dfs(j);
    return cnt;
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