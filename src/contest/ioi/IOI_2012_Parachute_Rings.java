package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_2012_Parachute_Rings {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;

  static ArrayList<DSU> val;
  static int[] degree;
  static ArrayList<Integer> u, v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    u = new ArrayList<Integer>();
    v = new ArrayList<Integer>();
    degree = new int[N];

    val = new ArrayList<DSU>();

    val.add(new DSU(N, -1));

    for (int i = 0; i < M; i++) {
      int x = readInt();
      if (x == -1) {
        int ans = 0;

        for (int k = 0; k < val.size(); k++) {
          if (val.get(k).exclude == -1) {
            if (val.get(k).cycleSize == 0)
              ans = N;
            else if (val.get(k).cycles == 1)
              ans = val.get(k).cycleSize;
          } else {
            if (val.get(k).cycles == 0 && val.get(k).bigNodes == 0)
              ans++;
          }
        }
        out.println(ans);

      } else {
        int y = readInt();
        u.add(x);
        v.add(y);

        degree[x]++;
        degree[y]++;

        // rebuild excluding u[i] and all neighbors of u[i]
        int rebuildIndex = x;

        if (degree[y] > degree[rebuildIndex])
          rebuildIndex = y;

        if (degree[rebuildIndex] < 3 || degree[rebuildIndex] >= 5 || (degree[rebuildIndex] == 3 && (val.size() == 4 || (val.size() == 1 && val.get(0).exclude != -1))) || (degree[rebuildIndex] == 4 && val.size() == 1)) {
          for (int k = 0; k < val.size(); k++)
            val.get(k).merge(x, y);
          continue;
        } else if (degree[rebuildIndex] == 3) {
          val.clear();
          val.add(new DSU(N, rebuildIndex));
          for (int j = 0; j < u.size(); j++)
            if (u.get(j) == rebuildIndex)
              val.add(new DSU(N, v.get(j)));
            else if (v.get(j) == rebuildIndex)
              val.add(new DSU(N, u.get(j)));

          assert val.size() == 4;
        } else if (degree[rebuildIndex] == 4) {
          val.clear();
          val.add(new DSU(N, rebuildIndex));
        }

        for (int j = 0; j < u.size(); j++) {
          for (int k = 0; k < val.size(); k++) {
            val.get(k).merge(u.get(j), v.get(j));
          }
        }
      }
    }

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

  static class DSU {
    int size, cycleSize, cycles, exclude, bigNodes;
    int[] id, sz, deg;

    DSU(int size, int exclude) {
      this.cycleSize = 0;
      this.cycles = 0;
      this.size = size;
      this.exclude = exclude;
      id = new int[size];
      deg = new int[size];
      sz = new int[size];

      for (int i = 0; i < size; i++) {
        id[i] = i;
        sz[i] = 1;
      }
    }

    int find(int u) {
      return u == id[u] ? u : (id[u] = find(id[u]));
    }

    void merge(int u, int v) {
      if (u == exclude || v == exclude)
        return;

      deg[u]++;
      deg[v]++;
      if (deg[u] == 3)
        bigNodes++;
      if (deg[v] == 3)
        bigNodes++;

      u = find(u);
      v = find(v);

      if (u == v) {
        cycleSize += sz[u];
        cycles++;
        return;
      }

      if (sz[u] > sz[v]) {
        sz[u] += sz[v];
        id[v] = u;
      } else {
        sz[v] += sz[u];
        id[u] = v;
      }
    }
  }
}
