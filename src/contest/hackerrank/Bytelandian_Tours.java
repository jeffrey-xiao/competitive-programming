package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bytelandian_Tours {

  static final int MOD = 1000000007;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static ArrayList<ArrayList<Integer>> adj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));
    long[] fac = new long[10001];
    fac[0] = 1;
    for (int i = 1; i <= 10000; i++) {
      fac[i] = (fac[i - 1] * i) % MOD;
    }
    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      adj = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < n; i++)
        adj.add(new ArrayList<Integer>());
      for (int i = 0; i < n - 1; i++) {
        int a = readInt();
        int b = readInt();
        adj.get(a).add(b);
        adj.get(b).add(a);
      }

      int prev = -1;
      int curr = 0;
      boolean found = true;
      while (found) {
        found = false;
        for (int i : adj.get(curr)) {
          if (prev == i)
            continue;
          if (adj.get(i).size() != 1) {
            prev = curr;
            curr = i;
            found = true;
            break;
          }
        }
      }
      prev = -1;
      long res = 1;
      int next = 0;
    main:
      while (next != -1) {
        next = -1;
        int cnt = 0;
        for (int i : adj.get(curr)) {
          if (i == prev)
            continue;
          if (adj.get(i).size() == 1)
            cnt++;
          else {
            if (next != -1) {
              res = 0;
              break main;
            }
            next = i;
          }
        }
        res = (res * fac[cnt]) % MOD;
        if (next == -1)
          break;
        prev = curr;
        curr = next;
      }
      if (prev != -1)
        res = (res * 2) % MOD;
      out.println(res);
    }

    out.close();
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
