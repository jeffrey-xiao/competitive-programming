package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MEC_P4 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static int[] id, sz, status;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    id = new int[N];
    sz = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = i;
      sz[i] = 1;
    }

    Edge[] e = new Edge[M];
    status = new int[M];

    for (int i = 0; i < M; i++) {
      int u = readInt() - 1;
      int v = readInt() - 1;
      int cost = readInt();

      e[i] = new Edge(u, v, cost, i);
    }
    Arrays.sort(e);

    ArrayList<Edge> prev = new ArrayList<Edge>();
    int prevCost = 0;
    for (int i = 0; i < M; i++) {
      if (e[i].weight == prevCost) {
        prev.add(e[i]);
      } else {
        prevCost = e[i].weight;

        ArrayList<Edge> possiblyUseful = new ArrayList<Edge>();
        for (Edge edge : prev) {
          if (find(edge.x) == find(edge.y)) {
            status[edge.index] = -1;
          } else {
            possiblyUseful.add(edge);
          }
        }

        boolean allUseful = true;
        for (Edge edge : possiblyUseful) {
          int rx = find(edge.x);
          int ry = find(edge.y);
          if (rx == ry) {
            allUseful = false;
          } else {
            merge(rx, ry);
          }
        }

        if (allUseful) {
          for (Edge edge : possiblyUseful) {
            status[edge.index] = 1;
          }
        }

        prev.clear();
        prev.add(e[i]);
      }
    }

    ArrayList<Edge> possiblyUseful = new ArrayList<Edge>();
    for (Edge edge : prev) {
      if (find(edge.x) == find(edge.y)) {
        status[edge.index] = -1;
      } else {
        possiblyUseful.add(edge);
      }
    }

    boolean allUseful = true;
    for (Edge edge : possiblyUseful) {
      int rx = find(edge.x);
      int ry = find(edge.y);
      if (rx == ry) {
        allUseful = false;
      } else {
        merge(rx, ry);
      }
    }

    if (allUseful) {
      for (Edge edge : possiblyUseful) {
        status[edge.index] = 1;
      }
    }

    for (int i = 0; i < M; i++) {
      if (status[i] == -1) {
        out.println("not useful");
      } else if (status[i] == 0) {
        out.println("so so");
      } else {
        out.println("useful");
      }
    }

    out.close();
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

  static class Edge implements Comparable<Edge> {
    int x, y, weight, index;

    Edge(int x, int y, int weight, int index) {
      this.x = x;
      this.y = y;
      this.weight = weight;
      this.index = index;
    }

    @Override
    public int compareTo(Edge o) {
      return weight - o.weight;
    }
  }
}
