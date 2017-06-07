package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_MMORPG {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int r = readInt();
    int n = readInt();
    int[] x1 = new int[r], x2 = new int[r], y1 = new int[r], y2 = new int[r];
    boolean[] unlock = new boolean[r];
    for (int i = 0; i < r; i++) {
      x1[i] = readInt();
      y1[i] = readInt();
      x2[i] = readInt() + x1[i];
      y2[i] = readInt() + y1[i];
    }
    for (int i = 0; i < n; i++) {
      int x = readInt();
      int y = readInt();
      for (int j = 0; j < r; j++)
        if (x1[j] <= x && x < x2[j] && y1[j] <= y && y < y2[j])
          unlock[j] = true;
    }
    int cnt = 0;
    for (int i = 0; i < r; i++)
      if (unlock[i])
        cnt++;
    out.println(cnt);
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
