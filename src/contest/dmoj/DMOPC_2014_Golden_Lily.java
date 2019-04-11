package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DMOPC_2014_Golden_Lily {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int[] movex = {0, 1, -1};
  static int[] movey = {1, 0, 0};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int l = readInt();
    int d = readInt();
    int[][] min = new int[d][l];
    int[][] grid = new int[d][l];

    for (int i = 0; i < d; i++) {
      for (int j = 0; j < l; j++) {
        min[i][j] = 1 << 30;
        grid[i][j] = readInt();
      }
    }

    int x = readInt();
    int y = readInt();
    min[0][0] = grid[0][0];

    PriorityQueue<State> pq = new PriorityQueue<State>();
    pq.offer(new State(0, 0, min[0][0]));
    while (!pq.isEmpty()) {
      State curr = pq.poll();
      for (int z = 0; z < 3; z++) {
        int nx = curr.x + movex[z];
        int ny = curr.y + movey[z];
        if (nx >= 0 && nx < l && ny >= 0 && ny < d && min[ny][nx] > curr.min + grid[ny][nx]) {
          min[ny][nx] = curr.min + grid[ny][nx];
          pq.offer(new State(nx, ny, min[ny][nx]));
        }
      }
    }
    pr.println(min[y][x]);
    pr.close();
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

  static class State implements Comparable<State> {
    int x, y, min;

    State(int x, int y, int min) {
      this.x = x;
      this.y = y;
      this.min = min;
    }

    @Override
    public int compareTo(State s) {
      return min - s.min;
    }
  }
}
