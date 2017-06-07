package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MEC_P1 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int R = readInt();
    int C = readInt();

    char[][] grid = new char[R][C];

    for (int i = 0; i < R; i++)
      grid[i] = readLine().toCharArray();

    ArrayList<Point> p = new ArrayList<Point>();

    for (int i = 0; i < C; i++)
      for (int j = 0; j < R; j++)
        if (grid[j][i] == 'X')
          p.add(new Point(j, i));

    for (int i = 1; i < p.size(); i++)
      out.printf("%.3f\n", Math.atan2(p.get(i).c - p.get(i - 1).c, -p.get(i).r + p.get(i - 1).r) / Math.PI * 180);

    if (p.size() == 0)
      out.println("0.000");

    out.close();
  }

  static class Point {
    int r, c;

    Point (int r, int c) {
      this.r = r;
      this.c = c;
    }
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
