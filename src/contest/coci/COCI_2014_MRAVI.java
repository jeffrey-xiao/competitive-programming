package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2014_MRAVI {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  static int n;
  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
  static double[] need;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    n = readInt();
    need = new double[n];
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Edge>());
    }
    for (int i = 0; i < n - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int c = readInt();
      int d = readInt();
      adj.get(a).add(new Edge(b, c, d));
      adj.get(b).add(new Edge(a, c, d));
    }
    for (int i = 0; i < n; i++) {
      need[i] = Math.max(0, readInt());
    }
    boolean[] v = new boolean[n];
    Queue<Integer> curr = new LinkedList<Integer>();
    Stack<Integer> process = new Stack<Integer>();
    curr.offer(0);
    process.push(0);
    v[0] = true;
    while (!curr.isEmpty()) {
      Integer c = curr.poll();
      for (Edge e : adj.get(c)) {
        if (v[e.dest])
          continue;
        v[e.dest] = true;
        curr.offer(e.dest);
        process.push(e.dest);
      }
    }
    v = new boolean[n];
    while (!process.isEmpty()) {
      Integer c = process.pop();
      v[c] = true;
      for (Edge e : adj.get(c)) {
        if (v[e.dest])
          continue;
        if (e.charged)
          need[e.dest] = Math.max(need[e.dest], Math.sqrt(need[c]) * 100 / e.percent);
        need[e.dest] = Math.max(need[e.dest], need[c] * 100.0 / e.percent);
      }
    }
    System.out.println(need[0]);
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

  static class Edge {
    int dest, percent;
    boolean charged;

    Edge(int dest, int percent, int charged) {
      this.dest = dest;
      this.percent = percent;
      this.charged = charged == 1;
    }
  }
}