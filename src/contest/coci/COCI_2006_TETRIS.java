package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_TETRIS {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int[][][] possible = new int[][][]{{{}, {0, 0, 0}}, {{0}}, {{0, 1}, {-1}}, {{-1, 0}, {1}}, {{0, 0}, {-1}, {1}, {-1, 1}}, {{0, 0}, {0}, {-2}, {1, 0}}, {{0, 0}, {0}, {2}, {0, -1}}};
    int c = readInt();
    int n = readInt() - 1;
    int[] field = new int[c];
    for (int x = 0; x < c; x++)
      field[x] = readInt();
    int count = 0;
    for (int x = 0; x < possible[n].length; x++) {
      nextBlock:
      for (int y = 0; y < c; y++) {
        for (int z = 0; z < possible[n][x].length; z++) {
          if (y + z + 1 >= c || field[y + z + 1] - field[y + z] != possible[n][x][z])
            continue nextBlock;
        }
        count++;
      }
    }
    System.out.println(count);
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
