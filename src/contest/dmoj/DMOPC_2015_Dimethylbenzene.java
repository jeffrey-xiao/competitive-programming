package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DMOPC_2015_Dimethylbenzene {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static boolean[] v;
  static int[] curr;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

  static boolean valid = false;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    v = new boolean[n];
    curr = new int[n];

    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Integer>());
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
    }

    for (int i = 0; i < n; i++)
      if (!v[i])
        dfs(i, 0);
    out.println(valid ? "YES" : "NO");
    out.close();
  }

  static void dfs(int i, int depth) {
    v[i] = true;
    curr[i] = depth;
    for (int j : adj.get(i)) {
      if (curr[j] != -1 && depth - curr[j] == 5) {
        valid = true;
      }
      if (!v[j]) {
        dfs(j, depth + 1);
      }
    }
    curr[i] = -1;
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
