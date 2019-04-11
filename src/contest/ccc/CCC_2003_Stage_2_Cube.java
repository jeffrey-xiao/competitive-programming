package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_Cube {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int[][][] cube = new int[12][12][12];
  static boolean[][][] empty;
  ;
  static int[] movex = {0, 0, 0, 0, -1, 1};
  static int[] movey = {0, 0, -1, 1, 0, 0};
  static int[] movez = {-1, 1, 0, 0, 0, 0};
  static int cnt = 0;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n;
    while ((n = readInt()) != 0) {
      cube = new int[12][12][12];
      for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++) {
          char[] in = (" " + next()).toCharArray();
          for (int k = 1; k <= n; k++)
            cube[i][j][k] = in[k];
        }
      boolean valid = true;
    main:
      for (int q = 0; q < 6; q++) {
        for (int x = 1; x <= n; x++) {
          for (int y = 1; y <= n; y++) {
            for (int z = 1; z <= n; z++) {
              if (cube[x + movex[q]][y + movey[q]][z + movez[q]] == 0) {
                empty = new boolean[12][12][12];
                cnt = 0;
                compute(x, y, z, movex[q], movey[q], movez[q]);
                if (cnt != n * n * n) {
                  valid = false;
                  break main;
                }
              }
            }
          }
        }
      }
      if (valid)
        System.out.println("Yes");
      else
        System.out.println("No");
    }

    pr.close();
  }

  private static void compute(int x, int y, int z, int i, int j, int k) {
    if (empty[x][y][z])
      return;
    empty[x][y][z] = true;
    cnt++;
    for (int q = 0; q < 6; q++) {
      int nx = x + movex[q];
      int ny = y + movey[q];
      int nz = z + movez[q];
      if (cube[x][y][z] == cube[nx][ny][nz])
        compute(nx, ny, nz, i, j, k);
    }
    if (cube[x + i][y + j][z + k] != 0)
      compute(x + i, y + j, z + k, i, j, k);
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
