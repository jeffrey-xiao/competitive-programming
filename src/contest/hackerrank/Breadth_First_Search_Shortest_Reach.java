package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Breadth_First_Search_Shortest_Reach {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int m = readInt();
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

      int[] dist = new int[n];
      for (int i = 0; i < n; i++) {
        adj.add(new ArrayList<Integer>());
        dist[i] = 1 << 29;
      }
      for (int i = 0; i < m; i++) {
        int a = readInt() - 1;
        int b = readInt() - 1;
        adj.get(a).add(b);
        adj.get(b).add(a);
      }
      int s = readInt() - 1;
      Queue<Integer> q = new ArrayDeque<Integer>();
      dist[s] = 0;
      q.offer(s);
      while (!q.isEmpty()) {
        Integer curr = q.poll();
        for (int next : adj.get(curr)) {
          if (dist[next] == 1 << 29) {
            dist[next] = dist[curr] + 1;
            q.offer(next);
          }
        }
      }
      for (int i = 0; i < n; i++) {
        if (i == s)
          continue;
        System.out.print(dist[i] == 1 << 29 ? "-1 " : dist[i] * 6 + " ");
      }
      System.out.println();
    }

    pr.close();
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
