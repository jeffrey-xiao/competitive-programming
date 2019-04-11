package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CCC_2016_Stage_2_O_Canada {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, G;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    G = readInt();

    HashMap<Integer, Integer> occ = new HashMap<Integer, Integer>();

    for (int i = 0; i < G; i++) {
      int[][] grid = new int[N][N];
      for (int j = 0; j < N; j++) {
        char[] in = br.readLine().trim().toCharArray();
        for (int k = 0; k < N; k++) {
          grid[j][k] = in[k] == 'R' ? 0 : 1;
        }
      }
      for (int j = 0; j < N - 1; j++)
        for (int k = 0; k < N - 1; k++) {
          if (grid[j][k] == 1) {
            grid[j + 1][k] = (grid[j + 1][k] + 1) % 2;
            grid[j][k + 1] = (grid[j][k + 1] + 1) % 2;
            grid[j + 1][k + 1] = (grid[j + 1][k + 1] + 1) % 2;
          }
        }
      int res = 0;
      for (int j = 0; j < N; j++) {
        res = res * 2 + grid[N - 1][j];
        res = res * 2 + grid[j][N - 1];
      }
      if (occ.get(res) == null)
        occ.put(res, 0);
      occ.put(res, occ.get(res) + 1);
    }
    int ans = 0;

    for (Map.Entry<Integer, Integer> e : occ.entrySet())
      ans += e.getValue() * (e.getValue() - 1) / 2;

    out.println(ans);
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
}
