package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ACM_NEERC_2014_E {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();
    int[] x = new int[N], y = new int[N];
    int wins = 0;
    for (int i = 0; i < N; i++) {
      x[i] = readInt();
      y[i] = readInt();
      if (x[i] > y[i])
        wins++;
    }

    ArrayList<Integer> ans = new ArrayList<Integer>();
    int need = (N - wins) - wins + 1;
    for (int i = 1; i < N; i++) {
      if (need <= 0)
        break;
      if (x[i] > y[i] && x[i - 1] > y[i - 1])
        continue;
      if ((x[i] + x[i - 1] > y[i] + y[i - 1]) || (x[i] <= y[i] && x[i - 1] <= y[i - 1])) {
        ans.add(i);
        i++;
        need--;
      }
    }
    if (need > 0)
      out.println(-1);
    else {
      out.println(ans.size());
      for (int index : ans)
        out.println(index + " " + (index + 1));
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
