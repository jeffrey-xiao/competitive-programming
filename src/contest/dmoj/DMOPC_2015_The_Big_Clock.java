package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_The_Big_Clock {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int g = readInt();
    for (int i = 0; i < g; i++) {
      int n = readInt();
      int sum = 0;
      int[] attack = new int[n];
      for (int j = 0; j < n; j++)
        sum += (attack[j] = readInt());
      int health = readInt();
      int taunt = readInt();
      boolean[] dp = new boolean[25];
      dp[0] = true;
      for (int j = 0; j < n; j++)
        for (int k = 24; k >= 0; k--)
          if (k - attack[j] >= 0 && dp[k - attack[j]])
            dp[k] = true;
      int value = -1;
      for (int j = taunt; j <= 24; j++)
        if (dp[j]) {
          value = j;
          break;
        }
      if (value == -1 || (sum - value < health))
        out.println("NOT LETHAL");
      else
        out.println("LETHAL");
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
