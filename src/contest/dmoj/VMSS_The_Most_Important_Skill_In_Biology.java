package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_The_Most_Important_Skill_In_Biology {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] X, Y;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    X = new int[N];
    Y = new int[N];

    for (int i = N - 1; i >= 0; i--) {
      X[i] = readInt();
      Y[i] = readInt();
    }
    double area = 0;

    for (int i = 0; i < N; i++) {
      area += X[i] * Y[(i + 1) % N] - Y[i] * X[(i + 1) % N];
    }
    out.println((int)(Math.ceil(Math.abs(area) / 2)));
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
