package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_BFed {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    String c = "";
    String next = readLine();
    c += next;
    while (br.ready()) {
      next = readLine();
      if (next == null)
        break;
      c += next;
    }
    int currCell = 0;
    char[] commands = c.toCharArray();
    short[] v = new short[30001];
    for (int x = 0; x < commands.length;) {
      if (commands[x] == '+')
        v[currCell] = (short)((v[currCell] + 257) % 256);
      else if (commands[x] == '-')
        v[currCell] = (short)((v[currCell] + 255) % 256);
      else if (commands[x] == '.')
        System.out.print((char)v[currCell]);
      else if (commands[x] == '>') {
        currCell++;
      } else if (commands[x] == '<')
        currCell--;
      else if (commands[x] == '[' && v[currCell] == 0) {
        int num = 0;
        while (num != 0 | commands[++x] != ']') {
          if (commands[x] == '[')
            num++;
          else if (commands[x] == ']')
            num--;
        }
      } else if (commands[x] == ']' && v[currCell] != 0) {
        int num = 0;
        while (num != 0 | commands[--x] != '[') {
          if (commands[x] == ']')
            num++;
          else if (commands[x] == '[')
            num--;
        }
      }
      x++;
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
