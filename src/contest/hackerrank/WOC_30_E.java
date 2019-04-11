package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class WOC_30_E {

  static final int SQRT = 200;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, Q;
  static int[] val, block, ans, occ;
  static int[][][] prefix;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    Q = readInt();

    val = new int[N + 1];
    block = new int[N + 1];
    prefix = new int[SQRT + 1][SQRT + 1][SQRT + 1];
    occ = new int[40001];

    for (int i = 1; i <= N; i++)
      val[i] = readInt();

    for (int i = 1; i <= N; i++)
      block[i] = (i - 1) / SQRT + 1;

    for (int i = 1; i <= SQRT; i++) {
      for (int j = 1; j <= N; j++)
        prefix[i][block[j]][val[j] % i]++;
      for (int j = 1; j <= block[N]; j++)
        for (int k = 0; k < i; k++)
          prefix[i][j][k] += prefix[i][j - 1][k];
    }

    ArrayList<Query> queries = new ArrayList<Query>();
    ans = new int[Q];

    for (int q = 0; q < Q; q++) {
      int l = readInt() + 1;
      int r = readInt() + 1;
      int x = readInt();
      int y = readInt();

      if (x <= SQRT)
        ans[q] = solveSmall(r, x, y) - solveSmall(l - 1, x, y);
      else
        queries.add(new Query(l, r, x, y, q));
    }

    Collections.sort(queries);

    int l = 1;
    int r = 0;

    for (Query query : queries) {
      while (l < query.l)
        occ[val[l++]]--;
      while (l > query.l)
        occ[val[--l]]++;
      while (r > query.r)
        occ[val[r--]]--;
      while (r < query.r)
        occ[val[++r]]++;

      int curr = query.y;
      while (curr <= 40000) {
        ans[query.index] += occ[curr];
        curr += query.x;
      }
    }

    for (int i = 0; i < Q; i++)
      out.println(ans[i]);

    out.close();
  }

  static int solveSmall(int i, int x, int y) {
    if (i == 0)
      return 0;
    int ret = prefix[x][block[i] - 1][y];
    for (int j = (block[i] - 1) * SQRT + 1; j <= i; j++)
      if (val[j] % x == y)
        ret++;
    return ret;
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

  static class Query implements Comparable<Query> {
    int l, r, x, y, index;

    Query(int l, int r, int x, int y, int index) {
      this.l = l;
      this.r = r;
      this.x = x;
      this.y = y;
      this.index = index;
    }

    @Override
    public int compareTo(Query o) {
      if (block[l] != block[o.l])
        return block[l] - block[o.l];
      return r - o.r;
    }
  }
}
