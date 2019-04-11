package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class WOC_30_G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, Q, totalLen, ln;
  static String[] input;
  static int[] text, lcp, toWordIndex, wordLength;
  static Integer[] sa;
  static int[][] rmq;

  // end locations of each word in the big word
  static TreeMap<Integer, Integer> endLocations = new TreeMap<Integer, Integer>();

  static double LOG_2 = Math.log(2);
  static double[] log = new double[2000001];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    Q = readInt();

    input = new String[N];
    wordLength = new int[N];

    for (int i = 1; i <= 2000000; i++)
      log[i] = Math.log(i);

    // concatenating all words
    for (int i = 0; i < N; i++) {
      input[i] = next();
      totalLen += input[i].length();
      wordLength[i] = input[i].length();
      endLocations.put(totalLen + i, i);
    }

    totalLen += N;
    text = new int[totalLen];
    toWordIndex = new int[totalLen];

    for (int i = 0, index = 0; i < N; i++) {
      for (int j = 0; j < input[i].length(); j++, index++)
        text[index] = input[i].charAt(j) - 'a';
      text[index++] = i + 26;
    }

    sa = SA.computeSuffixArray(text);
    lcp = getLcp(sa, text);
    ln = 1 + (int) (Math.ceil(Math.log(lcp.length - 1) / Math.log(2)));

    // computing the word associated with each index
    for (int i = 0; i < totalLen; i++) {
      if (text[sa[i]] > 25) {
        toWordIndex[i] = -1;
        continue;
      }
      int wordIndex = endLocations.ceilingEntry(sa[i]).getValue();
      toWordIndex[i] = wordIndex;
    }

    // precomputing RMQ
    rmq = new int[lcp.length][ln];

    for (int i = 0; i < lcp.length; i++)
      rmq[i][0] = lcp[i];

    for (int j = 1; j < ln; j++)
      for (int i = 0; i + (1 << j) - 1 < lcp.length; i++)
        rmq[i][j] = Math.min(rmq[i][j - 1], rmq[i + (1 << (j - 1))][j - 1]);

    int[] ans = new int[Q];
    ArrayList<ArrayList<Pair>> queries = new ArrayList<ArrayList<Pair>>();
    HashMap<Pair, Integer> first = new HashMap<Pair, Integer>();
    ArrayList<Pair> dups = new ArrayList<Pair>();

    for (int i = 0; i < N; i++)
      queries.add(new ArrayList<Pair>());

    for (int q = 0; q < Q; q++) {
      int x = readInt();
      int y = readInt();

      if (x == y) {
        ans[q] = wordLength[x];
        continue;
      }

      // search from the smaller set
      if (wordLength[x] > wordLength[y]) {
        int temp = x;
        x = y;
        y = temp;
      }

      Pair p = new Pair(x, y);
      if (first.containsKey(p))
        dups.add(new Pair(q, first.get(p)));
      else {
        first.put(p, q);
        queries.get(x).add(new Pair(y, q));
      }
    }

    int[] last = new int[N];

    // going forward
    Arrays.fill(last, -1);

    for (int i = 0; i < totalLen; i++) {
      if (toWordIndex[i] == -1)
        continue;
      last[toWordIndex[i]] = i;
      for (Pair query : queries.get(toWordIndex[i]))
        if (last[query.x] != -1)
          ans[query.y] = Math.max(ans[query.y], getRangeMin(last[query.x], i - 1));
    }

    // going backward
    Arrays.fill(last, -1);

    for (int i = totalLen - 1; i >= 0; i--) {
      if (toWordIndex[i] == -1)
        continue;
      last[toWordIndex[i]] = i;
      for (Pair query : queries.get(toWordIndex[i]))
        if (last[query.x] != -1)
          ans[query.y] = Math.max(ans[query.y], getRangeMin(i, last[query.x] - 1));
    }

    // processing dups
    for (Pair p : dups)
      ans[p.x] = ans[p.y];

    for (int i = 0; i < Q; i++)
      out.println(ans[i]);

    out.close();
  }

  static int getRangeMin(int l, int r) {
    int sz = r - l + 1;
    int lnSz = (int) (log[sz] / LOG_2);
    return Math.min(rmq[l][lnSz], rmq[r - (1 << lnSz) + 1][lnSz]);
  }

  static int[] getLcp(Integer[] sa, int[] text) {
    int len = text.length;
    int[] rank = new int[len];
    int[] LCP = new int[len - 1];
    for (int i = 0; i < len; i++)
      rank[sa[i]] = i;
    int k = 0;

    for (int i = 0; i < len; i++, k = k > 0 ? k - 1 : 0) {
      if (rank[i] == len - 1) {
        k = 0;
        continue;
      }

      int j = sa[rank[i] + 1];
      while (j + k < len && i + k < len && text[j + k] == text[i + k])
        k++;
      LCP[rank[i]] = k;
    }
    return LCP;
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

  static class Pair {
    int x, y;

    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      return x * 31 + y;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Pair) {
        Pair p = (Pair) o;
        return x == p.x && y == p.y;
      }
      return false;
    }
  }

  static class SA {
    static final SuffixComparator C = new SuffixComparator();
    static int[] order;
    static int sz, len;

    static Integer[] computeSuffixArray(int[] input) {
      len = input.length;
      Integer[] res = new Integer[len];
      order = new int[len];
      int[] newOrder = new int[len];
      sz = 0;

      for (int i = 0; i < len; i++) {
        res[i] = i;
        order[i] = input[i];
        newOrder[i] = 0;
      }

      for (sz = 1; ; sz <<= 1) {
        Arrays.sort(res, C);
        for (int i = 0; i < len - 1; i++)
          newOrder[i + 1] = newOrder[i] + (C.compare(res[i], res[i + 1]) < 0 ? 1 : 0);
        for (int i = 0; i < len; i++)
          order[res[i]] = newOrder[i];
        if (newOrder[len - 1] == len - 1)
          break;
      }

      return res;
    }

    // Comparator for suffixes
    static class SuffixComparator implements Comparator<Integer> {
      @Override
      public int compare(Integer o1, Integer o2) {
        if (order[o1] != order[o2])
          return order[o1] - order[o2];
        if ((o1 += sz) < len & (o2 += sz) < len)
          return order[o1] - order[o2];
        return o2 - o1;
      }
    }
  }
}
