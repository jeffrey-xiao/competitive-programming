package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class STNBD_Rinslet_Laurenfrost {

  static final int MOD = 1000000007;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    int[] indegree = new int[n];
    ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    for (int x = 0; x < n; x++)
      adj.add(new ArrayList<Integer>());
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      indegree[b]++;
    }
    Queue<Integer> start = new LinkedList<Integer>();
    boolean[] v = new boolean[n];
    long[] count = new long[n];
    long[] sum = new long[n];
    for (int x = 0; x < n; x++)
      if (indegree[x] == 0) {
        start.add(x);
        count[x] = 1;
        v[x] = true;
      }
    long total = 0;
    while (!start.isEmpty()) {
      int curr = start.poll();
      for (Integer i : adj.get(curr)) {
        indegree[i]--;
        count[i] += (count[curr]) % MOD;
        sum[i] += (count[curr] + sum[curr]) % MOD;
        if (indegree[i] == 0)
          start.add(i);
      }
      if (adj.get(curr).size() == 0)
        total = (total + sum[curr]) % MOD;
    }
    System.out.println(total % MOD);
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
