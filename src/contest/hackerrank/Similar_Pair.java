package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Similar_Pair {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int n, t;
  static int[] tree = new int[200001];
  static long ans = 0;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    t = readInt();
    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());
    int[] in = new int[n];
    for (int i = 0; i < n - 1; i++) {
      int a = readInt();
      int b = readInt();
      adj.get(a - 1).add(b - 1);
      in[b - 1]++;
    }
    for (int i = 0; i < n; i++) {
      if (in[i] == 0)
        dfs(i);
    }
    System.out.println(ans);
    pr.close();
  }

  static void dfs (int i) {
    ans += query(i + 1 + t) - query(i + 1 - t - 1);

    update(i + 1, 1);
    for (Integer j : adj.get(i))
      dfs(j);
    update(i + 1, -1);

  }

  static void update (int x, int val) {
    for (; x <= 200000; x += (x & -x))
      tree[x] += val;
  }

  static int query (int x) {
    int sum = 0;
    for (; x > 0; x -= (x & -x))
      sum += tree[x];
    return sum;
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
