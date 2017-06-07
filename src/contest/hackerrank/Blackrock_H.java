package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Blackrock_H {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] val;
  static int[][] cache;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    char[] in = next().toCharArray();
    N = in.length;
    val = new int[N];

    for (int i = 0; i < N; i++)
      val[i] = in[i] == 'a' ? 0 : 1;

    if (N % 2 == 1) {
      out.println(0);
      out.close();
      return;
    }

    int half = N / 2;
    cache = new int[half + 1][];
    for (int i = 0; i <= half; i++) {
      cache[i] = new int[1 << i];
    }

    // solving left bitmasks
    main : for (int i = 0; i < (1 << half); i++) {
      int cnt = bitCount(i);
      if (cnt < half - cnt)
        continue;
      int set1 = 0;
      int set2 = 0;
      int len1 = 0;
      int len2 = 0;

      // a is 0, b is 1
      for (int j = 0; j < half; j++) {
        // in set1
        if ((i & 1 << j) > 0) {
          set1 |= (val[j] << len1);
          len1++;
        }

        // in set2
        else {
          set2 |= (val[j] << len2);
          len2++;
        }

      }
      if ((set1 & ((1 << Math.min(len1, len2)) - 1)) != (set2 & ((1 << Math.min(len1, len2)) - 1)))
        continue main;
      if (len1 > len2) {
        cache[len1 - len2][(set1 >> len2)]++;
      } else if (len2 == len1) {
        if ((i ^ ((1 << half) - 1)) < i)
          cache[0][0]++;
      }
    }

    long ans = 0;

    // solving right bitmasks
    main : for (int i = 0; i < (1 << half); i++) {
      int cnt = bitCount(i);
      if (cnt < half - cnt)
        continue;
      int set1 = 0;
      int set11 = 0;
      int set22 = 0;
      int set2 = 0;
      int len1 = 0;
      int len2 = 0;
      for (int j = half - 1; j >= 0; j--) {
        // in set1
        if ((i & 1 << j) > 0) {
          set1 = set1 | (val[half + j] << len1);
          set11 = (set11 << 1) | val[half + j];
          len1++;
        }
        // in set2
        else {
          set2 = set2 | (val[half + j] << len2);
          set22 = (set22 << 1) | val[half + j];
          len2++;
        }

      }
      if ((set1 & ((1 << Math.min(len1, len2)) - 1)) != (set2 & ((1 << Math.min(len1, len2)) - 1)))
        continue main;
      if (len1 > len2) {
        ans += cache[len1 - len2][(set11 & (((1 << (len1 - len2)) - 1)))];
      } else if (len2 == len1) {
        ans += cache[0][0];
      }
    }

    out.println(ans * 2l);
    out.close();
  }

  static int bitCount (int x) {
    x = x - ((x >> 1) & 0x55555555);
    x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
    x = (x + (x >> 4)) & 0x0F0F0F0F;
    x = x + (x >> 8);
    x = x + (x >> 16);
    return x & 0x0000003F;
  }

  static class State {
    int mask, length;

    State (int mask, int length) {
      this.mask = mask;
      this.length = length;
    }

    @Override
    public boolean equals (Object o) {
      if (o instanceof State) {
        State s = (State)o;
        return s.mask == mask && s.length == length;
      }
      return false;
    }

    @Override
    public int hashCode () {
      return mask * 31 + length;
    }

    @Override
    public String toString () {
      return String.format("(%d, %d)", mask, length);
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
