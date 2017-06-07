package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_2014_Tile {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static ArrayList<Tile> res = new ArrayList<Tile>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int x = readInt();
    int y = readInt();

    compute(0, (1 << n) - 1, 0, (1 << n) - 1, x, y);
    for (Tile t : res)
      pr.printf("%d %d %d %d %d %d\n", t.x1, t.y1, t.x2, t.y2, t.x3, t.y3);
    pr.close();
  }

  private static void compute (int x1, int x2, int y1, int y2, int x, int y) {
    if (x1 == x2 && y1 == y2)
      return;
    int midx = (x1 + x2) / 2;
    int midy = (y1 + y2) / 2;
    if (x <= midx && y <= midy) {
      res.add(new Tile(midx, midy + 1, midx + 1, midy, midx + 1, midy + 1));
      compute(x1, midx, y1, midy, x, y);
      compute(midx + 1, x2, y1, midy, midx + 1, midy);
      compute(x1, midx, midy + 1, y2, midx, midy + 1);
      compute(midx + 1, x2, midy + 1, y2, midx + 1, midy + 1);
    } else if (x <= midx && y > midy) {
      res.add(new Tile(midx, midy, midx + 1, midy, midx + 1, midy + 1));
      compute(x1, midx, y1, midy, midx, midy);
      compute(midx + 1, x2, y1, midy, midx + 1, midy);
      compute(x1, midx, midy + 1, y2, x, y);
      compute(midx + 1, x2, midy + 1, y2, midx + 1, midy + 1);
    } else if (x > midx && y <= midy) {
      res.add(new Tile(midx, midy + 1, midx, midy, midx + 1, midy + 1));
      compute(x1, midx, y1, midy, midx, midy);
      compute(midx + 1, x2, y1, midy, x, y);
      compute(x1, midx, midy + 1, y2, midx, midy + 1);
      compute(midx + 1, x2, midy + 1, y2, midx + 1, midy + 1);
    } else if (x > midx && y > midy) {
      res.add(new Tile(midx, midy + 1, midx + 1, midy, midx, midy));
      compute(x1, midx, y1, midy, midx, midy);
      compute(midx + 1, x2, y1, midy, midx + 1, midy);
      compute(x1, midx, midy + 1, y2, midx, midy + 1);
      compute(midx + 1, x2, midy + 1, y2, x, y);
    }

  }

  static class Tile {
    int x1, y1;
    int x2, y2;
    int x3, y3;

    Tile (int x1, int y1, int x2, int y2, int x3, int y3) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      this.x3 = x3;
      this.y3 = y3;
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
