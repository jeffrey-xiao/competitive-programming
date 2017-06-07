package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DMOPC_2015_Magic {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int K, D, Sa, Sb, Pa, Pb;
  static boolean[][] v;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    boolean[] prime = new boolean[2001];

    Arrays.fill(prime, true);
    prime[0] = false;
    prime[1] = false;
    for (int i = 2; i * i <= 2000; i++)
      if (prime[i])
        for (int j = i * i; j <= 2000; j += i)
          prime[j] = false;

    K = readInt();
    D = readInt();
    Sa = readInt();
    Sb = readInt();
    Pa = readInt();
    Pb = readInt();

    v = new boolean[1001][1001];
    adjust(Pa, Pb);
    boolean aliceWon = false;
    while (!v[Pa][Pb]) {
      v[Pa][Pb] = true;
      int prevPa = Pa;
      int prevPb = Pb;
      Pa = (Pa + Sa) % K;
      Pb = (Pb + Sb) % K;
      adjust(prevPa, prevPb);
      if (prime[Math.min(Pa, K - Pa) + Math.min(Pb, K - Pb)]) {
        aliceWon = true;
        break;
      }
    }
    out.println(aliceWon ? "Alice" : "Bob");
    out.close();
  }

  static void adjust (int prevPa, int prevPb) {
    if (Math.abs(Pa - Pb) < D || Math.abs(Pa - Pb + K) < D) {
      if (prevPa < prevPb) {
        Pb = (Pa + D) % K;
      } else {
        Pb = ((Pa - D) % K + K) % K;
      }
    }
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
