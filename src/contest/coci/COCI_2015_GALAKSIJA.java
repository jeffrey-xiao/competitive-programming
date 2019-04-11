package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class COCI_2015_GALAKSIJA {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static int[] a, b, c;
  static int[] order, id, sz;
  static ArrayList<HashMap<Integer, Long>> states;
  static long[] ans;

  static int[] v;
  static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();

    a = new int[n];
    b = new int[n];
    c = new int[n];
    order = new int[n];
    ans = new long[n];
    states = new ArrayList<HashMap<Integer, Long>>();
    id = new int[n];
    sz = new int[n];
    v = new int[n];

    for (int i = 0; i < n; i++)
      adj.add(new ArrayList<Edge>());

    for (int i = 0; i < n - 1; i++) {
      a[i] = readInt() - 1;
      b[i] = readInt() - 1;
      c[i] = readInt();
      adj.get(b[i]).add(new Edge(a[i], c[i]));
      adj.get(a[i]).add(new Edge(b[i], c[i]));
    }

    dfs(0, -1);

    for (int i = 0; i < n; i++) {
      states.add(new HashMap<Integer, Long>());
      states.get(i).put(v[i], 1l);
      id[i] = i;
      sz[i] = 1;
    }

    for (int i = 0; i < n - 1; i++)
      order[i] = readInt() - 1;

    long total = 0;
    for (int i = n - 2; i >= 0; i--) {
      int id1 = find(a[order[i]]);
      int id2 = find(b[order[i]]);

      merge(id1, id2);

      if (id2 == id[id2]) {
        int temp = id1;
        id1 = id2;
        id2 = temp;
      }
      for (Map.Entry<Integer, Long> entry : states.get(id2).entrySet()) {
        int nextKey = entry.getKey();
        if (states.get(id1).containsKey(nextKey)) {
          total += entry.getValue() * states.get(id1).get(nextKey);
          states.get(id1).put(nextKey, states.get(id1).get(nextKey) + entry.getValue());
        } else {
          states.get(id1).put(nextKey, entry.getValue());
        }
      }
      ans[i] = total;
    }

    for (int i = 0; i < n; i++)
      out.println(ans[i]);
    out.close();
  }

  static void dfs(int i, int par) {
    for (Edge e : adj.get(i)) {
      if (e.dest != par) {
        v[e.dest] = v[i] ^ e.val;
        dfs(e.dest, i);
      }
    }
  }

  static int find(int i) {
    return i == id[i] ? i : (id[i] = find(id[i]));
  }

  static void merge(int i, int j) {
    if (sz[i] >= sz[j]) {
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

  static class Edge {
    int dest, val;

    Edge(int dest, int val) {
      this.dest = dest;
      this.val = val;
    }
  }
}