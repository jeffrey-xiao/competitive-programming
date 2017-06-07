/*
 * The Hopcroft-Karp algorithm finds the maximal matching in a given bipartite graph using augmenting paths.
 * 
 * Time complexity: O(E sqrt(V))
 */

package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MaxBipartiteMatchingHopcroftKarp {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int NULL = 0;

  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int leftSize, rightSize, n;
  static int[] pair, dist;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    // number of vertices on the left side
    leftSize = readInt();
    // number of vertices on the right side
    rightSize = readInt();
    // number of edges
    n = readInt();
    for (int i = 0; i < leftSize + rightSize + 1; i++)
      adj.add(new ArrayList<Integer>());
    for (int i = 0; i < n; i++) {
      int left = readInt();
      int right = readInt();
      adj.get(left).add(leftSize + right);
    }
    out.println(getMaxMatching());
    out.close();
  }

  static int getMaxMatching () {
    pair = new int[leftSize + rightSize + 1];
    dist = new int[leftSize + rightSize + 1];
    int res = 0;
    while (bfs())
      // attempt to match edges starting from each unmatched left node
      for (int i = 1; i <= leftSize; i++)
        if (pair[i] == NULL)
          res += dfs(i) ? 1 : 0;
    return res;
  }

  static boolean bfs () {
    // all unmatched nodes in left set is connected to left super node
    // all unmatched nodes in right set is connected to right super node
    // both super nodes are simulated using a NULL node
    // we want to find the shortest augmenting path from the left super node to the right super node
    Queue<Integer> q = new ArrayDeque<Integer>();
    for (int i = 1; i <= leftSize; i++) {
      // if a node in the left set is unmatched, we can start an augmenting path from there
      if (pair[i] == NULL) {
        dist[i] = 0;
        q.offer(i);
      } else {
        dist[i] = 1 << 30;
      }
    }

    // initialize the right super node distance to infinity
    dist[NULL] = 1 << 30;

    while (!q.isEmpty()) {
      Integer curr = q.poll();

      if (dist[curr] >= dist[NULL])
        continue;

      for (int next : adj.get(curr)) {
        // we either pick already matched edges to remove them, or an edge
        // from an unmatched right node to the right super node
        if (dist[pair[next]] == 1 << 30) {
          dist[pair[next]] = dist[curr] + 1;
          q.offer(pair[next]);
        }

      }
    }

    // if the right super node can be reached, we can match more edges
    return dist[NULL] != 1 << 30;
  }

  static boolean dfs (int i) {
    if (i == NULL)
      return true;

    for (int j : adj.get(i)) {
      // matching the edges along the shortest augmenting path
      if (dist[pair[j]] == dist[i] + 1) {
        if (dfs(pair[j])) {
          pair[j] = i;
          pair[i] = j;
          return true;
        }
      }
    }

    // if an augmenting path cannot be found, then set the distance to infinity to avoid traversal
    dist[i] = 1 << 30;
    return false;
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
