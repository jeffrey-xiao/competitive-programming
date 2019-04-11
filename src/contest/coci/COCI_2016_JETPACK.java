package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2016_JETPACK {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static char[][] grid;
  static int[][] prev;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    grid = new char[10][N];
    prev = new int[10][N];
    for (int i = 9; i >= 0; i--)
      grid[i] = readLine().toCharArray();

    Queue<Point> q = new ArrayDeque<Point>();
    q.offer(new Point(0, 0));
    prev[0][0] = -1;

    while (!q.isEmpty()) {
      Point curr = q.poll();
      if (curr.c == N - 1) {
        Stack<Integer> press = new Stack<Integer>();
        grid[curr.r][curr.c] = 'A';
        while (prev[curr.r][curr.c] != -1) {
          if (prev[curr.r][curr.c] == 1) {
            curr.r--;
            press.push(curr.c);
          } else if (prev[curr.r][curr.c] == 3)
            curr.r++;
          curr.c--;
          grid[curr.r][curr.c] = 'A';
        }
        out.println(press.size());
        while (!press.isEmpty())
          out.printf("%d %d\n", press.pop() - 1, 1);
        out.close();
        return;
      }

      if (curr.r + 1 < 10 && grid[curr.r + 1][curr.c + 1] != 'X' && prev[curr.r + 1][curr.c + 1] == 0) {
        prev[curr.r + 1][curr.c + 1] = 1;
        q.offer(new Point(curr.r + 1, curr.c + 1));
      }
      if (curr.r - 1 >= 0 && grid[curr.r - 1][curr.c + 1] != 'X' && prev[curr.r - 1][curr.c + 1] == 0) {
        prev[curr.r - 1][curr.c + 1] = 3;
        q.offer(new Point(curr.r - 1, curr.c + 1));
      } else if (grid[curr.r][curr.c + 1] != 'X' && prev[curr.r][curr.c + 1] == 0) {
        prev[curr.r][curr.c + 1] = 2;
        q.offer(new Point(curr.r, curr.c + 1));
      }
    }

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

  static class Point {
    int r, c;

    Point(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
}
