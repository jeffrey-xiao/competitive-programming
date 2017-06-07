package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ACM_Greater_New_York_2015_I {
  public static void main (String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    int P = Integer.parseInt(in.readLine());
    main : while (P-- > 0) {
      StringTokenizer st = new StringTokenizer(in.readLine());
      int K = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      String s = st.nextToken();

      Map<String, Boolean> seen = new HashMap<String, Boolean>();
      seen.put(s, true);

      if (s.length() >= 8 && s.substring(0, 4).equals("1233") && s.substring(s.length() - 4).equals("4444"))
        if (s.substring(4, s.length() - 4).matches("[3]*")) {
          System.out.println(K + " C " + 1);
          continue main;
        }

      if (s.length() >= 8 && s.substring(0, 4).equals("5566") && s.substring(s.length() - 4).equals("7777"))
        if (s.substring(4, s.length() - 4).matches("[6]*")) {
          System.out.println(K + " C " + 1);
          continue main;
        }

      for (int i = 2; i <= M; i++) {
        s = rats(s);

        if (seen.containsKey(s)) {
          System.out.println(K + " R " + i);
          continue main;
        }
        seen.put(s, true);

        if (s.length() >= 8 && s.substring(0, 4).equals("1233") && s.substring(s.length() - 4).equals("4444"))
          if (s.substring(4, s.length() - 4).matches("[3]*")) {
            System.out.println(K + " C " + i);
            continue main;
          }

        if (s.length() >= 8 && s.substring(0, 4).equals("5566") && s.substring(s.length() - 4).equals("7777"))
          if (s.substring(4, s.length() - 4).matches("[6]*")) {
            System.out.println(K + " C " + i);
            continue main;
          }
      }

      System.out.println(K + " " + s);
    }
  }

  static String rats (String s) {
    String r = new StringBuilder(s).reverse().toString();
    BigInteger ss = new BigInteger(s), rs = new BigInteger(r);
    char[] nxt = ss.add(rs).toString().toCharArray();
    Arrays.sort(nxt);

    return new BigInteger(new String(nxt)).toString();
  }
}