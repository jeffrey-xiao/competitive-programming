package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class University_Codesprint_2_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int[] val = new int[N];

    for (int i = 0; i < N; i++)
      val[i] = readInt();

    int bestVal = val[0];
    int worstVal = val[0];
    int bestCount = 0;
    int worstCount = 0;
    for (int i = 1; i < N; i++) {
      if (val[i] > bestVal) {
        bestVal = val[i];
        bestCount++;
      }
      if (val[i] < worstVal) {
        worstVal = val[i];
        worstCount++;
      }
    }
    out.println(bestCount + " " + worstCount);
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
