package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_353B_Div2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, A, B, C, D;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    A = readInt();
    B = readInt();
    C = readInt();
    D = readInt();

    int a = 1;
    int b = a + A - D;
    int c = b + B - C;
    int d = c + D - A;

    int min = Math.min(a, Math.min(b, Math.min(c, d)));
    int max = Math.max(a, Math.max(b, Math.max(c, d)));

    out.println(Math.max(0, 1l * N * (N - max + min)));
    out.close();
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
