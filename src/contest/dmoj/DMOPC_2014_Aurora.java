package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DMOPC_2014_Aurora {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  @SuppressWarnings("unused")
  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    long a = readInt();
    long b = readInt();
    long c = readInt();
    Integer[] in = new Integer[n + 2];
    for (int i = 1; i <= n; i++) {
      in[i] = readInt();
    }
    Arrays.sort(in, 1, in.length - 1);
    long[] prefix = new long[n + 2];
    long[] suffix = new long[n + 2];
    for (int i = 1; i <= n; i++) {
      prefix[i] = (in[i] - 1) * b + prefix[i - 1];
    }
    for (int i = n; i >= 1; i--) {
      suffix[i] = suffix[i + 1] + (in[i] - 1) * a + c * (n - i);
    }
    long min = 1l << 60;
    for (int i = 0; i <= n; i++)
      min = Math.min(min, prefix[i] + suffix[i + 1]);
    System.out.println(min);
    pr.close();
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
