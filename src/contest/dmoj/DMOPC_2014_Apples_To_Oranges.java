package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DMOPC_2014_Apples_To_Oranges {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    int n = readInt();
    int m = readInt();
    for (int i = 0; i < n; i++)
      hm.put(next(), i);
    ArrayList<Edge> e = new ArrayList<Edge>();
    for (int i = 0; i < m; i++) {
      int a = hm.get(next());
      int b = hm.get(next());
      double c = readDouble();
      e.add(new Edge(a, b, c));
    }
    double[] max = new double[n];
    int s = hm.get("APPLES");
    max[s] = 1.0;
    for (int i = 0; i < n; i++)
      for (Edge edge : e)
        max[edge.dest] = Math.max(max[edge.dest], max[edge.orig] * edge.cost);
    double prev = max[s];
    for (int i = 0; i < n; i++)
      for (Edge edge : e)
        max[edge.dest] = Math.max(max[edge.dest], max[edge.orig] * edge.cost);
    if (max[s] - prev > 0.1)
      out.println("YA");
    else
      out.println("NAW");
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

  static class Edge {
    int orig, dest;
    double cost;

    Edge(int orig, int dest, double cost) {
      this.orig = orig;
      this.dest = dest;
      this.cost = cost;
    }
  }
}
