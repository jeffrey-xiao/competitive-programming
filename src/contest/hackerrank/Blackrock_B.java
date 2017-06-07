package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Blackrock_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    int minTradeSize = readInt();
    int inc = readInt();
    int availableUnits = readInt();

    Order[] o = new Order[T];
    int[] ans = new int[T];

    TreeSet<String> names = new TreeSet<String>();
    HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
    HashMap<Integer, String> toName = new HashMap<Integer, String>();

    for (int i = 0; i < T; i++) {
      o[i] = new Order(next(), readInt());
      names.add(o[i].name);
    }

    int index = 0;
    for (String s : names) {
      toIndex.put(s, index);
      toName.put(index, s);
      index++;
    }

    for (int i = 0; i < T; i++)
      o[i].p = toIndex.get(o[i].name);

    Arrays.sort(o);

    int total = 0;
    for (int i = 0; i < T; i++)
      if (o[i].order != 0)
        total += o[i].order;

    for (int i = 0; i < T; i++) {
      double proportionalAllocation = 1.0 * o[i].order / total * availableUnits;
      int toAllocate = 0;
      if (proportionalAllocation < minTradeSize) {
        if (proportionalAllocation >= minTradeSize / 2.0) {
          toAllocate = minTradeSize;
        }
      } else {
        if (proportionalAllocation >= o[i].order)
          toAllocate = o[i].order;
        else {
          toAllocate = (int)(proportionalAllocation);
          toAllocate = ((toAllocate - minTradeSize) / inc) * inc + minTradeSize;
        }
      }
      if ((o[i].order - toAllocate < minTradeSize || (o[i].order - toAllocate - minTradeSize) % inc != 0) && o[i].order - toAllocate != 0)
        toAllocate = 0;

      total -= o[i].order;
      ans[o[i].p] += toAllocate;
      availableUnits -= toAllocate;
    }

    for (int i = 0; i < T; i++)
      out.printf("%s %d\n", toName.get(i), ans[i]);

    out.close();
  }

  static class Order implements Comparable<Order> {
    int p, order;
    String name;
    boolean done = false;

    Order (String name, int order) {
      this.name = name;
      this.order = order;
    }

    @Override
    public int compareTo (Order o) {
      if (order == o.order)
        return p - o.p;
      return order - o.order;
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
