package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_2003_Amazing_Robots {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int r1, c1, r2, c2, g1, g2;
  static int sr1, sc1, sr2, sc2;
  static char[][] grid1, grid2;
  static int[] mover = {0, 0, -1, 1};
  static int[] movec = {-1, 1, 0, 0};
  static State[][][][][] prev;
  static String dir = "WENS";
  static ArrayList<Guard> guard1 = new ArrayList<Guard>(), guard2 = new ArrayList<Guard>();
  static Point[][][] pos;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    r1 = readInt();
    c1 = readInt();
    grid1 = new char[r1 + 2][c1 + 2];
    for (int i = 1; i <= r1; i++) {
      char[] in = (" " + next()).toCharArray();
      for (int j = 1; j <= c1; j++) {
        grid1[i][j] = in[j];
        if (in[j] == 'X') {
          sr1 = i;
          sc1 = j;
        }
      }
    }
    g1 = readInt();
    pos = new Point[10][12][2];
    for (int i = 0; i < g1; i++) {
      guard1.add(new Guard(readInt(), readInt(), readInt(), readCharacter()));
      for (int j = 0; j < 12; j++)
        pos[i][j][0] = getPoint(guard1.get(i), j);
    }
    r2 = readInt();
    c2 = readInt();
    grid2 = new char[r2 + 2][c2 + 2];
    for (int i = 1; i <= r2; i++) {
      char[] in = (" " + next()).toCharArray();
      for (int j = 1; j <= c2; j++) {
        grid2[i][j] = in[j];
        if (in[j] == 'X') {
          sr2 = i;
          sc2 = j;
        }
      }
    }
    g2 = readInt();
    for (int i = 0; i < g2; i++) {
      guard2.add(new Guard(readInt(), readInt(), readInt(), readCharacter()));
      for (int j = 0; j < 12; j++)
        pos[i][j][1] = getPoint(guard2.get(i), j);
    }
    prev = new State[r1 + 2][c1 + 2][r2 + 2][c2 + 2][12];
    Queue<State> q = new ArrayDeque<State>();
    prev[sr1][sc1][sr2][sc2][0] = new State(-1, -1, -1, -1, -1);
    q.offer(new State(sr1, sc1, sr2, sc2, 0));
    State backtrack = null;
    while (!q.isEmpty()) {
      State curr = q.poll();
      if ((curr.r1 == 0 || curr.r1 == r1 + 1 || curr.c1 == 0 || curr.c1 == c1 + 1) && (curr.r2 == 0 || curr.r2 == r2 + 1 || curr.c2 == 0 || curr.c2 == c2 + 1)) {
        backtrack = curr;
        break;
      }
      for (int k = 0; k < 4; k++) {
        int nr1 = curr.r1;
        int nc1 = curr.c1;
        int nr2 = curr.r2;
        int nc2 = curr.c2;
        if (nr1 != 0 && nr1 != r1 + 1 && nc1 != 0 && nc1 != c1 + 1 && grid1[nr1 + mover[k]][nc1 + movec[k]] != '#') {
          nr1 += mover[k];
          nc1 += movec[k];
        }
        if (nr2 != 0 && nr2 != r2 + 1 && nc2 != 0 && nc2 != c2 + 1 && grid2[nr2 + mover[k]][nc2 + movec[k]] != '#') {
          nr2 += mover[k];
          nc2 += movec[k];
        }
        boolean guarded = false;
        for (int i = 0; i < guard1.size(); i++)
          guarded |= isGuarded(curr.r1, curr.c1, nr1, nc1, i << 1, curr.time);
        for (int i = 0; i < guard2.size(); i++)
          guarded |= isGuarded(curr.r2, curr.c2, nr2, nc2, i << 1 | 1, curr.time);
        if (!guarded && prev[nr1][nc1][nr2][nc2][(curr.time + 1) % 12] == null) {
          prev[nr1][nc1][nr2][nc2][(curr.time + 1) % 12] = new State(curr.r1, curr.c1, curr.r2, curr.c2, curr.time, dir.charAt(k));
          q.offer(new State(nr1, nc1, nr2, nc2, (curr.time + 1) % 12));
        }
      }
    }
    if (backtrack == null)
      out.println(-1);
    else {
      int res = 0;
      ArrayDeque<Character> commands = new ArrayDeque<Character>();
      backtrack = prev[backtrack.r1][backtrack.c1][backtrack.r2][backtrack.c2][backtrack.time];
      while (backtrack.c1 != -1 && backtrack.c2 != -1) {
        commands.add(backtrack.dir);
        res++;
        backtrack = prev[backtrack.r1][backtrack.c1][backtrack.r2][backtrack.c2][backtrack.time];
      }
      out.println(res);
      while (!commands.isEmpty())
        out.println(commands.pollLast());
    }
    out.close();
  }

  static Point getPoint (Guard g, int time) {
    int gr = g.r, gc = g.c;
    int move = Math.min(time % (g.len * 2 - 2), Math.abs(time % (g.len * 2 - 2) - g.len * 2 + 2));
    if (g.dir == 'E') {
      gc += move;
    } else if (g.dir == 'W') {
      gc -= move;
    } else if (g.dir == 'N') {
      gr -= move;
    } else {
      gr += move;
    }
    return new Point(gr, gc);
  }

  static boolean isGuarded (int r, int c, int nr, int nc, int gid, int time) {
    Point g = pos[gid >> 1][time][gid & 1];
    Point ng = pos[gid >> 1][(time + 1) % 12][gid & 1];
    return ng.r == nr && ng.c == nc || (ng.r == r && ng.c == c && nr == g.r && nc == g.c);
  }

  static class Point {
    int r, c;

    Point (int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  static class Guard {
    int r, c, len;
    char dir;

    Guard (int r, int c, int len, char dir) {
      this.r = r;
      this.c = c;
      this.len = len;
      this.dir = dir;
    }
  }

  static class State {
    int r1, c1, r2, c2, time;
    char dir;

    State (int r1, int c1, int r2, int c2, int time) {
      this(r1, c1, r2, c2, time, ' ');
    }

    State (int r1, int c1, int r2, int c2, int time, char dir) {
      this.r1 = r1;
      this.c1 = c1;
      this.r2 = r2;
      this.c2 = c2;
      this.time = time;
      this.dir = dir;
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
