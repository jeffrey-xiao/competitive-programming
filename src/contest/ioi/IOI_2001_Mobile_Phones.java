package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2001_Mobile_Phones {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[][] tree;
  static int n;

  public static void main (String[] args) throws IOException {
    readInt();
    n = readInt();
    tree = new int[n + 1][n + 1];
    int command = readInt();
    while (command != 3) {
      if (command == 1) {
        int x = readInt() + 1;
        int y = readInt() + 1;
        int a = readInt();
        update(x, y, a);
      } else {
        int x1 = readInt();
        int y1 = readInt();
        int x2 = readInt() + 1;
        int y2 = readInt() + 1;
        int total = freqTo(x2, y2) + freqTo(x1, y1) - freqTo(x1, y2) - freqTo(x2, y1);
        System.out.println(total);
      }
      command = readInt();
    }
  }

  static void update (int idxx, int idxy, int val) {
    int y = idxy;
    while (idxx <= n) {
      idxy = y;
      while (idxy <= n) {
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

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
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
