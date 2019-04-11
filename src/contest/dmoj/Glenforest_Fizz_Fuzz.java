package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Glenforest_Fizz_Fuzz {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    for (int i = 1, j = 1; i <= n; i++, j += 2) {
      String a = "";
      String b = "";

      if (i % 7 == 0 && i % 13 == 0)
        a = "Fizz Fuzz";
      else if (i % 7 == 0)
        a = "Fizz";
      else if (i % 13 == 0)
        a = "Fuzz";
      else
        a = "" + i;

      if (j % 7 == 0 && j % 13 == 0)
        b = "Fizz Fuzz";
      else if (j % 7 == 0)
        b = "Fizz";
      else if (j % 13 == 0)
        b = "Fuzz";
      else
        b = "" + j;
      if ((a.equals("Fizz Fuzz") && (b.equals("Fizz") || b.equals("Fuzz"))) || (b.equals("Fizz Fuzz") && (a.equals("Fizz") || a.equals("Fuzz"))))
        System.out.println("Fizz Fuzz");
      else
        System.out.println(a + " " + b);
    }
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
