package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Chemistry_Homework {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[][] adj;
  static int[] size;
  static boolean[] v;
  static boolean isPossible = true;
  static int C = 0;
  static int H = 0;
  static int energy = 0;
  static int n, m;

  public static void main(String[] args) throws IOException {
    n = readInt();
    m = readInt();
    adj = new int[n + 1][n + 1];
    size = new int[n + 1];
    v = new boolean[n + 1];
    for (int x = 0; x < m; x++) {
      int a = readInt();
      int b = readInt();
      adj[a][b]++;
      adj[b][a]++;
    }
    for (int x = 1; x <= n; x++) {
      for (int y = 1; y <= n; y++) {
        size[x] += adj[x][y];
      }
      if (size[x] != 1 && size[x] != 4) {
        System.out.println("Impossible");
        return;
      }
    }
    for (int x = 1; x <= n; x++) {
      if (!v[x]) {
        dfs(x);
      }
    }
    if (!isPossible) {
      System.out.println("Impossible.");
    } else {
      System.out.printf("%d%nC%sH%s%n", energy, C == 1 ? "" : C, H == 1 ? "" : H);
    }
  }

  private static void dfs(int i) {
    v[i] = true;
    if (size[i] == 4)
      C++;
    if (size[i] == 1)
      H++;
    for (int x = 1; x <= n; x++) {
      if (adj[i][x] > 0 && !v[x]) {
        if (size[i] == 4 && size[x] == 4 && adj[i][x] == 2) {
          energy += 615;
        } else if (size[i] == 4 && size[x] == 4 && adj[i][x] == 1) {
          energy += 346;
        } else if (((size[i] == 4 && size[x] == 1) || size[i] == 1 && size[x] == 4) && adj[i][x] == 1) {
          energy += 413;
        } else {
        }
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
