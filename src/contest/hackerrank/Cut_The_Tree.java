package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Cut_The_Tree {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] sum;
  static int[] a;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    sum = new int[n];
    a = new int[n];
    int total = 0;
    for (int i = 0; i < n; i++) {
      total += a[i] = readInt();
      adj.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < n - 1; i++) {
      int x = readInt() - 1;
      int y = readInt() - 1;
      adj.get(x).add(y);
      adj.get(y).add(x);
    }
    dfs(0, -1);
    int min = 1 << 30;
    for (int i = 0; i < n; i++) {
      min = Math.min(Math.abs(total - sum[i] - sum[i]), min);
    }
    out.println(min);
    out.close();
  }

  static void dfs (int i, int prev) {
    sum[i] = a[i];
    for (Integer j : adj.get(i)) {
      if (j == prev)
        continue;
      dfs(j, i);
      sum[i] += sum[j];
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
