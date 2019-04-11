package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Russian_Palindrome_Cultivation {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    char[] input = next().toCharArray();
    boolean isAllNine = true;
    for (int i = 0; i < input.length; i++)
      if (input[i] != '9')
        isAllNine = false;

    if (isAllNine) {
      out.print("1");
      for (int i = 0; i < input.length - 1; i++)
        out.print("0");
      out.println("1");
    } else {
      int inc = 0;
      int i = input.length / 2;
      int j = (input.length - 1) / 2;
      while (j >= 0) {
        if (input[j] > input[i] && inc == 0)
          inc = -1;
        else if (input[j] < input[i] && inc == 0)
          inc = 1;
        input[i] = input[j];
        i++;
        j--;
      }
      if (inc == 1 || inc == 0) {
        i = input.length / 2;
        j = (input.length - 1) / 2;
        while (input[i] == '9') {
          input[i] = '0';
          i--;
          j--;
        }
        input[i]++;
        if (i != j)
          input[j]++;
      }
      out.println(new String(input));
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
