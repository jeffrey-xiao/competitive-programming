package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class String_Similarity {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    for (int i = 0; i < n; i++)
      pr.println(getSum(next()));

    pr.close();
  }

  static int getSum (String s) {
    int[] z = new int[s.length()];
    int l = 0, r = 0;
    int sum = 0;
    for (int i = 1; i < s.length(); i++) {
      if (i > r) {
        l = r = i;
        while (r < s.length() && s.charAt(r - l) == s.charAt(r))
          r++;
        r--;
        z[i] = r - l + 1;
        sum += z[i];
      } else {
        int j = i - l;
        if (z[j] < r - i + 1)
          sum += (z[i] = z[j]);
        else {
          l = i;
          while (r < s.length() && s.charAt(r - l) == s.charAt(r))
            r++;
          r--;
          sum += (z[i] = r - l + 1);
        }
      }
    }
    return sum + s.length();
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
