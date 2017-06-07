package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2015_Trapped_In_The_Haybales {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int sz[], id[];
  static Seg seg[];

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int ans = 0;
    int[] s = new int[n];
    int[] p = new int[n];
    for (int i = 0; i < n; i++) {
      s[i] = readInt();
      p[i] = readInt();
    }
    sz = new int[n - 1];
    id = new int[n - 1];
    seg = new Seg[n - 1];
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

      @Override
      public int compare (Integer o1, Integer o2) {
        return seg[o1].compareTo(seg[o2]);
      }

    });
    for (int i = 0; i < n - 1; i++) {
      sz[i] = 1;
      id[i] = i;
      seg[i] = new Seg(p[i], p[i + 1], s[i], s[i + 1], i - 1, i, i + 1, p[i + 1] - p[i]);
      pq.offer(i);
    }
    while (!pq.isEmpty()) {
      int curr = pq.poll();
      if (seg[curr].area == 0)
        continue;
      int len = seg[curr].r - seg[curr].l;
      if (len <= seg[curr].lm && len <= seg[curr].rm) {
        ans += seg[find(curr)].area;
        seg[find(curr)].area = 0;
      } else if (len > seg[curr].lm) {
        if (seg[curr].l == p[0]) {
          seg[find(curr)].area = 0;
        } else {
          int rl = find(seg[curr].li);
          int rr = find(curr);
          merge(rl, rr);
          pq.offer(find(rl));
        }
      } else if (len > seg[curr].rm) {
        if (seg[curr].r == p[n - 1]) {
          seg[find(curr)].area = 0;
        } else {
          int rl = find(curr);
          int rr = find(seg[curr].ri);
          merge(rl, rr);
          pq.offer(find(rl));
        }
      }
    }
    pr.println(ans);
    pr.close();
  }

  static int find (int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
  }

  // x is left and y is right
  static void merge (int rl, int rr) {
    if (seg[rl].compareTo(seg[rr]) < 0) {
      sz[rl] += sz[rr];
      seg[rl] = new Seg(seg[rl].l, seg[rr].r, seg[rl].lm, seg[rr].rm, seg[rl].li, rl, seg[rr].ri, seg[rl].area + seg[rr].area);
      seg[rr].area = 0;
      id[rr] = rl;
    } else {
      sz[rr] += sz[rl];
      seg[rr] = new Seg(seg[rl].l, seg[rr].r, seg[rl].lm, seg[rr].rm, seg[rl].li, rr, seg[rr].ri, seg[rl].area + seg[rr].area);
      seg[rl].area = 0;
      id[rl] = rr;
    }
  }

  static class Seg implements Comparable<Seg> {
    int l, r;
    int lm, rm;
    int area;
    int li, ci, ri;

    Seg (int l, int r, int lm, int rm, int li, int ci, int ri, int area) {
      this.l = l;
      this.r = r;
      this.lm = lm;
      this.rm = rm;
      this.area = area;
      this.li = li;
      this.ci = ci;
      this.ri = ri;
    }

    @Override
    public int compareTo (Seg o) {
      if ((r - l) - (o.r - o.l) == 0)
        return l - o.l;
      return (r - l) - (o.r - o.l);
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
