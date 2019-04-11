package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Mackenize_Genetic_Probability {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();

    char[] in1 = readLine().toCharArray();
    char[] in2 = readLine().toCharArray();
    char[] in3 = readLine().toCharArray();

    double prob = 1.0;

    for (int i = 0; i < N; i++) {
      int cnt = 0;
      for (int j = 0; j < 2; j++)
        for (int k = 0; k < 2; k++)
          if ((in1[i * 2 + j] == in3[i * 2] && in2[i * 2 + k] == in3[i * 2 + 1]) || (in1[i * 2 + j] == in3[i * 2 + 1] && in2[i * 2 + k] == in3[i * 2]))
            cnt++;

      prob *= cnt / 4.0;
    }
    out.printf("%.10f\n", prob);
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
