package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SEED_Battle_Positions {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();
    int q = readInt();
    int[] sum = new int[n + 1];
    for (int i = 0; i < q; i++) {
      int a = readInt() - 1;
      int b = readInt();
      int c = readInt();
      sum[a] += c;
      sum[b] -= c;
    }
    int curr = 0;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      sum[i] = (curr += sum[i]);
      if (sum[i] < m)
        cnt++;
    }
    pr.println(cnt);
    pr.close();
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
