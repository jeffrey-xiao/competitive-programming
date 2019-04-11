package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class New_Year_Late_Christmas_Shopping {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    HashSet<Integer> hs = new HashSet<Integer>();
    for (int j = 0; j < n; j++) {
      m = readInt();
      int[] a = new int[m];
      for (int i = 0; i < m; i++) {
        a[i] = readInt();
        if (hs.contains(a[i])) {
          out.println("YES");
          out.close();
          return;
        }
      }
      for (int i = 0; i < m; i++)
        hs.add(a[i]);
    }
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
