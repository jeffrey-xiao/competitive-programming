package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CROC_2016_Elimination_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    String s1 = readLine();
    s1 += new StringBuilder(readLine()).reverse();

    String s2 = readLine();
    s2 += new StringBuilder(readLine()).reverse();

    String res1 = "";
    for (int i = 0; i < 4; i++)
      if (s1.charAt(i) != 'X')
        res1 += s1.charAt(i);

    String res2 = "";
    for (int i = 0; i < 4; i++)
      if (s2.charAt(i) != 'X')
        res2 += s2.charAt(i);

    if ((res1 + res1).indexOf(res2) != -1)
      out.println("YES");
    else
      out.println("NO");

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
