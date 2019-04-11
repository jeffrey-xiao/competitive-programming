package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MEC_P2 {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();

    String in1 = readLine();
    String in2 = readLine();

    int left = 1 << 30;
    int right = 1 << 30;

    if (in1.equals(in2)) {
      out.println("L0");
    } else {
      for (int i = 0; i < N; i++) {
        in1 = in1.substring(1) + in1.charAt(0);
        if (in1.equals(in2)) {
          left = Math.min(left, i + 1);
        }
      }
      for (int i = 0; i < N; i++) {
        in1 = in1.charAt(in1.length() - 1) + in1.substring(0, in1.length() - 1);
        if (in1.equals(in2)) {
          right = Math.min(left, i + 1);
        }
      }

      if (left == 1 << 30 && right == 1 << 30) {
        out.println(-1);
      } else if (left <= right) {
        out.println("L" + left);
      } else {
        out.println("R" + right);
      }
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
