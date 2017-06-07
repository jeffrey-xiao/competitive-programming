package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Checkerboard_Summation_Hard {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int nx;
  static int ny;
  static int[][] tree;

  public static void main (String[] args) throws IOException {
    nx = readInt();
    ny = readInt();
    tree = new int[nx + 2][ny + 2];
    int command = readInt();
    while (command != 0) {
      if (command == 1) {
        int x = readInt();
        int y = readInt();
        int a = readInt();
        if ((x + y) % 2 != 0)
          a = -a;
        int sum = freqTo(x, y) + freqTo(x - 1, y - 1) - freqTo(x - 1, y) - freqTo(x, y - 1);
        update(x, y, a - sum);
      } else {
        int x1 = readInt() - 1;
        int y1 = readInt() - 1;
        int x2 = readInt();
        int y2 = readInt();

        int total = freqTo(x2, y2) + freqTo(x1, y1) - freqTo(x1, y2) - freqTo(x2, y1);
        if ((x1 + y1) % 2 != 0)
          total = -total;
        System.out.println(total);
      }
      command = readInt();
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static void update (int idxx, int idxy, int val) {
    int y = idxy;

    while (idxx <= nx) {
      idxy = y;
      while (idxy <= ny) {
        tree[idxx][idxy] += val;
        idxy += (idxy & -idxy);
      }
      idxx += (idxx & -idxx);
    }
  }

  static int freqTo (int idxx, int idxy) {
    int sum = 0;
    int y = idxy;
    while (idxx > 0) {
      idxy = y;
      while (idxy > 0) {
        sum += tree[idxx][idxy];
        idxy -= (idxy & -idxy);
      }
      idxx -= (idxx & -idxx);
    }
    return sum;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
