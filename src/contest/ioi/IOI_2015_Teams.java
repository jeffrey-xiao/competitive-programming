package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class IOI_2015_Teams {

  static BufferedReader br;
  static OutputStreamWriter out;
  static StringTokenizer st;

  static Person[] p;

  static int N, M;

  static int[] seg;
  static int used;

  static ArrayList<Integer> a;

  static Node[] nodes = new Node[12500000];
  static int nodeIndex = 0;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new OutputStreamWriter(System.out);
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    p = new Person[N];

    a = new ArrayList<Integer>();

    a.add(0);

    for (int i = 0; i < N; i++) {
      int lo = readInt();
      int hi = readInt();
      p[i] = new Person(lo, hi);
      a.add(lo);
      a.add(hi);
    }

    Collections.sort(a);
    a = unique(a);

    seg = new int[a.size()];
    for (int i = 0; i < 12500000; i++)
      nodes[i] = new Node(0);

    Arrays.sort(p);

    nodes[nodeIndex].left = nodes[nodeIndex].right = nodeIndex;
    seg[0] = nodeIndex++;

    int index = 0;

    for (int i = 1; i < a.size(); i++) {
      int prev = seg[i - 1];
      while (index < N && p[index].lo <= a.get(i)) {
        prev = update(prev, 0, a.size() - 1, findFloor(a, p[index].hi), 1);
        index++;
      }

      seg[i] = prev;
    }

    M = readInt();

    main:
    for (int i = 0; i < M; i++) {
      int currIndex = nodeIndex;
      nodes[nodeIndex].left = nodes[nodeIndex].right = nodeIndex;
      used = nodeIndex++;

      int sz = readInt();
      int[] val = new int[sz];

      for (int j = 0; j < sz; j++)
        val[j] = readInt();

      Arrays.sort(val);
      for (int j = 0; j < sz; j++) {
        int curr = findFloor(a, val[j]);
        int next = findCeil(a, val[j]);

        if (next > a.size() - 1) {
          out.write('0');
          out.write('\n');
          nodeIndex = currIndex;
          continue main;
        }

        int sum = getSum(seg[curr], used, 0, a.size() - 1, next, a.size() - 1);

        if (sum < val[j]) {
          out.write('0');
          out.write('\n');
          nodeIndex = currIndex;
          continue main;
        }

        used = query(seg[curr], used, val[j], 0, a.size() - 1, next, a.size() - 1).n;
      }
      out.write('1');
      out.write('\n');
      nodeIndex = currIndex;
    }

    out.close();
  }

  static int getSum(int curr, int used, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
      return nodes[curr].cnt - nodes[used].cnt;

    int mid = (l + r) >> 1;
    if (qr <= mid)
      return getSum(nodes[curr].left, nodes[used].left, l, mid, ql, qr);
    else if (ql > mid)
      return getSum(nodes[curr].right, nodes[used].right, mid + 1, r, ql, qr);
    return getSum(nodes[curr].left, nodes[used].left, l, mid, ql, mid) + getSum(nodes[curr].right, nodes[used].right, mid + 1, r, mid + 1, qr);
  }

  static State query(int curr, int used, int K, int l, int r, int ql, int qr) {
    int mid = (l + r) >> 1;
    if (K == 0)
      return new State(0, used);

    if (l == ql && r == qr) {
      if (l == r) {
        if (nodes[curr].cnt - nodes[used].cnt <= K) {
          return new State(nodes[curr].cnt - nodes[used].cnt, curr);
        }
        nodes[nodeIndex].cnt = K + nodes[used].cnt;
        nodes[nodeIndex].left = -1;
        nodes[nodeIndex].right = -1;
        return new State(K, nodeIndex++);
      }

      if (nodes[curr].cnt - nodes[used].cnt <= K)
        return new State(nodes[curr].cnt - nodes[used].cnt, curr);
      else {
        int leftSz = nodes[nodes[curr].left].cnt - nodes[nodes[used].left].cnt;
        if (leftSz < K) {
          State right = query(nodes[curr].right, nodes[used].right, K - leftSz, mid + 1, r, mid + 1, qr);
          assert leftSz + right.cnt == nodes[nodes[curr].left].cnt + nodes[right.n].cnt - nodes[used].cnt;
          nodes[nodeIndex].left = nodes[curr].left;
          nodes[nodeIndex].right = right.n;
          nodes[nodeIndex].cnt = nodes[nodes[curr].left].cnt + nodes[right.n].cnt;
          return new State(leftSz + right.cnt, nodeIndex++);
        } else {
          State left = query(nodes[curr].left, nodes[used].left, K, l, mid, ql, mid);
          assert K == nodes[left.n].cnt + nodes[nodes[used].right].cnt - nodes[used].cnt;
          nodes[nodeIndex].left = left.n;
          nodes[nodeIndex].right = nodes[used].right;
          nodes[nodeIndex].cnt = nodes[left.n].cnt + nodes[nodes[used].right].cnt;
          return new State(K, nodeIndex++);
        }
      }
    }

    if (qr <= mid) {
      State s = query(nodes[curr].left, nodes[used].left, K, l, mid, ql, qr);
      nodes[nodeIndex].left = s.n;
      nodes[nodeIndex].right = nodes[used].right;
      nodes[nodeIndex].cnt = nodes[s.n].cnt + nodes[nodes[used].right].cnt;
      return new State(s.cnt, nodeIndex++);
    } else if (ql > mid) {
      State s = query(nodes[curr].right, nodes[used].right, K, mid + 1, r, ql, qr);
      nodes[nodeIndex].left = nodes[used].left;
      nodes[nodeIndex].right = s.n;
      nodes[nodeIndex].cnt = nodes[nodes[used].left].cnt + nodes[s.n].cnt;
      return new State(s.cnt, nodeIndex++);
    } else {
      State left = query(nodes[curr].left, nodes[used].left, K, l, mid, ql, mid);
      State right = query(nodes[curr].right, nodes[used].right, K - left.cnt, mid + 1, r, mid + 1, qr);
      nodes[nodeIndex].left = left.n;
      nodes[nodeIndex].right = right.n;
      nodes[nodeIndex].cnt = nodes[left.n].cnt + nodes[right.n].cnt;
      return new State(left.cnt + right.cnt, nodeIndex++);
    }
  }

  static int findFloor(ArrayList<Integer> val, int x) {
    int lo = 0;
    int hi = val.size() - 1;
    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      if (val.get(mid) <= x) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return hi;
  }

  static int findCeil(ArrayList<Integer> val, int x) {
    int lo = 0;
    int hi = val.size() - 1;
    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      if (val.get(mid) < x) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return lo;
  }

  static ArrayList<Integer> unique(ArrayList<Integer> val) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    if (val.isEmpty())
      return ret;
    ret.add(val.get(0));

    for (int i = 1; i < val.size(); i++)
      if (val.get(i) != val.get(i - 1))
        ret.add(val.get(i));

    return ret;
  }

  static int update(int prev, int l, int r, int val, int inc) {
    if (l <= val && val <= r) {
      if (l == r) {
        nodes[nodeIndex].cnt = nodes[prev].cnt + inc;
        return nodeIndex++;
      }
      int mid = (l + r) >> 1;
      int curr = nodeIndex++;
      nodes[curr].cnt = nodes[prev].cnt + inc;
      nodes[curr].left = update(nodes[prev].left, l, mid, val, inc);
      nodes[curr].right = update(nodes[prev].right, mid + 1, r, val, inc);
      return curr;
    }
    return prev;
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

  static class State {
    int cnt;
    int n;

    State(int cnt, int n) {
      this.cnt = cnt;
      this.n = n;
    }
  }

  static class Person implements Comparable<Person> {
    int lo, hi;

    Person(int lo, int hi) {
      this.lo = lo;
      this.hi = hi;
    }

    @Override
    public int compareTo(Person p) {
      return lo - p.lo;
    }
  }

  static class Node {
    int left, right;
    int cnt;

    Node(int cnt) {
      this(-1, -1, cnt);
    }

    Node(int left, int right, int cnt) {
      this.left = left;
      this.right = right;
      this.cnt = cnt;
    }
  }
}
