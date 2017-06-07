package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACM_NAQ_2016_E {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, leftSize, rightSize;
  static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
  static int[] prev;
  static boolean[] vis;
  static int[] pair, dist;

  static final int NULL = 0;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    leftSize = ((N - 1) * (N - 1) + 1) / 2;
    rightSize = (N - 1) * (N - 1) / 2;

    for (int i = 0; i < leftSize + rightSize + 1; i++)
      adj.add(new ArrayList<Integer>());

    int free = 0;

    for (int i = 0; i < 2 * N - 1; i++) {
      char[] input = readLine().toCharArray();

      if (i % 2 == 0) {
        for (int j = 1; j < 2 * N - 1; j += 2) {
          if (input[j] == '.') {
            free++;
            int r1 = i / 2 - 1;
            int r2 = i / 2;
            int c1 = j / 2;
            int c2 = j / 2;

            if (r1 < 0 || r2 >= N - 1)
              continue;

            int index1 = r1 * (N - 1) + c1;
            int index2 = r2 * (N - 1) + c2;
            if ((r1 + c1) % 2 == 0)
              adj.get(index1 / 2 + 1).add(leftSize + index2 / 2 + 1);
            else
              adj.get(index2 / 2 + 1).add(leftSize + index1 / 2 + 1);
          }
        }
      } else {
        for (int j = 0; j < 2 * N - 1; j += 2) {
          if (input[j] == '.') {
            free++;
            int r1 = i / 2;
            int r2 = i / 2;
            int c1 = j / 2 - 1;
            int c2 = j / 2;

            if (c1 < 0 || c2 >= N - 1)
              continue;

            int index1 = r1 * (N - 1) + c1;
            int index2 = r2 * (N - 1) + c2;
            if ((r1 + c1) % 2 == 0)
              adj.get(index1 / 2 + 1).add(leftSize + index2 / 2 + 1);
            else
              adj.get(index2 / 2 + 1).add(leftSize + index1 / 2 + 1);
          }
        }
      }
    }

    int matched = getMaxMatching();
    out.println(free - matched - ((N - 1) * (N - 1) - matched * 2) + 1);
    out.close();
  }

  static int getMaxMatching () {
    pair = new int[leftSize + rightSize + 1];
    dist = new int[leftSize + rightSize + 1];
    int res = 0;
    while (bfs())
      for (int i = 1; i <= leftSize; i++)
        if (pair[i] == NULL)
          res += dfs(i) ? 1 : 0;
    return res;
  }

  static boolean bfs () {
    Queue<Integer> q = new ArrayDeque<Integer>();
    for (int i = 1; i <= leftSize; i++) {
      if (pair[i] == NULL) {
        dist[i] = 0;
        q.offer(i);
      } else {
        dist[i] = 1 << 30;
      }
    }
    dist[NULL] = 1 << 30;
    while (!q.isEmpty()) {
      Integer curr = q.poll();
      if (dist[curr] >= dist[NULL])
        continue;
      for (int next : adj.get(curr)) {
        if (dist[pair[next]] == 1 << 30) {
          dist[pair[next]] = dist[curr] + 1;
          q.offer(pair[next]);
        }

      }
    }
    return dist[NULL] != 1 << 30;
  }

  static boolean dfs (int i) {
    if (i == NULL)
      return true;
    for (int j : adj.get(i)) {
      if (dist[pair[j]] == dist[i] + 1)
        if (dfs(pair[j])) {
          pair[j] = i;
          pair[i] = j;
          return true;
        }
    }
    dist[i] = 1 << 30;
    return false;
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
