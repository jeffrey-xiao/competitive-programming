package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Rust_And_Murderer {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static HashSet<Integer> nv = new HashSet<Integer>();
  static ArrayList<ArrayList<Integer>> adj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    for (int t = readInt(); t > 0; t--) {
      int n = readInt();
      int m = readInt();
      adj = new ArrayList<ArrayList<Integer>>();
      for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<Integer>());
        nv.add(i);
      }
      for (int i = 0; i < m; i++) {
        int a = readInt() - 1;
        int b = readInt() - 1;
        adj.get(b).add(a);
        adj.get(a).add(b);
      }
      Queue<Integer> q = new ArrayDeque<Integer>();
      int s = readInt() - 1;
      q.offer(s);
      int[] dist = new int[n];
      nv.remove(s);
      while (!q.isEmpty()) {
        Integer curr = q.poll();
        HashSet<Integer> newnv = new HashSet<Integer>();
        for (int i : adj.get(curr)) {
          if (nv.contains(i))
            newnv.add(i);
        }
        for (Integer i : nv)
          if (!newnv.contains(i)) {
            dist[i] = dist[curr] + 1;
            q.offer(i);
          }
        nv = newnv;
      }
      for (int i = 0; i < n; i++)
        if (i != s)
          out.print(dist[i] + " ");
      out.println();
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
