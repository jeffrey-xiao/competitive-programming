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

public class Snakes_And_Ladders_The_Quickest_Way_Up {

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

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int[] to = new int[101];
      int[] dist = new int[101];
      for (int i = 1; i <= 100; i++) {
        to[i] = -1;
        dist[i] = 1 << 30;
      }
      dist[1] = 0;
      for (int i = 0; i < n; i++)
        to[readInt()] = readInt();
      n = readInt();
      for (int i = 0; i < n; i++)
        to[readInt()] = readInt();
      Queue<Integer> q = new ArrayDeque<Integer>();
      q.offer(1);
      while (!q.isEmpty()) {
        int curr = q.poll();
        for (int i = 1; i <= 6; i++) {
          if (curr + i > 100)
            continue;
          int next = curr + i;
          if (to[next] != -1)
            next = to[next];
          if (dist[curr] + 1 < dist[next]) {
            dist[next] = dist[curr] + 1;
            q.offer(next);
          }
        }
      }
      out.println(dist[100] == 1 << 30 ? -1 : dist[100]);
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