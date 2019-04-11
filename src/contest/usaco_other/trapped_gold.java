package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: trapped
*/
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class trapped_gold {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int id[];
  static Seg seg[];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("trapped.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));

    int n = readInt();
    int ans = 0;
    Hay[] h = new Hay[n];
    for (int i = 0; i < n; i++) {
      h[i] = new Hay(readInt(), readInt());
    }
    id = new int[n - 1];
    seg = new Seg[n - 1];
    Arrays.sort(h);
    PriorityQueue<Int> pq = new PriorityQueue<Int>();
    for (int i = 0; i < n - 1; i++) {
      id[i] = i;
      seg[i] = new Seg(h[i].pos, h[i + 1].pos, h[i].size, h[i + 1].size, i - 1, i, i + 1, h[i + 1].pos - h[i].pos);
      pq.offer(new Int(i));
    }
    while (!pq.isEmpty()) {
      int curr = pq.poll().i;
      if (seg[curr].area == 0)
        continue;
      int len = seg[curr].r - seg[curr].l;
      if (len <= seg[curr].lm && len <= seg[curr].rm) {
        ans += seg[find(curr)].area;
        seg[find(curr)].area = 0;
      } else if (len > seg[curr].lm) {
        if (seg[curr].l == h[0].pos) {
          seg[find(curr)].area = 0;
        } else {
          int rl = find(seg[curr].li);
          int rr = find(curr);
          merge(rl, rr);
          pq.offer(new Int(find(rl)));
        }
      } else if (len > seg[curr].rm) {
        if (seg[curr].r == h[n - 1].pos) {
          seg[find(curr)].area = 0;
        } else {
          int rl = find(curr);
          int rr = find(seg[curr].ri);
          merge(rl, rr);
          pq.offer(new Int(find(rl)));
        }
      }
    }
    pr.println(ans);
    pr.close();
  }

  static int find(int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
  }

  // x is left and y is right
  static void merge(int rl, int rr) {
    if (seg[rl].compareTo(seg[rr]) < 0) {
      seg[rl] = new Seg(seg[rl].l, seg[rr].r, seg[rl].lm, seg[rr].rm, seg[rl].li, rl, seg[rr].ri, seg[rl].area + seg[rr].area);
      seg[rr].area = 0;
      id[rr] = rl;
    } else {
      seg[rr] = new Seg(seg[rl].l, seg[rr].r, seg[rl].lm, seg[rr].rm, seg[rl].li, rr, seg[rr].ri, seg[rl].area + seg[rr].area);
      seg[rl].area = 0;
      id[rl] = rr;
    }
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

  static class Hay implements Comparable<Hay> {
    int size, pos;

    Hay(int size, int pos) {
      this.size = size;
      this.pos = pos;
    }

    @Override
    public int compareTo(Hay o) {
      return pos - o.pos;
    }
  }

  static class Int implements Comparable<Int> {
    int i;

    Int(int i) {
      this.i = i;
    }

    @Override
    public int compareTo(Int o) {
      return seg[i].compareTo(seg[o.i]);
    }
  }

  static class Seg implements Comparable<Seg> {
    int l, r;
    int lm, rm;
    int area;
    int li, ci, ri;

    Seg(int l, int r, int lm, int rm, int li, int ci, int ri, int area) {
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
    public int compareTo(Seg o) {
      if ((r - l) - (o.r - o.l) == 0)
        return l - o.l;
      return (r - l) - (o.r - o.l);
    }
  }
}
