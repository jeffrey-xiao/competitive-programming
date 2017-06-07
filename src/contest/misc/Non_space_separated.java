package contest.misc;

import java.math.BigInteger;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Non_space_separated {
  static Scanner scan = new Scanner(System.in);
  static TreeMap<BigInteger, Integer> m = new TreeMap<BigInteger, Integer>();

  public static void main (String[] args) {
    String s = scan.next();
    getSums(s, new BigInteger("0"));
    int max = 0;
    String key = "";
    for (Map.Entry<BigInteger, Integer> entry : m.entrySet()) {
      if (entry.getValue() > max) {
        key = entry.getKey().toString();
        max = entry.getValue();
      }
    }
    System.out.printf("%d%s", m.size(), key);
  }

  private static void getSums (String s, BigInteger i) {
    if (s.length() == 0) {
      if (m.get(i) == null)
        m.put(i, 1);
      else
        m.put(i, m.get(i) + 1);
    }
    for (int x = 0; x < s.length(); x++) {
      BigInteger curr = new BigInteger(s.substring(0, x + 1));
      getSums(s.substring(x + 1, s.length()), i.add(curr));
    }

  }

}
