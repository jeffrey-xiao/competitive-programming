import java.io.*;
import java.util.*;

public class C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static char[][] board;
  static int N;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));


    N = readInt();
    board = new char[N][N];
    for (int i = 0; i < N; i++) {
      board[i] = readLine().toCharArray();
    }

    int ans = 0;
    for (int row = 1; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (needsAttack(row - 1, col - 1) || (needsAttack(row - 1, col + 1) && !canPlace(row, col + 2))) {
          if (!canPlace(row, col)) {
            out.println(-1);
            out.close();
            return;
          }
          board[row][col] = 'x';
          ans++;
        }
      }
    }

    for (int col = 0; col < N; col++) {
      if (needsAttack(N - 1, col)) {
        out.println(-1);
        out.close();
        return;
      }
    }

    out.println(ans);
    out.close();
  }

  static boolean needsAttack(int r, int c) {
    if (r >= N || c >= N || r < 0 || c < 0)
      return false;
    if (board[r][c] != '*') {
      return false;
    }
    if (isChar(r + 1, c - 1, 'x') ||
        isChar(r + 1, c + 1, 'x') ||
        isChar(r + 1, c - 1, 'K') ||
        isChar(r + 0, c - 1, 'K') ||
        isChar(r - 1, c - 1, 'K') ||
        isChar(r + 1, c + 1, 'K') ||
        isChar(r + 0, c + 1, 'K') ||
        isChar(r - 1, c + 1, 'K') ||
        isChar(r + 1, c + 0, 'K') ||
        isChar(r - 1, c + 0, 'K')) {
      return false;
    }
    return true;
  }

  static boolean isChar(int r, int c, char ch) {
    if (r >= N || c >= N || r < 0 || c < 0)
      return false;
    if (board[r][c] != ch) {
      return false;
    }
    return true;
  }

  static boolean canPlace(int r, int c) {
    if (r >= N || c >= N || r < 0 || c < 0)
      return false;
    if (board[r][c] != '-') {
      return false;
    }
    return true;
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
