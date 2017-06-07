package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Subset_Component {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] id = new int[64];
  static int cnt = 0;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Edge>());
      long d = readLong();
      int prev = -1;
      for (int j = 0; j < 64; j++) {
        if ((d & 1l << j) > 0) {
          if (prev != -1)
            adj.get(i).add(new Edge(j, prev));
          prev = j;
        }
      }
    }
    long ans = 0;
    for (int i = 0; i < (1 << n); i++) {
      id = new int[64];
      for (int j = 0; j < 64; j++)
        id[j] = j;
      cnt = 64;
      for (int j = 0; j < n; j++) {
        if ((i & 1l << j) > 0) {
          for (Edge e : adj.get(j)) {
            if (find(e.a) != find(e.b))
              merge(e.a, e.b);
          }
        }
      }
      ans += cnt;
    }
    out.println(ans);
    out.close();
  }

  static int find (int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
  }

  static void merge (int x, int y) {
    cnt--;
    int rx = find(x);
    int ry = find(y);
    id[ry] = rx;
  }

  static class Edge {
    int a, b;

    Edge (int a, int b) {
      this.a = a;
      this.b = b;
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
