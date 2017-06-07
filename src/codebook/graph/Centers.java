/*
 * Tree algorithms that compute the center, centroid, and the diameter of a tree.
 *
 * Time complexity:
 *  - getCenters: O(V + E)
 *  - getCentroid: O(V + E)
 *  - getDiameter: O(V + E)
 */

package codebook.graph;

import java.util.ArrayList;

public class Centers {
  public static ArrayList<Integer> getCenters (ArrayList<ArrayList<Integer>> adj) {
    int n = adj.size();
    ArrayList<Integer> currLeaves = new ArrayList<Integer>();
    int[] degree = new int[n];
    for (int i = 0; i < n; i++) {
      degree[i] = adj.get(i).size();
      if (degree[i] <= 1)
        currLeaves.add(i);
    }
    int removedLeaves = currLeaves.size();
    while (removedLeaves < n) {
      ArrayList<Integer> newLeaves = new ArrayList<Integer>();
      for (int i : currLeaves)
        for (int j : adj.get(i))
          if (--degree[j] == 1)
            newLeaves.add(j);
      currLeaves = newLeaves;
      removedLeaves += currLeaves.size();
    }
    return currLeaves;
  }

  public static int getCentroid (ArrayList<ArrayList<Integer>> adj, int curr, int par) {
    int n = adj.size();
    int sz = 1;
    boolean valid = true;
    for (int next : adj.get(curr)) {
      if (next == par)
        continue;
      int ret = getCentroid(adj, next, curr);
      if (ret >= 0)
        return ret;
      valid &= -ret <= n / 2;
      sz += -ret;
    }
    valid &= n - sz <= n / 2;
    return valid ? curr : -sz;
  }

  static class State {
    int node, depth;

    State (int node, int depth) {
      this.node = node;
      this.depth = depth;
    }
  }

  public static int getDiameter (ArrayList<ArrayList<Integer>> adj) {
    int far = dfs(adj, 0, -1, 0).node;
    return dfs(adj, far, -1, 0).depth;
  }

  private static State dfs (ArrayList<ArrayList<Integer>> adj, int curr, int par, int depth) {
    int maxDepth = depth;
    for (int next : adj.get(curr))
      if (next != par)
        maxDepth = dfs(adj, next, curr, depth + 1).depth;
    return new State(curr, maxDepth);
  }
}
