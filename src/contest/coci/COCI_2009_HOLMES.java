package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2009_HOLMES {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static boolean[] ans = new boolean[1000];
  static boolean[][] cause = new boolean[1000][1000];
  static boolean[] v = new boolean[1000];
  static int d, m, n;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    d = readInt();
    m = readInt();
    n = readInt();
    for (int i = 0; i < d; i++) {
      adj.add(new ArrayList<Integer>());
      rev.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      rev.get(b).add(a);
    }

    for (int i = 0; i < n; i++) {
      int a = readInt() - 1;
      ans[a] = true;
    }

    for (int i = 0; i < d; i++) {
      if (!v[i]) {
        find(i);
      }
    }
    for (int i = 0; i < d; i++) {
      if (ans[i])
      outer:
        for (int j = 0; j < d; j++) {
          if (!ans[j]) {
            for (int k = 0; k < d; k++) {
              if (cause[i][k] && !cause[j][k])
                continue outer;
            }
            ans[j] = true;
          }
        }
    }
    for (int i = 0; i < d; i++) {
      if (ans[i])
        mark(i);
    }
    if (n != 0)
      for (int i = 0; i < d; i++)
        if (ans[i])
          System.out.print(i + 1 + " ");
    pr.close();
  }

  static void mark(int i) {
    ans[i] = true;
    for (int j : adj.get(i)) {
      if (!ans[j])
        mark(j);
    }
  }

  private static void find(int i) {
    boolean isCause = rev.get(i).size() == 0;
    v[i] = true;
    for (int j : rev.get(i)) {
      if (!v[j])
        find(j);
      for (int k = 0; k < d; k++)
        cause[i][k] |= cause[j][k];
    }
    if (isCause)
      cause[i][i] = true;
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
