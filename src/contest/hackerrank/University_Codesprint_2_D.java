package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class University_Codesprint_2_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int Q, N, G, K, initial;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<HashSet<Integer>> guesses = new ArrayList<HashSet<Integer>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    Q = readInt();

    for (int q = 1; q <= Q; q++) {
      N = readInt();

      adj.clear();
      guesses.clear();
      for (int i = 0; i < N; i++) {
        adj.add(new ArrayList<Integer>());
        guesses.add(new HashSet<Integer>());
      }

      for (int i = 0; i < N - 1; i++) {
        int u = readInt() - 1;
        int v = readInt() - 1;
        adj.get(u).add(v);
        adj.get(v).add(u);
      }

      G = readInt();
      K = readInt();

      for (int i = 0; i < G; i++) {
        int u = readInt() - 1;
        int v = readInt() - 1;

        guesses.get(v).add(u);
      }

      initial = 0;
      dfs(0, -1);

      int ans = getAns(0, -1, initial);
      int total = N;
      int gcd = gcd(ans, total);
      if (ans == 0)
        out.println(ans + "/1");
      else
        out.println(ans / gcd + "/" + (total / gcd));
    }

    out.close();
  }

  static void dfs(int u, int p) {
    if (guesses.get(u).contains(p)) {
      initial++;
    }
    for (int v : adj.get(u)) {
      if (v == p)
        continue;
      dfs(v, u);
    }
  }

  static int getAns(int u, int p, int curr) {
    if (p != -1) {
      if (guesses.get(p).contains(u))
        curr++;
      if (guesses.get(u).contains(p))
        curr--;
    }
    int ret = 0;
    if (curr >= K)
      ret++;

    for (int v : adj.get(u))
      if (v != p)
        ret += getAns(v, u, curr);
    return ret;
  }

  static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
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
