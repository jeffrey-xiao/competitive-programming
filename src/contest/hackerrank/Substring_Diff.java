package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Substring_Diff {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int n;
  static char[] p, q;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    for (int t = readInt(); t > 0; t--) {
      int s = readInt();
      p = next().toCharArray();
      q = next().toCharArray();
      n = p.length;
      int max = 0;
      for (int i = 0; i < n; i++) {
        max = Math.max(max, Math.max(getMax(0, i, s), getMax(i, 0, s)));

      }
      out.println(max);
    }

    out.close();
  }

  static int getMax(int i, int j, int s) {
    int len = 0;
    int mismatch = 0;
    int best = 0;
    while (i + len < n && j + len < n) {
      if (p[i + len] != q[j + len])
        mismatch++;
      if (mismatch <= s) {
        best = Math.max(best, len + 1);
        len++;
      } else {
        while (i < n && j < n && p[i] == q[j]) {
          i++;
          j++;
          len--;
        }
        i++;
        j++;
        mismatch--;
      }
    }
    return best;
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
}
