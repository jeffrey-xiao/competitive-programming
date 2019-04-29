package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class IOI_1995_Packing_Rectangles {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[][] r = new int[4][2];

  static int min = 1 << 30;
  static TreeSet<Rect> ans = new TreeSet<Rect>();
  static int cr;
  static int cc;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    for (int i = 0; i < 4; i++) {
      r[i][0] = readInt();
      r[i][1] = readInt();
    }
    permute(0);
    out.println(min);
    for (Rect r : ans)
      out.printf("%d %d%n", r.r, r.c);
    out.close();
  }

  static void permute(int i) {
    if (i == 3) {
      for (int j = 0; j < 1 << 4; j++) {
        cr = r[0][j >> 0 & 1] + r[1][j >> 1 & 1] + r[2][j >> 2 & 1] + r[3][j >> 3 & 1];
        cc = max(r[0][j >> 0 & 1 ^ 1], r[1][j >> 1 & 1 ^ 1], r[2][j >> 2 & 1 ^ 1], r[3][j >> 3 & 1 ^ 1]);
        add(cr, cc);
        cr = max(r[0][j >> 0 & 1] + r[1][j >> 1 & 1] + r[2][j >> 2 & 1], r[3][j >> 3 & 1]);
        cc = max(r[0][j >> 0 & 1 ^ 1], r[1][j >> 1 & 1 ^ 1], r[2][j >> 2 & 1 ^ 1]) + r[3][j >> 3 & 1 ^ 1];
        add(cr, cc);
        cr = max(r[0][j >> 0 & 1] + r[1][j >> 1 & 1], r[3][j >> 3 & 1]) + r[2][j >> 2 & 1];
        cc = max(max(r[0][j >> 0 & 1 ^ 1], r[1][j >> 1 & 1 ^ 1]) + r[3][j >> 3 & 1 ^ 1], r[2][j >> 2 & 1 ^ 1]);
        add(cr, cc);
        cr = r[0][j >> 0 & 1] + max(r[1][j >> 1 & 1], r[2][j >> 2 & 1]) + r[3][j >> 3 & 1];
        cc = max(r[0][j >> 0 & 1 ^ 1], r[1][j >> 1 & 1 ^ 1] + r[2][j >> 2 & 1 ^ 1], r[3][j >> 3 & 1 ^ 1]);
        add(cr, cc);
        cr = max(r[0][j >> 0 & 1] + r[1][j >> 1 & 1], r[1][j >> 1 & 1] + r[2][j >> 2 & 1], r[2][j >> 2 & 1] + r[3][j >> 3 & 1]);
        cc = max(r[0][j >> 0 & 1 ^ 1] + r[2][j >> 2 & 1 ^ 1], r[0][j >> 0 & 1 ^ 1] + r[3][j >> 3 & 1 ^ 1], r[1][j >> 1 & 1 ^ 1] + r[3][j >> 3 & 1 ^ 1]);
        add(cr, cc);
      }
      return;
    }
    for (int j = i; j < 4; j++) {
      swap(i, j);
      permute(i + 1);
      swap(i, j);
    }
  }

  static void add(int r, int c) {
    if (r * c < min) {
      min = r * c;
      ans.clear();
      ans.add(new Rect(Math.min(r, c), Math.max(r, c)));
    } else if (r * c == min) {
      ans.add(new Rect(Math.min(r, c), Math.max(r, c)));
    }
  }

  static int max(int... val) {
    int ret = 0;
    for (int x : val)
      ret = Math.max(ret, x);
    return ret;
  }

  static void swap(int i, int j) {
    int[] temp = r[i];
    r[i] = r[j];
    r[j] = temp;
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

  static class Rect implements Comparable<Rect> {
    int r, c;

    Rect(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public int hashCode() {
      return r + c;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Rect) {
        Rect rect = (Rect)o;
        return r == rect.r && c == rect.c;
      }
      return false;
    }

    @Override
    public int compareTo(Rect rect) {
      return r - rect.r;
    }
  }
}
