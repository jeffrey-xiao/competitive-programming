package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2016_Stage_2_Zombies_Apocalypse {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, K, Q;
  static int[] R, C;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    R = new int[K];
    C = new int[K];

    for (int i = 0; i < K; i++) {
      R[i] = readInt();
      C[i] = readInt();
    }

    Q = readInt();

    out.println(getArea(Q) - getArea(Q - 1));

    out.close();
  }

  static long getArea(int q) {
    Point[] ver = new Point[2 * K];
    Point[] hor = new Point[2 * K];
    boolean[] use = new boolean[K];

    for (int i = 0; i < K; i++) {
      int loR = Math.max(1, R[i] - q);
      int hiR = Math.min(N, R[i] + q);
      int loC = Math.max(1, C[i] - q);
      int hiC = Math.min(M, C[i] + q);
      hor[i << 1] = new Point(loC, i, 1);
      hor[i << 1 | 1] = new Point(hiC + 1, i, -1);
      ver[i << 1] = new Point(loR, i, 1);
      ver[i << 1 | 1] = new Point(hiR + 1, i, -1);
    }

    Arrays.sort(ver);
    Arrays.sort(hor);

    // area of sweepline
    long horRet = 0;
    // previous horizontal position
    long horPrev = -1;
    for (int i = 0; i < 2 * K; i++) {
      // previous vertical position
      long verPrev = -1;
      // area of vertical strip
      long verRet = 0;
      // height of overlapping strips
      long curr = 0;
      for (int j = 0; j < 2 * K; j++) {
        verRet += (ver[j].pos - verPrev) * Math.min(curr, 1);
        verPrev = ver[j].pos;
        if (use[ver[j].index])
          curr += ver[j].type;

        while (j + 1 < 2 * K && ver[j + 1].pos == verPrev) {
          j++;
          if (use[ver[j].index])
            curr += ver[j].type;
        }
      }

      horRet += (hor[i].pos - horPrev) * verRet;

      horPrev = hor[i].pos;

      use[hor[i].index] = hor[i].type == 1 ? true : false;

      while (i + 1 < 2 * K && hor[i + 1].pos == horPrev) {
        i++;
        use[hor[i].index] = hor[i].type == 1 ? true : false;
      }
    }

    return horRet;
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

  static class Point implements Comparable<Point> {
    Long pos;
    int index, type;

    Point(long pos, int index, int type) {
      this.pos = pos;
      this.index = index;
      this.type = type;
    }

    @Override
    public int compareTo(Point o) {
      return pos.compareTo(o.pos);
    }
  }
}
