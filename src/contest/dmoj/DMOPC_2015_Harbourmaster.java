package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_Harbourmaster {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static double C, S, P;
  static int N;

  static int[] c, s, p;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    C = readInt();
    S = readInt();
    P = readInt();

    N = readInt();

    c = new int[N];
    s = new int[N];
    p = new int[N];

    int totalC = 0;
    int totalS = 0;
    int totalP = 0;

    for (int i = 0; i < N; i++) {
      totalC += c[i] = readInt();
      totalS += s[i] = readInt();
      totalP += p[i] = readInt();
    }

    double max = 0;

    if (N <= 5) {
      max = Math.min(totalC / C, Math.min(totalS / S, totalP / P));
    } else {
      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          for (int k = j + 1; k < N; k++) {
            for (int l = k + 1; l < N; l++) {
              for (int m = l + 1; m < N; m++) {
                totalC = c[i] + c[j] + c[k] + c[l] + c[m];
                totalS = s[i] + s[j] + s[k] + s[l] + s[m];
                totalP = p[i] + p[j] + p[k] + p[l] + p[m];
                max = Math.max(max, Math.min(totalC / C, Math.min(totalS / S, totalP / P)));
              }
            }
          }
        }
      }
    }

    out.printf("%.1f\n", Math.min(100, max * 100));
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
