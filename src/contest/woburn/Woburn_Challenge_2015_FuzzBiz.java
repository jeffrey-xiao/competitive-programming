package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_FuzzBiz {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;

  // 0 = not divisible by 3 or 5
  // 1 = divisible by 3
  // 2 = divisible by 5
  // 3 = divisible by 3 and 5
  static int[] states;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    states = new int[M];
    for (int i = 0; i < M; i++) {
      String in = readLine();
      if (in.equals("fizz"))
        states[i] = 1;
      else if (in.equals("buzz"))
        states[i] = 2;
      else if (in.equals("fizzbuzz"))
        states[i] = 3;
    }

    int ans = 0;

    for (int i = 1; i <= 15; i++) {
      boolean valid = true;
      for (int j = 0; j < M; j++) {
        int state = 0;
        if ((i + j) % 3 == 0 && (i + j) % 5 == 0)
          state = 3;
        else if ((i + j) % 3 == 0)
          state = 1;
        else if ((i + j) % 5 == 0)
          state = 2;
        if (state != states[j])
          valid = false;
      }
      if (valid) {
        ans += (N - i - M + 16) / 15;
      }
    }
    out.println(ans);

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
