package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class DMOPC_2014_Not_Enough_Servers {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int n, m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    char[][] g = new char[n][m];
    // mask: subset of people that fail ith test case
    int[] masks = new int[m];
    int sum = 0;
    for (int i = 0; i < n; i++) {
      g[i] = next().toCharArray();
    }
    int start = 0;
    for (int i = 0; i < n; i++) {
      long curr = 0;
      for (int j = 0; j < m; j++) {
        curr *= 2;
        if (g[i][j] == 'X')
          curr++;
      }
      start *= 2;
      start += (curr == 0) ? 1 : 0;
    }
    for (int i = 0; i < m; i++) {
      int curr = 0;
      for (int j = 0; j < n; j++) {
        curr *= 2;
        if (g[j][i] == 'X')
          curr++;
      }
      masks[i] = curr;
      sum += masks[i];
    }
    if (sum == 0) {
      System.out.println("1\n1");
      return;
    }
    // subset of people
    Queue<State> q = new ArrayDeque<State>();
    q.offer(new State(start, 0));
    int[] min = new int[1 << n];
    for (int i = 0; i < 1 << n; i++)
      min[i] = 1 << 30;
    min[start] = 0;
    int[] prevS = new int[1 << n];
    int[] prevI = new int[1 << n];
    prevS[start] = prevI[start] = -1;
    for (int i = 0; i < (1 << n); i++) {
      if (min[i] == 1 << 30)
        continue;
      for (int j = 0; j < m; j++) {
        if ((masks[j] | i) == i)
          continue;
        int next = masks[j] | i;
        if (min[next] <= min[i] + 1)
          continue;
        min[next] = min[i] + 1;
        prevS[next] = i;
        prevI[next] = j;
      }
    }
    System.out.println(min[(1 << n) - 1]);
    int curr = (1 << n) - 1;
    while (curr != -1) {
      if (prevI[curr] != -1)
        System.out.print(prevI[curr] + 1 + " ");
      curr = prevS[curr];
    }
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
    int bitmask, cost, cnt;

    State(int bitmask, int cost) {
      this.bitmask = bitmask;
      this.cost = cost;
      for (int i = 0; i < m; i++)
        if (((1 << i) & bitmask) > 0)
          cnt++;
    }

    @Override
    public int compareTo(State o) {
      if (cnt == o.cnt)
        return cost - o.cost;
      return cnt - o.cnt;
    }
  }
}
