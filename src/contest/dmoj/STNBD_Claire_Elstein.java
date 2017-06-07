package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class STNBD_Claire_Elstein {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    char[] c1 = readLine().toCharArray();
    char[] c2 = readLine().toCharArray();
    int[] occ = new int[26];
    for (int x = 0; x < c1.length; x++)
      occ[c1[x] - 'a']++;
    for (int x = 0; x < c2.length; x++)
      occ[c2[x] - 'a']--;
    int sum = 0;
    for (int x = 0; x < 26; x++)
      sum += Math.abs(occ[x]);
    System.out.println(sum);
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
