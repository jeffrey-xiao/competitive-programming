package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Round_336C_Div2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] tree;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    tree = new int[1000100];

    int n = readInt();
    Beacon[] b = new Beacon[n];
    for (int i = 0; i < n; i++)
      b[i] = new Beacon(readInt() + 1, readInt());

    Arrays.sort(b);
    for (int i = 0; i < n; i++) {
      b[i].numDestroyed = query(b[i].pos) - query(b[i].pos - b[i].power - 1);
      update(b[i].pos, 1);
    }
    int[] saved = new int[n];
    int max = 0;
    for (int i = 0; i < n; i++) {
      saved[i] = 1;
      if (i - 1 - b[i].numDestroyed >= 0)
        saved[i] += saved[i - 1 - b[i].numDestroyed];
      max = Math.max(saved[i], max);
    }
    out.println(n - max);
    out.close();
  }

  public static void update(int idx, int val) {
    for (int x = idx; x < 1000100; x += (x & -x))
      tree[x] += val;
  }

  public static int query(int idx) {
    int res = 0;
    for (int x = idx; x > 0; x -= (x & -x))
      res += tree[x];
    return res;
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

  static class Beacon implements Comparable<Beacon> {
    int pos, power;
    int numDestroyed;

    Beacon(int pos, int power) {
      this.pos = pos;
      this.power = power;
    }

    @Override
    public int compareTo(Beacon o) {
      return pos - o.pos;
    }
  }
}
