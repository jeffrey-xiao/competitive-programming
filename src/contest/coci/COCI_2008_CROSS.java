package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2008_CROSS {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int[][] pos = new int[9][9];
    int[][] num = new int[9][9];
    Queue<Point> q = new LinkedList<Point>();
    for (int i = 0; i < 9; i++) {
      char[] in = next().toCharArray();
      for (int j = 0; j < 9; j++) {
        if (in[j] == '.')
          num[i][j] = -1;
        else {
          num[i][j] = in[j] - '0';
          q.offer(new Point(i, j));
        }
        pos[i][j] = (1 << 10) - 1;
      }
    }
    boolean hasNext = true;
    while (hasNext) {
      hasNext = false;
      while (!q.isEmpty()) {
        Point curr = q.poll();
        int n = num[curr.x][curr.y];
        for (int x = 0; x < 9; x++) {
          if (num[x][curr.y] == n && x != curr.x) {
            System.out.println("ERROR");
            return;
          }
          pos[x][curr.y] &= ~(1 << n);
        }
        for (int y = 0; y < 9; y++) {
          if (num[curr.x][y] == n && y != curr.y) {
            System.out.println("ERROR");
            return;
          }
          pos[curr.x][y] &= ~(1 << n);
        }
        for (int x = curr.x / 3 * 3; x < (curr.x / 3 + 1) * 3; x++) {
          for (int y = curr.y / 3 * 3; y < (curr.y / 3 + 1) * 3; y++) {
            if (x == curr.x ^ y == curr.y)
              continue;
            if (num[x][y] == n && x != curr.x && y != curr.y) {
              System.out.println("ERROR");
              return;
            }
            pos[x][y] &= ~(1 << n);
          }
        }
      }
      for (int n = 1; n <= 9; n++) {
        for (int i = 0; i < 3; i++) {
        next:
          for (int j = 0; j < 3; j++) {
            boolean has = false;
            int nx = 0;
            int ny = 0;
            for (int x = i * 3; x < (i + 1) * 3; x++) {
              for (int y = j * 3; y < (j + 1) * 3; y++) {
                if ((pos[x][y] & 1 << n) > 0 && num[x][y] == -1) {
                  if (has)
                    continue next;
                  else {
                    nx = x;
                    ny = y;
                    has = true;
                  }
                }
                if (num[x][y] == n)
                  continue next;
              }
            }
            if (has) {
              num[nx][ny] = n;
              q.offer(new Point(nx, ny));
              hasNext = true;
            } else {
              System.out.println("ERROR");
              return;
            }
          }
        }
      }
    }
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (num[i][j] == -1)
          System.out.print(".");
        else
          System.out.print(num[i][j]);
      }
      System.out.println();
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

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
