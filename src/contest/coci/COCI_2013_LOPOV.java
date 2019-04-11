package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class COCI_2013_LOPOV {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, K;
  static Item[] items;
  static TreeMap<Integer, Integer> bags;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    items = new Item[N];
    bags = new TreeMap<Integer, Integer>();

    for (int i = 0; i < N; i++)
      items[i] = new Item(readInt(), readInt());

    Arrays.sort(items);

    for (int i = 0; i < K; i++) {
      int c = readInt();
      if (!bags.containsKey(c))
        bags.put(c, 0);
      bags.put(c, bags.get(c) + 1);
    }

    long ans = 0;

    for (int i = 0; i < N; i++) {
      Integer c = bags.ceilingKey(items[i].mass);
      if (c != null) {
        ans += items[i].value;
        bags.put(c, bags.get(c) - 1);
        if (bags.get(c) == 0)
          bags.remove(c);
      }
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

  static class Item implements Comparable<Item> {
    int mass, value;

    Item(int mass, int value) {
      this.mass = mass;
      this.value = value;
    }

    @Override
    public int compareTo(Item item) {
      return item.value - value;
    }
  }
}
