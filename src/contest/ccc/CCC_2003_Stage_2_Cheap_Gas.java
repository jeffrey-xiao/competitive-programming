package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_Cheap_Gas {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] movex = {0, 0, -1, 1};
  static int[] movey = {-1, 1, 0, 0};

  public static void main (String[] args) throws IOException {
    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int m = readInt();
      int f = readInt();
      int k = readInt();
      if (f >= n * m) {
        System.out.println("0.00");
        continue;
      }
      double[][][] dp = new double[n][m][f + 1];
      double[][] cost = new double[n][m];
      for (int x = 0; x < n; x++)
        for (int y = 0; y < m; y++) {
          cost[x][y] = 1 << 30;
          for (int z = 0; z <= f; z++)
            dp[x][y][z] = 1 << 30;
        }
      for (int x = 0; x < k; x++) {
        int a = readInt() - 1;
        int b = readInt() - 1;
        double c = readDouble();
        cost[a][b] = Math.min(cost[a][b], c);
      }
      Queue<State> q = new LinkedList<State>();
      q.offer(new State(0, 0, f, 0));
      dp[0][0][f] = 0;
      while (!q.isEmpty()) {
        State curr = q.poll();
        if (dp[curr.x][curr.y][curr.fuel] < curr.cost)
          continue;
        for (int z = 0; z < 4; z++) {
          int nx = curr.x + movex[z];
          int ny = curr.y + movey[z];

          if (nx < 0 || ny < 0 || nx >= n || ny >= m)
            continue;

          if (cost[curr.x][curr.y] != 1 << 30) {
            for (int nf = curr.fuel - 1; nf <= f - 1; nf++) {
              double newCost = (nf - (curr.fuel - 1)) * cost[curr.x][curr.y] + curr.cost;
              int newFuel = nf;

              if (newFuel < 0 || dp[nx][ny][newFuel] <= newCost)
                continue;
              dp[nx][ny][newFuel] = newCost;
              q.offer(new State(nx, ny, newFuel, newCost));
            }
          } else {
            if (curr.fuel == 0)
              continue;
            if (dp[nx][ny][curr.fuel - 1] <= curr.cost)
              continue;
            dp[nx][ny][curr.fuel - 1] = curr.cost;
            q.offer(new State(nx, ny, curr.fuel - 1, curr.cost));
          }
        }
      }
      double min = 1 << 30;
      for (int ff = 0; ff <= f; ff++) {
        min = Math.min(dp[n - 1][m - 1][ff], min);
      }
      if (min == 1 << 30)
        System.out.println("Stranded on the shoulder");
      else
        System.out.printf("%.2f\n", min);
    }
  }

  static class State implements Comparable<State> {
    int x, y, fuel;
    double cost;

    State (int x, int y, int fuel, double cost) {
      this.x = x;
      this.y = y;
      this.fuel = fuel;
      this.cost = cost;
    }

    @Override
    public int compareTo (State o) {
      return (int)(cost - o.cost);
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
