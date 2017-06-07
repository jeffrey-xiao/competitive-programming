package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_GG {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    char[] in = (" " + next()).toCharArray();
    int[] sum = new int[in.length];
    for (int i = 1; i < in.length; i++)
      sum[i] = sum[i - 1] + (in[i] == 'G' ? 1 : 0);
    int q = readInt();
    for (int i = 0; i < q; i++) {
      int l = readInt() + 1;
      int r = readInt() + 1;
      out.println(sum[r] - sum[l - 1]);
    }
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
