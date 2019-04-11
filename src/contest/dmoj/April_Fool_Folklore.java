package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class April_Fool_Folklore {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));
    out.print(",-------------------------------------------------------------------------------------------------");
    compute(0, ".");
    out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.");
    out.close();
  }

  static void compute(int i, String curr) {
    if (i == 14)
      return;
    out.print("[-");
    String next = "";
    next += curr;
    for (int j = 0; j < i; j++) {
      next += "-";
    }
    next += curr.substring(0, curr.length() - i - 1);
    for (int j = 0; j <= i; j++) {
      next += "+";
    }
    next += ".";
    compute(i + 1, next);
    out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    out.print(curr);
    for (int j = 0; j < i; j++) {
      out.print("-");
    }
    out.print(curr.substring(0, curr.length() - i - 1));
    for (int j = 0; j <= i; j++) {
      out.print("+");
    }
    out.print(".");
    out.println("[-]]");
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
