package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class ACM_NEERC_2014_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, chain;
  static int[] chainId, depth, sz, chainHead, parent;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static ArrayList<TreeMap<String, TreeSet<Attribute>>> toDepth = new ArrayList<TreeMap<String, TreeSet<Attribute>>>();
  static ArrayList<ArrayList<Attribute>> properties = new ArrayList<ArrayList<Attribute>>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    chainId = new int[N];
    chainHead = new int[N];
    depth = new int[N];
    sz = new int[N];
    parent = new int[N];

    Arrays.fill(chainHead, -1);

    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Integer>());

    for (int i = 0; i < N; i++) {
      toDepth.add(new TreeMap<String, TreeSet<Attribute>>());
      properties.add(new ArrayList<Attribute>());
      int par = readInt() - 1;
      int num = readInt();
      if (par != -1) {
        adj.get(par).add(i);
        adj.get(i).add(par);
      }
      for (int j = 0; j < num; j++) {
        String[] in = next().split("=");
        properties.get(i).add(new Attribute(in[0], in[1]));
      }
    }

    dfs(0, -1, 0);
    hld(0, -1);

    int Q = readInt();

    for (int i = 0; i < Q; i++) {
      int component = readInt() - 1;
      String key = next();
      boolean found = false;
      while (component != -1) {
        TreeSet<Attribute> ts = toDepth.get(chainId[component]).get(key);
        if (!found && ts != null && ts.floor(new Attribute("", "", depth[component])) != null) {
          found = true;
          System.out.println(toDepth.get(chainId[component]).get(key).floor(new Attribute("", "", depth[component])).value);
          System.out.flush();
          break;
        }
        component = parent[chainHead[chainId[component]]];
      }
      if (!found) {
        System.out.println("N/A");
        System.out.flush();
      }
    }
    out.close();
  }

  static void hld (int u, int par) {
    chainId[u] = chain;
    for (int i = 0; i < properties.get(u).size(); i++) {
      String key = properties.get(u).get(i).key;
      String value = properties.get(u).get(i).value;
      if (toDepth.get(chainId[u]).get(key) == null)
        toDepth.get(chainId[u]).put(key, new TreeSet<Attribute>());
      toDepth.get(chainId[u]).get(key).add(new Attribute(key, value, depth[u]));
    }

    if (chainHead[chain] == -1)
      chainHead[chain] = u;

    int maxIndex = -1;
    for (int v : adj.get(u))
      if (v != par && (maxIndex == -1 || sz[maxIndex] < sz[v]))
        maxIndex = v;

    if (maxIndex != -1)
      hld(maxIndex, u);

    for (int v : adj.get(u))
      if (v != par && v != maxIndex) {
        chain++;
        hld(v, u);
      }
  }

  static void dfs (int u, int par, int d) {
    sz[u] = 1;
    depth[u] = d;
    parent[u] = par;
    for (int v : adj.get(u))
      if (v != par) {
        dfs(v, u, d + 1);
        sz[u] += sz[v];
      }
  }

  static class Attribute implements Comparable<Attribute> {
    String key, value;
    int depth;

    Attribute (String key, String value) {
      this(key, value, 0);
    }

    Attribute (String key, String value, int depth) {
      this.key = key;
      this.value = value;
      this.depth = depth;
    }

    @Override
    public int compareTo (Attribute a) {
      return depth - a.depth;
    }

    @Override
    public String toString () {
      return String.format("%s:%s", key, value);
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
