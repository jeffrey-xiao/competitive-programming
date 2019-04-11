package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DMOPC_2015_Line_Graph {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] id, sz;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int k = readInt();
    id = new int[k];
    sz = new int[k];
    int cost = 0;
    for (int i = 0; i < k; i++) {
      id[i] = i;
      sz[i] = 1;
    }
    ArrayList<Edge> e = new ArrayList<Edge>();
    for (int i = 0; i < n - 1; i++) {
      e.add(new Edge(i % k, (i + 1) % k, readInt()));
    }
    Collections.sort(e);
    for (int i = 0; i < e.size() && k > 1; i++) {
      int ri = find(e.get(i).a);
      int rj = find(e.get(i).b);
      if (ri != rj) {
        k--;
        merge(ri, rj);
        cost += e.get(i).c;
      }
    }
    out.println(cost);
    out.close();
  }

  static int find(int i) {
    return i == id[i] ? i : (id[i] = find(id[i]));
  }

  static void merge(int i, int j) {
    if (sz[i] > sz[j]) {
      sz[i] += sz[j];
      id[j] = i;
    } else {
      sz[j] += sz[i];
      id[i] = j;
    }
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

  static class Edge implements Comparable<Edge> {
    int a, b, c;

    Edge(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    @Override
    public int compareTo(Edge e) {
      return c - e.c;
    }
  }
}
