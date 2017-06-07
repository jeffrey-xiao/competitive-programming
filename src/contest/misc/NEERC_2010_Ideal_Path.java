package contest.misc;

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

public class NEERC_2010_Ideal_Path {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static int[] dist;
  static int[] color;
  static int[] prev;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int m = readInt();
    for (int x = 0; x < n; x++)
      adj.add(new ArrayList<Edge>());
    for (int x = 0; x < m; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      adj.get(a).add(new Edge(b, c));
      adj.get(b).add(new Edge(a, c));
    }
    dist = new int[n];
    color = new int[n];
    prev = new int[n];
    for (int x = 0; x < n; x++) {
      dist[x] = 1 << 30;
      color[x] = 1 << 30;
      prev[x] = -1;
    }
    dist[0] = 0;
    color[0] = -1;
    Queue<Integer> q = new LinkedList<Integer>();
    q.offer(0);
    while (!q.isEmpty()) {
      Integer curr = q.poll();
      for (Edge e : adj.get(curr)) {

        int nextDist = dist[curr] + 1;
        int nextColor = e.color;
        if (e.dest == 28) {
          System.out.println("HERE " + curr + " " + color[curr] + " " + nextDist + " " + nextColor);
        }
        boolean equal = (nextDist == dist[e.dest] && nextColor == color[e.dest]);
        if ((nextDist < dist[e.dest] || (nextDist == dist[e.dest] && nextColor < color[e.dest])) || (equal && (prev[e.dest] == -1 || color[prev[e.dest]] > color[curr]))) {
          dist[e.dest] = nextDist;
          color[e.dest] = nextColor;
          prev[e.dest] = curr;
          q.offer(e.dest);
        }
      }
    }
    System.out.println(dist[n - 1]);
    print(n - 1);
  }

  private static void print (int i) {
    if (i != 0) {
      print(prev[i]);
      System.out.println(i + " " + color[i] + " " + dist[i]);
    }
  }

  static class Edge {
    int dest, color;

    Edge (int dest_, int color_) {
      dest = dest_;
      color = color_;
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
