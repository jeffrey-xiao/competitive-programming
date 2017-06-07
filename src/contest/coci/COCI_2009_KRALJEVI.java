package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2009_KRALJEVI {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int R, C;
  static char[][] grid;
  static int[][] leftCnt, rightCnt, topCnt, topLeftCnt, topRightCnt;
  static long[][] leftSum, rightSum, topSum, topLeftSum, topRightSum;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    R = readInt();
    C = readInt();

    leftCnt = new int[R + 2][C + 2];
    rightCnt = new int[R + 2][C + 2];
    topCnt = new int[R + 2][C + 2];
    leftSum = new long[R + 2][C + 2];
    rightSum = new long[R + 2][C + 2];
    topSum = new long[R + 2][C + 2];

    topRightSum = new long[R + 2][C + 2];
    topRightCnt = new int[R + 2][C + 2];
    topLeftSum = new long[R + 2][C + 2];
    topLeftCnt = new int[R + 2][C + 2];

    grid = new char[R + 2][C + 2];

    for (int i = 1; i <= R; i++)
      grid[i] = (" " + next()).toCharArray();

    out.printf("%d %d\n", compute('M'), compute('S'));
    out.close();
  }

  static long compute (char curr) {
    long ret = 0;
    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        leftCnt[i][j] = leftCnt[i][j - 1] + (grid[i][j] == curr ? 1 : 0);
        topCnt[i][j] = topCnt[i - 1][j] + (grid[i][j] == curr ? 1 : 0);

        leftSum[i][j] = leftSum[i][j - 1] + leftCnt[i][j - 1];
        topSum[i][j] = topSum[i - 1][j] + topCnt[i - 1][j];

        topLeftCnt[i][j] = topLeftCnt[i - 1][j - 1] + topCnt[i][j] + leftCnt[i][j] - (grid[i][j] == curr ? 1 : 0);
        topLeftSum[i][j] = topLeftSum[i - 1][j - 1] + topLeftCnt[i - 1][j - 1] + topSum[i][j] + leftSum[i][j];
      }
    }

    for (int i = 1; i <= R; i++) {
      for (int j = C; j >= 1; j--) {
        rightCnt[i][j] = rightCnt[i][j + 1] + (grid[i][j] == curr ? 1 : 0);
        rightSum[i][j] = rightSum[i][j + 1] + rightCnt[i][j + 1];

        topRightCnt[i][j] = topRightCnt[i - 1][j + 1] + topCnt[i][j] + rightCnt[i][j] - (grid[i][j] == curr ? 1 : 0);
        topRightSum[i][j] = topRightSum[i - 1][j + 1] + topRightCnt[i - 1][j + 1] + topSum[i][j] + rightSum[i][j];
      }
    }

    for (int i = 1; i <= R; i++) {
      for (int j = 1; j <= C; j++) {
        if ((grid[i][j] == curr)) {
          ret += topRightSum[i - 1][j + 1] + topLeftSum[i - 1][j - 1] + topRightCnt[i - 1][j + 1] + topLeftCnt[i - 1][j - 1] + topSum[i][j] + leftSum[i][j];
        }
      }
    }

    return ret;
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
