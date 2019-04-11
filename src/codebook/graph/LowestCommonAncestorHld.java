/*
 * An algorithm to find the lowest common ancestor (LCA) of two nodes in a tree using heavy light decomposition (HLD).
 * 
 * Time complexity:
 *  - Preprocessing: O(V + E)
 *  - Query: O(log V)
 */

package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LowestCommonAncestorHld {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] depth, parent, chain, size, head;
  static int n, q, chainNum;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    // number of nodes
    n = readInt();
    // number of queries
    q = readInt();

    depth = new int[n];
    parent = new int[n];
    chain = new int[n];
    size = new int[n];
    head = new int[n];

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<Integer>());
      head[i] = -1;
    }

    for (int i = 0; i < n - 1; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    dfs(0, 0, -1);
    getHld(0, -1);

    for (int i = 0; i < q; i++)
      out.println(getLca(readInt() - 1, readInt() - 1) + 1);

    out.close();
  }

  static int getLca(int i, int j) {
    while (chain[i] != chain[j]) {
      if (depth[head[chain[i]]] < depth[head[chain[j]]])
        j = parent[head[chain[j]]];
      else
        i = parent[head[chain[i]]];
    }
    if (depth[i] < depth[j])
      return i;
    return j;
  }

  static void getHld(int i, int prev) {
    if (head[chainNum] == -1) {
      head[chainNum] = i;
    }
    chain[i] = chainNum;
    int maxIndex = -1;
    for (int j : adj.get(i))
      if (j != prev && (maxIndex == -1 || size[maxIndex] < size[j]))
        maxIndex = j;
    if (maxIndex != -1)
      getHld(maxIndex, i);
    for (int j : adj.get(i))
      if (j != prev && j != maxIndex) {
        chainNum++;
        getHld(j, i);
      }
  }

  static void dfs(int i, int d, int prev) {
    depth[i] = d;
    parent[i] = prev;
    size[i] = 1;
    for (int j : adj.get(i)) {
      if (j != prev) {
        dfs(j, d + 1, i);
        size[i] += size[j];
      }
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
}
