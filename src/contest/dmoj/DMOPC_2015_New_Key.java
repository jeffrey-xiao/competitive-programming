package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_New_Key {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    char[] encrypted = readLine().toCharArray();
    char[] decrypted = new char[encrypted.length];

    for (int i = 0; i < encrypted.length; i++) {
      if (encrypted[i] == '0')
        decrypted[i] = '9';
      else if ('1' <= encrypted[i] && encrypted[i] <= '9')
        decrypted[i] = (char)('A' + encrypted[i] - '0' - 1);
      else if ('A' <= encrypted[i] && encrypted[i] <= 'J')
        decrypted[i] = (char)('K' + encrypted[i] - 'A' - 1);
      else if ('K' <= encrypted[i] && encrypted[i] <= 'P')
        decrypted[i] = (char)('U' + encrypted[i] - 'K' - 1);
    }
    out.println(decrypted);
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
