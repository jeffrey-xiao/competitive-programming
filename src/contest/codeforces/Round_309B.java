package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Round_309B {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static ArrayList<Integer> left = new ArrayList<Integer>();

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));
    long n = readLong();
    long k = readLong();
    for (int i = 0; i < n; i++)
      left.add(i + 1);
    compute(n, k);
    pr.close();
  }

  private static void compute (long n, long k) {
    if (n == 0) {
      return;
    }
    if (n == 1 && k == 1) {
      print(0);
      return;
    }
    long cnt = 0;
    for (int i = 1; i <= n; i++) {
      cnt += (long)Math.ceil(Math.pow(2, n - i - 1));
      if (k <= cnt) {
        for (int j = i; j >= 1; j--) {
          print(j - 1);
        }
        compute(n - i, k - (long)((cnt - Math.ceil(Math.pow(2, n - i - 1)))));
        break;
      }
    }
  }

  private static void print (int i) {
    System.out.print(left.get(i) + " ");
    left.remove(i);
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
