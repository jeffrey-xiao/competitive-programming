package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class Debug {

  static final long MOD = 1000000007;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int n, m;
  static int[][] g;
  static ArrayList<HashMap<State, Long>> dp;
  static ArrayList<ArrayList<Integer>> indexes;
  static boolean[][] v;
  static int[] dsu;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    g = new int[n][m];
    indexes = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < n; i++) {
      String in = next();
      indexes.add(new ArrayList<Integer>());
      for (int j = 0; j < m; j++) {
        if (in.charAt(j) == '.')
          g[i][j] = -1;
        else if (in.charAt(j) == '#')
          g[i][j] = 0;
        else {
          g[i][j] = -2;
          indexes.get(i).add(j);
        }
      }
    }
    out.println(bf());
    dp = new ArrayList<HashMap<State, Long>>();
    dp.add(new HashMap<State, Long>());
    dp.add(new HashMap<State, Long>());
    for (int i = 0; i < n; i++) {
      int sz = indexes.get(i).size();
      dp.get(i % 2).clear();
      for (int j = 0; j < (1 << sz); j++) {
        int[] newRow = new int[m];
        for (int k = 0; k < m; k++)
          newRow[k] = g[i][k];
        for (int k = 0; k < sz; k++) {
          newRow[indexes.get(i).get(k)] = (j & 1 << k) > 0 ? -1 : 0;
        }
        int id = 1;
        for (int k = 0; k < m; k++) {
          if (newRow[k] == -1)
            newRow[k] = id++;
          if (k + 1 < m && newRow[k] != 0 && newRow[k + 1] == -1)
            newRow[k + 1] = newRow[k];
        }

        if (i == 0) {
          dp.get(i).put(new State(newRow, true), 1l);
        } else {
          int[] ogrow = Arrays.copyOf(newRow, newRow.length);
          main:
          for (Map.Entry<State, Long> e : dp.get((i - 1) % 2).entrySet()) {
            newRow = Arrays.copyOf(ogrow, ogrow.length);
            State s = e.getKey();
            dsu = new int[m];
            HashSet<Integer> hs = new HashSet<Integer>();
            for (int k = 0; k < m; k++) {
              hs.add(s.row[k]);
              dsu[k] = k;
            }
            for (int k = 0; k < m - 1; k++) {
              if (newRow[k] == newRow[k + 1] && newRow[k] != 0)
                merge(k, k + 1);
            }
            for (int k = 0; k < m; k++) {
              for (int l = k + 1; l < m; l++) {
                if (s.row[k] != 0 && s.row[l] != 0 && newRow[k] != 0 && newRow[l] != 0 && s.row[k] == s.row[l] && newRow[k] == newRow[l]) {
                  continue main;
                }
              }
            }

            boolean hasCurr = false;
            boolean hasPrev = false;
            for (int k = 0; k < m; k++) {
              for (int l = k + 1; l < m; l++) {
                if (newRow[k] != 0 && newRow[l] != 0 && s.row[k] != 0 && s.row[l] != 0 && s.row[k] == s.row[l]) {
                  merge(k, l);
                }
              }
              if (!s.canEmpty && newRow[k] != 0)
                continue main;
              if (newRow[k] != 0)
                hasCurr = true;
              if (s.row[k] != 0)
                hasPrev = true;
            }
            for (Integer k : hs) {
              if (k == 0)
                continue;
              boolean valid = false;
              for (int l = 0; l < m; l++) {
                if (s.row[l] == k && newRow[l] != 0)
                  valid = true;
              }
              if (!valid && hasCurr)
                continue main;
            }
            if (!hasCurr && hs.size() > 2)
              continue main;

            for (int k = 0; k < m; k++)
              if (newRow[k] != 0)
                newRow[k] = find(k) + 1;
            id = 1;
            HashMap<Integer, Integer> convert = new HashMap<Integer, Integer>();
            for (int k = 0; k < m; k++) {
              if (newRow[k] != 0 && !convert.containsKey(newRow[k]))
                convert.put(newRow[k], id++);
              if (newRow[k] != 0)
                newRow[k] = convert.get(newRow[k]);
            }
            boolean canEmpty = false;
            if (hasCurr)
              canEmpty = true;
            else if (!hasCurr && hasPrev)
              canEmpty = false;
            else if (!hasCurr && !hasPrev)
              canEmpty = s.canEmpty;
            add(i % 2, new State(newRow, canEmpty), e.getValue());
          }
        }
      }
    }
    long ans = 0;
    for (Map.Entry<State, Long> e : dp.get((n - 1) % 2).entrySet()) {
      HashSet<Integer> hs = new HashSet<Integer>();
      State s = e.getKey();
      for (int i = 0; i < m; i++)
        hs.add(s.row[i]);
      if (hs.size() <= 2) {
        ans = (ans + e.getValue()) % MOD;
      }
    }
    out.println(ans);
    out.close();
  }

  private static int find(int x) {
    return x == dsu[x] ? x : (dsu[x] = find(dsu[x]));
  }

  private static void merge(int x, int y) {
    int rx = find(x);
    int ry = find(y);
    dsu[rx] = ry;
  }

  private static int bf() {

    ArrayList<Point> p = new ArrayList<Point>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (g[i][j] == -2)
          p.add(new Point(i, j));
      }
    }
    int res = 0;
    for (int i = 0; i < (1 << p.size()); i++) {
      for (int j = 0; j < p.size(); j++) {
        if ((i & 1 << j) > 0) {
          g[p.get(j).x][p.get(j).y] = -1;
        } else {
          g[p.get(j).x][p.get(j).y] = 0;
        }
      }
      int cnt = 0;
      v = new boolean[n][m];
      boolean valid = true;
      for (int k = 0; k < n; k++) {
        for (int l = 0; l < m; l++) {
          if (!v[k][l] && g[k][l] != 0) {
            valid &= dfs(k, l, -1, -1);
            cnt++;
          }
        }
      }
      if (cnt <= 1 && valid) {
        res++;
      }
    }
    return res;

  }

  static boolean dfs(int i, int j, int pi, int pj) {
    v[i][j] = true;
    boolean valid = true;
    for (int z = 0; z < 4; z++) {
      int ni = i + movex[z];
      int nj = j + movey[z];
      if (ni < 0 || nj < 0 || ni >= n || nj >= m || (ni == pi && nj == pj) || g[ni][nj] == 0)
        continue;
      if (v[ni][nj]) {
        valid = false;
        continue;
      }
      valid &= dfs(ni, nj, i, j);
    }
    return valid;
  }

  static void add(int i, State s, long cnt) {
    if (!dp.get(i).containsKey(s))
      dp.get(i).put(s, 0l);
    dp.get(i).put(s, (dp.get(i).get(s) + cnt) % MOD);
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

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class State {
    int[] row;
    boolean canEmpty;

    State(int[] row, boolean canEmpty) {
      this.row = Arrays.copyOf(row, row.length);
      this.canEmpty = canEmpty;
    }

    public int hashCode() {
      int res = 0;
      for (int i = 0; i < row.length; i++)
        res = (res * 31 + row[i]);
      return res * 31 + (canEmpty ? 1 : 0);
    }

    public boolean equals(Object o) {
      if (o instanceof State) {
        State s = (State) o;
        for (int i = 0; i < row.length; i++)
          if (s.row[i] != row[i])
            return false;
        return canEmpty == s.canEmpty;
      }
      return false;
    }

  }
}
