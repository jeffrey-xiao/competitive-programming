package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_A_Very_Very_Original_Problem {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    String in = readLine();
    int len = in.length() * 2 + 1;
    char[] text = new char[len];
    for (int i = 0; i < len; i++)
      text[i] = '#';
    for (int i = 1; i < len; i += 2)
      text[i] = in.charAt(i / 2);

    int[] max = new int[len];

    int c = 0;
    int r = 0;
    for (int i = 1; i < len; i++) {
      int j = (c - (i - c));

      max[i] = r > i ? Math.min(r - i, max[j]) : 0;

      while (i + 1 + max[i] < len && i - 1 - max[i] >= 0 && text[i + 1 + max[i]] == text[i - 1 - max[i]])
        max[i]++;

      if (i + max[i] > r) {
        r = i + max[i];
        c = i;
      }
    }
    long[] prefix = new long[len + 1];
    long[] suffix = new long[len + 1];
    for (int i = 0; i < len; i++) {
      prefix[i]++;
      prefix[i + max[i]]--;

      suffix[i - max[i] + 1]++;
      suffix[i + 1]--;
    }
    for (int i = 1; i < len; i++) {
      prefix[i] += prefix[i - 1];
      suffix[i] += suffix[i - 1];
    }
    for (int i = 3; i < len; i += 2)
      prefix[i] += prefix[i - 2];

    long ans = 0;
    for (int i = 3; i < len; i += 2) {
      ans += suffix[i] * prefix[i - 2];
    }

    out.println(ans);
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
