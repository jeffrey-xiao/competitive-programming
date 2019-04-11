package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2009_BAKICE {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};
  static int r, c;

  public static void main(String[] args) throws IOException {
    r = readInt();
    c = readInt();
    char[][] g = new char[r][];
    Queue<Point> moves = new LinkedList<Point>();
    ArrayList<State> states = new ArrayList<State>();
    int count = 0;
    for (int x = 0; x < r; x++) {
      String next = next();
      g[x] = next.toCharArray();
      for (int y = 0; y < c; y++) {
        if (next.charAt(y) == 'L') {
          moves.offer(new Point(x, y, count));
          count++;
          states.add(new State(x, y));
        }
      }
    }
    while (!moves.isEmpty()) {
      Point curr = moves.poll();
      State currState = states.get(curr.stateNum);
      double dist = currState.getDist(curr);
      if (dist > currState.minDistance)
        continue;
      else if (dist == currState.minDistance && g[curr.x][curr.y] == 'X') {
        currState.minDistance = dist;
        currState.points.add(curr);
        g[curr.x][curr.y] = '.';
      } else if (dist < currState.minDistance && g[curr.x][curr.y] == 'X') {
        currState.points.clear();
        currState.points.add(curr);
        currState.minDistance = dist;
        g[curr.x][curr.y] = '.';
      }

      for (int z = 0; z < 4; z++) {
        int nx = curr.x + movex[z];
        int ny = curr.y + movey[z];
        if (nx < 0 || ny < 0 || nx >= r || ny >= c || currState.visited[nx][ny])
          continue;

        currState.visited[nx][ny] = true;
        moves.offer(new Point(nx, ny, curr.stateNum));
      }
    }
    count = 0;
    for (State s : states)
      if (s.points.size() > 1)
        count++;
    System.out.println(count);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class State {
    int x;
    int y;
    double minDistance;
    boolean[][] visited = new boolean[r][c];
    HashSet<Point> points = new HashSet<Point>();

    State(int x, int y) {
      this.x = x;
      this.y = y;
      visited[x][y] = true;
      this.minDistance = Integer.MAX_VALUE;
    }

    double getDist(Point p) {
      int x1 = p.x - x;
      int y1 = p.y - y;
      return Math.sqrt(x1 * x1 + y1 * y1);
    }
  }

  static class Point {
    int x;
    int y;
    int stateNum;

    Point(int x, int y, int state) {
      this.x = x;
      this.y = y;
      this.stateNum = state;
    }
  }
}
