package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class VMSS_Whipping_Out_The_Old_Dictionary {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (; t > 0; t--) {
      ArrayList<String[]> input = new ArrayList<String[]>();

      String in = "";

      while ((in = br.readLine()) != null && !in.equals(""))
        input.add(in.split(" "));

      ArrayList<String> dictionary = new ArrayList<String>();
      for (int x = 0; x < input.size(); x++) {
        for (int y = 0; y < input.get(x).length; y++) {
          String s = input.get(x)[y];
          if (dictionary.indexOf(s) == -1) {
            dictionary.add(s);
            System.out.print(s + " ");
          } else {
            System.out.print(dictionary.indexOf(s) + 1 + " ");
          }
        }
        System.out.println();
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}