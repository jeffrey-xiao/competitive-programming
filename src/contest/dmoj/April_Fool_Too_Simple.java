package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class April_Fool_Too_Simple {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));

    char a = (char) Math.ceil(Math.log(Character.MIN_VALUE + Character.BYTES));
    char b = get(a);
    char c = get(b);
    char d = get(c);
    char e = get(d);
    char f = get(e);
    char g = get(f);
    out.print((char) (g + d));
    out.print((char) (g + f + c + a));
    out.print((char) (g + f + d + c));
    out.print((char) (g + f + d + c));
    out.print((char) (g + f + d + c + b + a));
    out.print((char) (f + d + c));
    out.print((char) (f));
    out.print((char) (g + e + c + b + a));
    out.print((char) (g + f + d + c + b + a));
    out.print((char) (g + f + e + b));
    out.print((char) (g + f + d + c));
    out.print((char) (g + f + c));
    out.print((char) (f + a));

    out.close();
  }

  static char get(char c) {
    return (char) (c + c);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}