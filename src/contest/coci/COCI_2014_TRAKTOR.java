package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_TRAKTOR {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int n = readInt();
    int k = readInt();
    int[] cntx = new int[100001];
    int[] cnty = new int[100001];
    int[] diag1 = new int[200002];
    int[] diag2 = new int[200002];
    for (int i = 0; i < n; i++) {
      int x = readInt();
      int y = readInt();
      cntx[x]++;
      cnty[y]++;
      diag1[x + y]++;
      diag2[x - y + 100001]++;
      if (cntx[x] >= k || cnty[y] >= k || diag1[x + y] >= k || diag2[x - y + 100001] >= k) {
        ps.println(i + 1);
        ps.close();
        return;
      }
    }

    ps.println(-1);
    ps.close();
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