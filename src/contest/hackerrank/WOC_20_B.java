package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WOC_20_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, K;
  static int[] occ;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    occ = new int[K];

    for (int i = 0; i < N; i++) {
      occ[readInt() % K]++;
    }

    int ans = 0;

    for (int i = 1; i < K; i++) {
      if (i == K - i)
        ans += Math.min(1, occ[i]) * 2;
      else
        ans += Math.max(occ[i], occ[K - i]);

    }

    ans /= 2;
    ans += Math.min(1, occ[0]);

    out.println(ans);
    out.close();
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
