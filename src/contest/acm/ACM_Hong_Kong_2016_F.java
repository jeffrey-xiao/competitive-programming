package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ACM_Hong_Kong_2016_F {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, K, sz;
  static int[] man, woman;
  static int[] total, diff;
  static int res = 0;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    total = new int[K];
    diff = new int[K];
    man = new int[N + 1];
    woman = new int[N + 1];

    sz = (int)Math.sqrt(N);

    for (int i = 1; i <= N; i++)
      man[i] = readInt();

    for (int i = 1; i <= N; i++)
      woman[i] = readInt();

    ArrayList<Query> q = new ArrayList<Query>();

    int[] ans = new int[M];

    for (int i = 0; i < M; i++) {
      int l = readInt() + 1;
      int r = readInt() + 1;
      q.add(new Query(l, r, i));
    }

    Collections.sort(q);
    int l = 1, r = 0;
    for (Query query : q) {
      while (r > query.r) {
        remove(r);
        r--;
      }
      while (r < query.r) {
        r++;
        update(r);
      }
      while (l < query.l) {
        remove(l);
        l++;
      }
      while (l > query.l) {
        l--;
        update(l);
      }
      ans[query.index] = res;
    }

    for (int i = 0; i < M; i++)
      out.println(ans[i]);

    out.close();
  }

  static void remove (int i) {
    if (diff[man[i]] <= 0)
      res--;
    diff[man[i]]--;
    if (diff[woman[i]] >= 0)
      res--;
    diff[woman[i]]++;
  }

  static void update (int i) {
    if (diff[man[i]] <= -1)
      res++;
    diff[man[i]]++;

    if (diff[woman[i]] >= 1)
      res++;
    diff[woman[i]]--;
  }

  static class Query implements Comparable<Query> {
    int l, r, index;

    Query (int l, int r, int index) {
      this.l = l;
      this.r = r;
      this.index = index;
    }

    @Override
    public int compareTo (Query o) {
      if ((l - 1) / sz != (o.l - 1) / sz)
        return (l - 1) / sz - (o.l - 1) / sz;
      return r - o.r;
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
