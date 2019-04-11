package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class BOI_2009_Diamonds_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static PrintStream pr = new PrintStream(System.out);
  static int L, M, N;

  public static void main(String[] args) throws IOException {
    L = readInt();
    M = readInt();
    N = readInt();
    int[] grid = new int[N * M * L + M * L];
    for (int x = 1; x <= N; x++) {
      for (int y = 1; y <= M; y++) {
        for (int z = 1; z <= L; z++) {

          grid[i(x, y, z)] = readInt() + grid[i(x - 1, y, z)] + grid[i(x, y - 1, z)] + grid[i(x, y, z - 1)] - grid[i(x - 1, y - 1, z)] - grid[i(x - 1, y, z - 1)] - grid[i(x, y - 1, z - 1)] + grid[i(x - 1, y - 1, z - 1)];

        }
      }
    }
    while (true) {
      String nextLine = br.readLine();
      if (nextLine == null)
        break;
      StringTokenizer next = new StringTokenizer(nextLine);
      int z1 = Integer.parseInt(next.nextToken());
      int y1 = Integer.parseInt(next.nextToken());
      int x1 = Integer.parseInt(next.nextToken());
      int z2 = Integer.parseInt(next.nextToken());
      int y2 = Integer.parseInt(next.nextToken());
      int x2 = Integer.parseInt(next.nextToken());
      int sum = grid[i(x2, y2, z2)] - grid[i(x1, y2, z2)] - grid[i(x2, y1, z2)] - grid[i(x2, y2, z1)] + grid[i(x1, y1, z2)] + grid[i(x2, y1, z1)] + grid[i(x1, y2, z1)] - grid[i(x1, y1, z1)];
      pr.print(sum + "\n");
    }
  }

  static int i(int x, int y, int z) {
    return Math.max(x * N * M + y * M + z, 0);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
