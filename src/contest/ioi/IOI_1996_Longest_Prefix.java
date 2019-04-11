package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class IOI_1996_Longest_Prefix {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;
  static boolean[] dp;
  static ArrayList<HashSet<Integer>> adj;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));
    ArrayList<String> words = new ArrayList<String>();
    String in = null;
    while (!(in = next()).equals("."))
      words.add(in);
    String seq = " ";
    while ((in = br.readLine()) != null)
      seq += in;
    dp = new boolean[seq.length()];
    dp[0] = true;
    for (int i = 0; i < dp.length; i++) {
      for (String word : words) {
        if (dp[i]) {
          if (i + word.length() < dp.length && word.equals(seq.substring(i + 1, i + word.length() + 1)))
            dp[i + word.length()] = true;
        }
      }
    }
    for (int i = seq.length() - 1; i >= 0; i--) {
      if (dp[i]) {
        System.out.println(i);
        return;
      }
    }
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
