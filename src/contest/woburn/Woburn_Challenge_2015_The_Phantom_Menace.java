package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_The_Phantom_Menace {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = readInt();
    for (int i = 0; i < n; i++) {
      if (i > 0 && a[i] == 0) {
        for (int j = 1; j <= 4; j++) {
          if (a[i - 1] != j && (i + 1 >= a.length || a[i + 1] != j)) {
            a[i] = j;
            break;
          }
        }
      }
      if (i == 0 && a[i] == 0) {
        if (a.length == 1)
          a[i] = 1;
        if (a[i + 1] == 1)
          a[i] = 2;
        else
          a[i] = 1;
      }
    }
    for (int i = 0; i < n; i++)
      out.print(a[i]);
    out.println();

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
