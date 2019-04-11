// Stack overflows on the last case: See Good_Code_2 for thread implementation

package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Good_Code {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static long m;
  static int[] state, cnt;
  static HashMap<Integer, Integer> labelToLine = new HashMap<Integer, Integer>();
  static boolean[] isCycle, v;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readLong();

    state = new int[n + 1];
    cnt = new int[n + 1];
    isCycle = new boolean[n + 1];
    v = new boolean[n + 1];

    for (int i = 1; i <= n; i++) {
      String s = readLine();
      if (s.equals("c++;"))
        state[i] = -1;
      else if (s.charAt(s.length() - 1) == ':')
        labelToLine.put(Integer.parseInt(s.substring(0, s.length() - 1)), i);
      else
        state[i] = Integer.parseInt(s.substring(5, s.length() - 1));
    }

    dfs(1);

    int curr = 1;

    while (curr != n + 1) {
      if (isCycle[curr]) {
        if (cnt[curr] == 0) {
          out.println("TLE");
          out.close();
          return;
        } else {
          m %= cnt[curr];
          if (m == 0)
            m += cnt[curr];
        }
      }
      if (state[curr] <= 0) {
        m += state[curr];
        if (m == 0)
          break;
        curr++;
      } else {
        curr = labelToLine.get(state[curr]);
      }
    }
    if (m == 0)
      out.println(curr);
    else
      out.println("WA");
    out.close();
  }

  static int dfs(int i) {
    v[i] = true;
    if (state[i] <= 0) {
      cnt[i] -= state[i];
      if (i == n)
        return cnt[i];
      if (v[i + 1]) {
        isCycle[i + 1] = true;
        return cnt[i];
      }
      cnt[i] += dfs(i + 1);
      return cnt[i];
    } else {
      int next = labelToLine.get(state[i]);
      if (v[next]) {
        isCycle[next] = true;
        return cnt[i];
      }
      cnt[i] = dfs(next);
      return cnt[i];
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
