package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Mackenzie_Moores_Law {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    double d = readDouble();
    if (d == 2) {
      out.println("Now!");
    } else {
      int days = (int) (Math.ceil(Math.log(d / 2.0) / Math.log(2) * 2 * 365));
      int years = days / 365;
      days -= years * 365;

      int months = days / 30;
      days -= months * 30;

      int weeks = days / 7;
      days -= weeks * 7;

      if (years > 0)
        out.print(years + "Y ");
      if ((int) (months) > 0)
        out.print(months + "M ");
      if ((int) (weeks) > 0)
        out.print(weeks + "W ");
      if ((int) (days) > 0)
        out.print(days + "D ");
    }

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
