package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Mackenzie_Postcard {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int M = readInt();

    char[][] grid = new char[N][M];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < M; j++)
        grid[i][j] = readCharacter();

    int minR = 1 << 30;
    int maxR = -1 << 30;
    int minC = 1 << 30;
    int maxC = -1 << 30;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == '*') {
          minR = Math.min(minR, i);
          maxR = Math.max(maxR, i);

          minC = Math.min(minC, j);
          maxC = Math.max(maxC, j);
        }
      }
    }
    if (minR != 1 << 30) {
      for (int i = minR; i <= maxR; i++) {
        for (int j = minC; j <= maxC; j++) {
          out.print(grid[i][j] + " ");
        }
        out.println();
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
}
