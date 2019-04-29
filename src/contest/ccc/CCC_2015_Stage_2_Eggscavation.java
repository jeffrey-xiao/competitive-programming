package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CCC_2015_Stage_2_Eggscavation {

  static final int BIT_SIZE = 100001;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, K, M, T;
  static int[][] val;
  static int[] bit = new int[BIT_SIZE];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    val = new int[N + 2][N + 2];

    M = readInt();
    for (int i = 0; i < M; i++) {
      int len = readInt();
      int[] R = new int[len];
      int[] C = new int[len];

      for (int j = 0; j < len; j++) {
        R[j] = readInt();
        C[j] = readInt();
      }

      for (int j = 1; j < 1 << len; j++) {
        int sz = 0;
        int minR = 1, maxR = N, minC = 1, maxC = N;
        for (int k = 0; k < len; k++) {
          if ((j & 1 << k) > 0) {
            sz++;

            int u = Math.max(0, R[k] - K + 1);
            int d = R[k];

            int l = Math.max(0, C[k] - K + 1);
            int r = C[k];
            minR = Math.max(minR, u);
            maxR = Math.min(maxR, d);

            minC = Math.max(minC, l);
            maxC = Math.min(maxC, r);
          }
        }
        if (minR <= maxR && minC <= maxC) {
          maxR++;
          maxC++;
          if (sz % 2 == 0) {
            val[minR][minC]--;
            val[minR][maxC]++;
            val[maxR][minC]++;
            val[maxR][maxC]--;
          } else {
            val[minR][minC]++;
            val[minR][maxC]--;
            val[maxR][minC]--;
            val[maxR][maxC]++;
          }
        }
      }
    }

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        val[i][j] += val[i - 1][j] + val[i][j - 1] - val[i - 1][j - 1];
      }
    }

    ArrayList<TreeSet<Pair>> rows = new ArrayList<TreeSet<Pair>>();

    rows.add(new TreeSet<Pair>());
    for (int i = 1; i <= N - K + 1; i++) {
      rows.add(new TreeSet<Pair>());
      for (int j = 1; j <= N - K + 1; j++) {
        if (val[i][j] != 0) {
          update(val[i][j], 1);
          rows.get(i).add(new Pair(j, val[i][j]));
        }
      }
    }

    T = readInt();

    double total = (N - K + 1) * (N - K + 1);
    for (int t = 0; t < T; t++) {
      int type = readInt();
      if (type == 1) {
        int r = readInt();
        int c = readInt();

        int left = Math.max(1, c - K + 1);
        int right = Math.min(N - K + 1, c);
        int top = Math.min(N - K + 1, r);
        int bot = Math.max(1, r - K + 1);
        for (int i = top; i >= bot; i--) {
          Pair remove = rows.get(i).ceiling(new Pair(left, 0));
          while (remove != null && remove.col <= right) {
            update(remove.val, -1);
            rows.get(i).remove(remove);
            remove = rows.get(i).ceiling(new Pair(left, 0));
          }
        }
      } else if (type == 2) {
        int amt = readInt();
        out.printf("%.5f%n", (query(BIT_SIZE - 1) - query(amt - 1)) / total);
      }
    }

    out.close();
  }

  static void update(int x, int val) {
    if (x == 0)
      return;
    for (int i = x; i < BIT_SIZE; i += (i & -i))
      bit[i] += val;
  }

  static int query(int x) {
    x = Math.min(BIT_SIZE - 1, x);
    int sum = 0;
    for (int i = x; i > 0; i -= (i & -i))
      sum += bit[i];
    return sum;
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

  static class Pair implements Comparable<Pair> {
    int col, val;

    Pair(int col, int val) {
      this.col = col;
      this.val = val;
    }

    @Override
    public int compareTo(Pair o) {
      return col - o.col;
    }
  }
}
