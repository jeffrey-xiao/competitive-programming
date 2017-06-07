package contest.hackercup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class FHC_2016_Round_2_Snakes_And_Ladders {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int MOD = (int)(1e9 + 7);

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

    for (int t = 1; t <= T; t++) {
      int N = readInt();

      Ladder[] l = new Ladder[N];
      ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();

      for (int i = 0; i < N; i++)
        l[i] = new Ladder(readInt(), readInt());

      Arrays.sort(l);

      ArrayList<Integer> curr = new ArrayList<Integer>();
      TreeSet<Integer> prev = new TreeSet<Integer>();
      int currHeight = 1 << 30;

      for (int i = 0; i < N; i++) {
        if (l[i].height != currHeight) {
          if (curr.size() > 1)
            subsets.add(curr);

          curr = new ArrayList<Integer>();
          currHeight = l[i].height;

          prev.add(l[i].pos);
          curr.add(l[i].pos);
        } else {
          int lastPos = curr.get(curr.size() - 1);
          // there is a higher ladder in between
          if (prev.lower(l[i].pos) == null || prev.lower(l[i].pos) > lastPos) {
            if (curr.size() > 1)
              subsets.add(curr);
            curr = new ArrayList<Integer>();
          }

          prev.add(l[i].pos);
          curr.add(l[i].pos);
        }
      }

      if (curr.size() > 1)
        subsets.add(curr);

      long ans = 0;

      for (ArrayList<Integer> subset : subsets) {
        long[] suffix = new long[subset.size() + 1];
        long[] suffixSq = new long[subset.size() + 1];
        for (int i = subset.size() - 1; i >= 0; i--) {
          suffix[i] = (suffix[i + 1] + subset.get(i)) % MOD;
          suffixSq[i] = (suffixSq[i + 1] + 1L * subset.get(i) * subset.get(i)) % MOD;
        }
        for (int i = 0; i < subset.size() - 1; i++) {
          ans = (ans + suffixSq[i + 1] - 2L * subset.get(i) * (suffix[i + 1])) % MOD;
          ans = (ans + 1L * (subset.size() - 1 - i) * subset.get(i) * subset.get(i)) % MOD;
        }
      }

      out.printf("Case #%d: %d\n", t, (ans % MOD + MOD) % MOD);
    }

    out.close();
  }

  static class Ladder implements Comparable<Ladder> {
    int pos, height;

    Ladder (int pos, int height) {
      this.pos = pos;
      this.height = height;
    }

    @Override
    public int compareTo (Ladder l) {
      if (l.height == height)
        return pos - l.pos;
      return l.height - height;
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
