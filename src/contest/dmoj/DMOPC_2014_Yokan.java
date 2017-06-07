package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class DMOPC_2014_Yokan {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static int n, m;
  static Candy[] cc;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    cc = new Candy[n];
    int[] in = new int[n];
    for (int i = 0; i < n; i++) {
      in[i] = readInt();
      cc[i] = new Candy(in[i], i);

    }
    Arrays.sort(cc);
    int q = readInt();
    HashSet<Integer> hs = new HashSet<Integer>();
    for (int i = 0; i < q; i++) {
      int l = readInt() - 1;
      int r = readInt() - 1;
      boolean valid = false;
      int res = 0;
      hs.clear();
      for (int j = 0; j < 25; j++) {
        int rand = (int)(Math.random() * (r - l + 1) + l);
        int cnt = lower(in[rand], r) - Math.max(0, higher(in[rand], l)) + 1;
        if (cnt * 3 >= (r - l + 1) * 2) {
          valid = true;
          break;
        } else if (cnt * 3 >= (r - l + 1))
          hs.add(in[rand]);
        if (res >= 2)
          break;
      }
      if (valid || hs.size() >= 2)
        pr.println("YES");
      else
        pr.println("NO");
    }

    pr.close();
  }

  static int lower (int v, int i) {
    int lo = 0;
    int hi = n - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (cc[mid].v < v || (cc[mid].v == v && cc[mid].i <= i))
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    return hi;
  }

  static int higher (int v, int i) {
    int lo = 0;
    int hi = n - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (cc[mid].v < v || (cc[mid].v == v && cc[mid].i < i))
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    return lo;
  }

  static class Candy implements Comparable<Candy> {
    int v, i;

    Candy (int v, int i) {
      this.v = v;
      this.i = i;
    }

    @Override
    public int compareTo (Candy o) {
      if (v == o.v)
        return i - o.i;
      return v - o.v;
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
