/*
 * Karger's algorithm is a randomized algorithm that computes the minimum cut of a connected graph.
 * The probability for success is 1 / (N choose 2)
 * 
 * Time complexity: O(E)
 */

package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinRandomizedCutKarger {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] id, sz;
  static ArrayList<Edge> e;
  static int n, m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    id = new int[n];
    sz = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      sz[i] = 1;
    }
    for (int i = 0; i < m; i++)
      e.add(new Edge(readInt() - 1, readInt() - 1, readInt()));

    int currSize = e.size();
    int nodesLeft = n;

    while (nodesLeft > 2) {
      int ran = (int) (Math.random() * currSize);
      int rx = find(e.get(ran).a);
      int ry = find(e.get(ran).b);
      if (rx != ry) {
        merge(rx, ry);
        nodesLeft--;
      }
      Edge temp = e.get(--currSize);
      e.set(currSize, e.get(ran));
      e.set(ran, temp);
    }
    int ans = 0;
    for (Edge edge : e)
      if (find(edge.a) != find(edge.b))
        ans += edge.cost;
    out.println(ans);
    out.close();
  }

  static int find(int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
  }

  static void merge(int x, int y) {
    if (sz[x] > sz[y]) {
      sz[x] += sz[y];
      id[y] = x;
    } else {
      sz[y] += sz[x];
      id[x] = y;
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

  static class Edge {
    int a, b, cost;

    Edge(int a, int b, int cost) {
      this.a = a;
      this.b = b;
      this.cost = cost;
    }
  }
}
