package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Valentines_P3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[] movex = {-1, 1, 0, 0, 0, 0};
  static int[] movey = {0, 0, -1, 1, 0, 0};
  static int[] movez = {0, 0, 0, 0, -1, 1};

  public static void main (String[] args) throws IOException {
    int w = readInt();
    int l = readInt();
    int h = readInt();
    int n = readInt();
    boolean[][][] ob = new boolean[w + 1][l + 1][h + 1];
    boolean[][][] v = new boolean[w + 1][l + 1][h + 1];
    for (int x = 0; x < n; x++) {
      ob[readInt()][readInt()][readInt()] = true;
    }
    Queue<Pos> q = new LinkedList<Pos>();
    q.offer(new Pos(1, 1, 1, 0));
    while (!q.isEmpty()) {
      Pos curr = q.poll();
      if (curr.x >= w - 2 && curr.x <= w && curr.y == l && curr.z >= h - 2 && curr.z <= h) {
        System.out.println(curr.moves);
        return;
      }
      for (int k = 0; k < 6; k++) {
        int nx = curr.x + movex[k];
        int ny = curr.y + movey[k];
        int nz = curr.z + movez[k];
        if (nx <= 0 || nx > w || ny <= 0 || ny > l || nz <= 0 || nz > h || ob[nx][ny][nz] || v[nx][ny][nz])
          continue;
        v[nx][ny][nz] = true;
        ;
        q.offer(new Pos(nx, ny, nz, curr.moves + 1));
      }
    }

  }

  static class Pos {
    int x, y, z, moves;

    Pos (int x, int y, int z, int moves) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.moves = moves;
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
