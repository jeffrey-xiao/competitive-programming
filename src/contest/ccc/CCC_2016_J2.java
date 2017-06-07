package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2016_J2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int[] rowSum = new int[4];
    int[] colSum = new int[4];

    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++) {
        int val = readInt();
        rowSum[i] += val;
        colSum[j] += val;
      }
    if (rowSum[0] == rowSum[1] && rowSum[1] == rowSum[2] && rowSum[2] == rowSum[3] && colSum[0] == colSum[1] && colSum[1] == colSum[2] && colSum[2] == colSum[3] && rowSum[0] == colSum[0])
      out.println("magic");
    else
      out.println("not magic");

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
